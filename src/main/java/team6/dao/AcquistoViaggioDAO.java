package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import team6.entities.Abbonamento;
import team6.entities.AcquistoViaggio;
import team6.entities.Distributore;
import team6.enums.StatoDistributore;
import team6.exceptions.DistributoreFuoriServizioException;
import team6.exceptions.TesseraScadutaException;

import java.time.LocalDate;

public class AcquistoViaggioDAO {
    private final EntityManager em;

    public AcquistoViaggioDAO(EntityManager em) {
        this.em = em;
    }

    //Metodi di utilities
    //Save con i controlli se il distributore da dove stiamo acquistando è fuori servizio
    public void save(AcquistoViaggio acquisto) {
        //La macchinetta è fuori servizio?
        if (acquisto.getPuntoVendita() instanceof Distributore) {
            Distributore dist = (Distributore) acquisto.getPuntoVendita();
            if (dist.getStato() == StatoDistributore.FUORI_SERVIZIO) {
                throw new DistributoreFuoriServizioException("Impossibile emettere il titolo: questo distributore è fuori servizio!");
            }
        }
        //La tessera dell'abbonamento è scaduta?
        if (acquisto instanceof Abbonamento) {
            Abbonamento abb = (Abbonamento) acquisto;
            // Usiamo il metodo isscaduta per il controllo e gestione errori
            if (abb.getTessera().isScaduta()) {
                throw new TesseraScadutaException("Impossibile emettere l'abbonamento: la tessera inserita è scaduta!");
            }
        }
        //Se tutti i controlli vanno bene salva nel database
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(acquisto);
            tx.commit();
            System.out.println("Titolo emesso con successo!");
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        }
    }

    //Metodi task
    //Conta quanti biglietti e abbonamenti in totale sono stati venduti in un periodo
    public long contaTotaleTitoliEmessi(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM AcquistoViaggio a WHERE a.dataAcquisto BETWEEN :inizio AND :fine",
                Long.class
        );
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getSingleResult(); //restituisce il numero totale di titoliviaggio venduti
    }

    //Conta quanti biglietti/abbonamenti sono stati venduti da un solo negozio o macchinetta
    public long contaTitoliPerPuntoVendita(Long idPunto, LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM AcquistoViaggio a WHERE a.puntoVendita.id = :idPunto AND a.dataAcquisto BETWEEN :inizio AND :fine",
                Long.class
        );
        query.setParameter("idPunto", idPunto);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getSingleResult(); //restituisce il numero di biglietti in quel rivenditore o macchinetta
    }


    //query per contare quanti bilgietti sono stati timbrati in un mezzo dato l'id

    public long countBigliettiTimbrati(long idMezzo) {
        return this.em
                .createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.mezzo.id = :idMezzo AND b.dataValidazione IS NOT NULL", Long.class)
                .setParameter("idMezzo", idMezzo)
                .getSingleResult();
    }

    //query per contare quanti bilgietti sono stati timbrati in un determinato periodo
    public long countBigliettiTimbratiByPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        return this.em
                .createQuery("SELECT COUNT(b) FROM Biglietto b WHERE b.dataValidazione BETWEEN :dataInizio AND :dataFine", Long.class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getSingleResult();
    }
}
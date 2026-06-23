package team6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team6.entities.Tessera;
import team6.exceptions.ElementoNonTrovatoException;

import java.time.LocalDate;
import java.util.UUID;

public class TesseraDao {
    private final EntityManager em;

    public TesseraDao(EntityManager em){
        this.em=em;
    }

    //Metodi di utilities
    //Salva tessera
    public void save(Tessera tessera){
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.persist(tessera);
            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Cerca una tessera dall'id
    public Tessera findById(Long id){
        Tessera trovata = em.find(Tessera.class, id);
        //Se non la trova lancio l'errore custom
        if(trovata==null){
            throw new ElementoNonTrovatoException("La tessera con ID: " + id + " non è stata trovata");
        } return trovata;
    }

    //Cerca una tessera usando direttamente il suo codice uuid
    public Tessera findByCodiceTessera(UUID codiceTessera) {
        jakarta.persistence.TypedQuery<Tessera> query = em.createQuery(
                "SELECT t FROM Tessera t WHERE t.codTessera = :codice",
                Tessera.class
        );
        query.setParameter("codice", codiceTessera);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            throw new team6.exceptions.ElementoNonTrovatoException("Nessuna tessera trovata con il codice: " + codiceTessera);
        }
    }

    //Metodi task
    //Rinnova tessera
    public void rinnovaTessera(Long idTessera){
        //uso la findbyid per gestire anche l'errore se l'id è sbagliato
        Tessera tessera = findById(idTessera);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // uso il setter per rinnovarla da oggi e scadenza più un anno
        tessera.setDataEmissione(LocalDate.now());
        tessera.setDataScadenza(LocalDate.now().plusYears(1));
        tx.commit();
        System.out.println("Tessera rinnovata con successo");
    }

    //Simulo li controllore che inserisce il codice uuid della tessera e vede se c'è un abbonamento valido
    public boolean haAbbonamentoValido(UUID codiceTessera) {
        jakarta.persistence.TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(a) FROM Abbonamento a WHERE a.tessera.codTessera = :codice AND a.dataScadenza >= :oggi",
                Long.class
        );
        query.setParameter("codice", codiceTessera);
        query.setParameter("oggi", LocalDate.now()); // Prende la data di oggi

        long risultato = query.getSingleResult();

        // Se trova almeno 1 abbonamento non scaduto, restituisce TRUE (Valido!), altrimenti FALSE
        return risultato > 0;
    }
}
package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.AcquistoViaggioDAO;
import team6.dao.PuntoVenditaDao;
import team6.dao.TesseraDao;
import team6.dao.UtenteDao;
import team6.entities.*;
import team6.enums.StatoDistributore;
import team6.enums.TipoAbbonamento;

import java.time.LocalDate;
import java.time.LocalTime;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-buildweek-team6-pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        // assegno tutti i dao
        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        PuntoVenditaDao puntoVenditaDao = new PuntoVenditaDao(em);
        AcquistoViaggioDAO acquistoViaggioDAO = new AcquistoViaggioDAO(em);

        System.out.println("Test zona 1");

        try {
            //Creiamo e salviamo un Utente
            System.out.println("\n Creazione Utente");
            Utente utente = new Utente("Luigi", "Verdi", LocalDate.of(1995, 8, 22));
            utenteDao.save(utente);

            //Creiamo e salviamo la Tessera per questo utente (genera l'UUID in automatico)
            System.out.println("\n Creazione Tessera");
            Tessera tessera = new Tessera(LocalDate.now(), utente);
            tesseraDao.save(tessera);
            System.out.println("Codice Tessera generato: " + tessera.getCodTessera());

            //Creiamo e salviamo un Rivenditore punto vendita fisso
            System.out.println("\n Creazione Rivenditore");
            Rivenditore tabaccheria = new Rivenditore(
                    "Tabaccheria Centrale",
                    "Corso Vittorio",
                    "45",
                    "Bari",
                    12345678901L,
                    LocalTime.of(7, 30),
                    LocalTime.of(20, 0)
            );
            puntoVenditaDao.save(tabaccheria);

            //Creiamo e salviamo un Distributore Automatico
            System.out.println("\n Creazione Distributore Automatico");
            Distributore distributore = new Distributore("Stazione Centrale", "Snc", "Bari", StatoDistributore.ATTIVO);
            puntoVenditaDao.save(distributore);

            //Compriamo un Biglietto singolo presso la tabaccheria
            System.out.println("\n[5/6] Vendita Biglietto...");
            Biglietto biglietto = new Biglietto(LocalDate.now(), 1.50, tabaccheria);
            acquistoViaggioDAO.save(biglietto);

            //Compriamo un Abbonamento Mensile associato alla tessera dell'utente
            System.out.println("\n[6/6] Vendita Abbonamento...");
            Abbonamento abbonamento = new Abbonamento(LocalDate.now(), 35.00, distributore, TipoAbbonamento.MENSILE, tessera);
            acquistoViaggioDAO.save(abbonamento);


            // Test dei metodi delle task e controlli
            System.out.println("\n VERIFICHE E STATISTICHE");

            //Il controllore legge la tessera con l'uuid
            System.out.println("\n CONTROLLORE Sto verificando la tessera...");
            boolean haAbbonamento = tesseraDao.haAbbonamentoValido(tessera.getCodTessera());
            System.out.println("L'utente ha un abbonamento valido oggi? " + (haAbbonamento ? "SÌ, viaggia regolare!" : "NO, multa!"));

            //Conteggio totale dei titoli venduti oggi
            LocalDate oggi = LocalDate.now();
            long totaliEmessi = acquistoViaggioDAO.contaTotaleTitoliEmessi(oggi, oggi);
            System.out.println("\n STATISTICA Titoli totali emessi oggi nel sistema: " + totaliEmessi);

            //Conteggio dei titoli venduti solo dal distributore automatico
            long vendutiDaDistributore = acquistoViaggioDAO.contaTitoliPerPuntoVendita(distributore.getId(), oggi, oggi);
            System.out.println(" STATISTICA Titoli venduti oggi dal Distributore ID (" + distributore.getId() + "): " + vendutiDaDistributore);

        } catch (Exception e) {
            System.err.println("\n ERRORE DURANTE IL TEST: " + e.getMessage());
        } finally {
            // Chiudiamo la connessione per il db
            em.close();
            emf.close();
            System.out.println("\n FINE TEST ZONA 1");
        }
    }
}
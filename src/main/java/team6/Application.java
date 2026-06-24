package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.*;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-buildweek-team6-pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        PuntoVenditaDao pvDao = new PuntoVenditaDao(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        StatoMezzoDAO smDAO = new StatoMezzoDAO(em);
        TrattaDao trattaDao = new TrattaDao(em);
        PercorrenzaDao percDAO = new PercorrenzaDao(em);
        AcquistoViaggioDAO acqDAO = new AcquistoViaggioDAO(em);

        team6.DataSeeder.populateDatabase(em);

        System.out.println("--- Inizio Test dei DAO ---");


        em.close();
        entityManagerFactory.close();
    }
}
package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.*;
import team6.entities.*;
import team6.enums.StatoDiMezzo;
import team6.enums.StatoDistributore;
import team6.enums.TipoAbbonamento;
import team6.enums.tipoVeicolo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

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
package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

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
        //         LOGIN
        //[1] Entra come Utente Semplice
        //[2] Entra come Amministratore
        //[0] Chiudi il programma
        List<String> options=new ArrayList<>(listOf("AREA UTENTE","[1] Compra un Biglietto","[2] Compra un Abbonamento / Rinnova Tessera", "[3] Sali su un mezzo e timbra il biglietto","ACCESSO AMMINISTRATORE","[4] Guarda quanti biglietti totali sono stati venduti","[5] Guarda i biglietti venduti da un singolo negozio/macchinetta","[6] Controllo bigliettaio: verifica se una tessera è valida","[7] Controllo quante timbrature sono state effettuate su un singolo mezzo","[8] Controllo biglietti timbrati dato un periodo di tempo","[9] Manda un mezzo in manutenzione o rimettilo in servizio","[10] Storico di quante manutenzioni ha fatto un singolo Mezzo","[11] Controlla quante volte un mezzo ha percorso una tratta","[12] Calcola il tempo medio di viaggio di una linea"));
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);
        System.out.println("benvenuto, seleziona un numero per i nostri servizi:");
        while (loop==true){
            for(String op: options){System.out.println(op);}
            int response= scanner.nextInt();
            switch (response){
                case 1://caso
                    break;
                case 2://caso
                    break;
                case 3://caso
                    break;
                case 4://caso
                    break;
                case 5://caso
                    break;
                case 6://caso
                    break;
                case 7://caso
                    break;
                case 8://caso
                    break;
                case 9://caso
                    break;
                case 10://caso
                    break;
                case 11://caso
                    break;
                case 12://caso
                    break;
                case 13://caso
                    break;
                case 14://caso
                    break;
                default:
                    System.out.println("risposta non valida.");
            }

        em.close();
        entityManagerFactory.close();
    }

}
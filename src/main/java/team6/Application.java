package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.*;

import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-buildweek-team6-pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();

        UtenteDao UtenteDao = new UtenteDao(em);
        TesseraDao TesseraDao = new TesseraDao(em);
        PuntoVenditaDao PuntoVenditaDao = new PuntoVenditaDao(em);
        MezzoDAO MezzoDAO = new MezzoDAO(em);
        StatoMezzoDAO StatoMezzoDAO = new StatoMezzoDAO(em);
        TrattaDao TrattaDao = new TrattaDao(em);
        PercorrenzaDao PercorrenzaDao = new PercorrenzaDao(em);
        AcquistoViaggioDAO AcquistoViaggioDAO = new AcquistoViaggioDAO(em);

        team6.DataSeeder.populateDatabase(em);

        System.out.println("--- ...INIZIO SCANNER... ---");

        Scanner sc = new Scanner(System.in);

        final String USER = "admin";
        final String PASSWORD = "Admin1234";
        boolean running = true;

        while (running) {
            System.out.println("\n=============== MENU PRINCIPALE ===============");
            System.out.println("[1] Entra come Utente");
            System.out.println("[2] Entra come Admin");
            System.out.println("[0] Chiudi il programma");
            System.out.print("Scelta: ");

            int scelta = sc.nextInt();
            sc.nextLine();

            switch (scelta) {
                case 1 -> {
                    boolean utente = true;
                    while (utente) {
                        System.out.println("\n=============== MENU UTENTE ===============");
                        System.out.println("[1] Compra un Biglietto");
                        System.out.println("[2] Compra un Abbonamento / Rinnova Tessera");
                        System.out.println("[3] Sali su un mezzo e timbra il biglietto");
                        System.out.println("[4] Torna al menu principale");
                        System.out.print("Scelta: ");

                        int scelta1 = sc.nextInt();
                        sc.nextLine();

                        switch (scelta1) {
                            case 1 -> {
                            }

                            case 2 -> {

                            }

                            case 3 -> {

                            }

                            case 4 -> {
                                System.out.println("Torno al menu principale...");
                                utente = false;
                            }

                            default -> System.out.println("!!ATTENZIONE!!Opzione non valida.");
                        }
                    }
                }

                case 2 -> {
                    System.out.print("     Inserire Username: ");
                    String username = sc.nextLine();
                    System.out.print("     Inserire Password: ");
                    String password = sc.nextLine();

                    if (!username.equals(USER) || !password.equals(PASSWORD)) {
                        System.out.println("!!Credenziali errate!!");
                        break;
                    }

                    boolean admin = true;
                    while (admin) {
                        System.out.println("\n============ MENU ADMIN ============");
                        System.out.println("[1] Guarda quanti biglietti totali sono stati venduti");
                        System.out.println("[2] Guarda i biglietti venduti da un punto vendita");
                        System.out.println("[3] Controllo bigliettaio: verifica se una tessera è valida");
                        System.out.println("[4] Controllo quante timbrature sono state effettuate su un singolo mezzo");
                        System.out.println("[5] Controllo biglietti timbrati dato un periodo di tempo");
                        System.out.println("[6] Manda un mezzo in manutenzione o rimettilo in servizio");
                        System.out.println("[7] Storico di quante manutenzioni ha fatto un singolo Mezzo");
                        System.out.println("[8] Controlla quante volte un mezzo ha percorso una tratta");
                        System.out.println("[9] Calcola il tempo medio di viaggio di una linea");
                        System.out.println("[10] Torna al menu principale");
                        System.out.print("Scelta: ");

                        int scelta2 = sc.nextInt();
                        sc.nextLine();

                        switch (scelta2) {
                            case 1 -> {

                            }

                            case 2 -> {

                            }

                            case 3 -> {

                            }

                            case 4 -> {

                            }

                            case 5 -> {

                            }

                            case 6 -> {

                            }

                            case 7 -> {

                            }

                            case 8 -> {
                            }

                            case 9 -> {

                            }

                            case 10 -> {
                                System.out.println("Torno al menu principale...");
                                admin = false;
                            }

                            default -> System.out.println("!!ATTENZIOE!! Opzione non valida.");
                        }
                    }
                }

                case 0 -> {
                    System.out.println("APP OFFLINE...");
                    running = false;
                }

                default -> System.out.println("!!ATTENZIONE!!Opzione non valida.");
            }
        }
        em.close();
        entityManagerFactory.close();

    }
}
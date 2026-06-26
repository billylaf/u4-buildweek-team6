package team6;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team6.dao.*;
import team6.entities.*;
import team6.enums.StatoDistributore;
import team6.enums.TipoAbbonamento;
import team6.exceptions.DistributoreFuoriServizioException;
import team6.exceptions.ElementoNonTrovatoException;
import team6.exceptions.EntityNotFoundException;
import team6.exceptions.TesseraScadutaException;
import team6.utils.QRCodeGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("u4-buildweek-team6-pu");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        team6.DataSeeder.populateDatabase(em);
        Scanner scanner = new Scanner(System.in);

        UtenteDao utenteDao = new UtenteDao(em);
        TesseraDao tesseraDao = new TesseraDao(em);
        PuntoVenditaDao puntoVenditaDao = new PuntoVenditaDao(em);
        AcquistoViaggioDAO acquistoViaggioDAO = new AcquistoViaggioDAO(em);
        StatoMezzoDAO statoMezzoDao = new StatoMezzoDAO(em);
        PercorrenzaDao percorrenzaDao = new PercorrenzaDao(em);
        TrattaDao trattaDao = new TrattaDao(em);

        final String USER = "admin";
        final String PASS = "Admin1234";


        boolean programmaAttivo = true;

        System.out.println("SISTEMA DI GESTIONE TRASPORTI METROPOLITANI");

        while (programmaAttivo) {
            System.out.println("\n=== FASE LOGIN ===");
            System.out.println("[1] Entra come Utente Semplice");
            System.out.println("[2] Entra come Amministratore");
            System.out.println("[0] Chiudi il programma");
            System.out.print("Seleziona il profilo di accesso: ");

            int sceltaLogin = leggiNumeroSicuro(scanner);

            switch (sceltaLogin) {
                case 1:
                    gestisciMenuUtente(scanner, utenteDao, tesseraDao, puntoVenditaDao, acquistoViaggioDAO);
                    break;

                case 2:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    if (!username.equals(USER) || !password.equals(PASS)) {
                        System.out.println("!!Credenziali errate!! ");
                        break;
                    }
                    gestisciMenuAdmin(scanner, acquistoViaggioDAO, tesseraDao, puntoVenditaDao, statoMezzoDao, percorrenzaDao, trattaDao);
                    break;

                case 0:
                    programmaAttivo = false;
                    System.out.println("\nDisattivazione dell'interfaccia in corso. Spegnimento...");
                    break;

                default:
                    System.out.println("Scelta principale errata! Seleziona 1, 2 o 0.");
            }
        }

        scanner.close();
        em.close();
        entityManagerFactory.close();
        System.out.println("=== APPLICAZIONE TERMINATA CON SUCCESSO ===");
    }


    private static void gestisciMenuUtente(Scanner scanner, UtenteDao utenteDao, TesseraDao tesseraDao, PuntoVenditaDao puntoVenditaDao, AcquistoViaggioDAO acquistoViaggioDAO) {
        System.out.println("\n--- REGISTRAZIONE UTENTE IN CORSO ---");
        try {
            System.out.print("Inserisci il tuo Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Inserisci il tuo Cognome: ");
            String cognome = scanner.nextLine().trim();

            System.out.println("Inserisci la tua data di nascita:");
            LocalDate dataNascita = leggiDataSicura(scanner);

            Utente utenteLoggato = new Utente(nome, cognome, dataNascita);
            utenteDao.save(utenteLoggato);

            Tessera tesseraLoggata = new Tessera(LocalDate.now(), utenteLoggato);
            tesseraDao.save(tesseraLoggata);

            System.out.println("\n Profilo creato correttamente nel sistema!");
            System.out.println("Utente: " + utenteLoggato.getNome() + " " + utenteLoggato.getCognome());
            System.out.println("Codice Univoco Tessera (UUID): " + tesseraLoggata.getCodTessera());

            boolean inUtente = true;
            while (inUtente) {
                System.out.println("\n--- ACCESSO UTENTE ---");
                System.out.println("[1] Compra un Biglietto");
                System.out.println("[2] Compra un Abbonamento / Rinnova Tessera");
                System.out.println("[3] Sali su un mezzo e timbra il biglietto");
                System.out.println("[0] Torna al menu principale");
                System.out.print("Seleziona operazione: ");

                int sceltaUtente = leggiNumeroSicuro(scanner);

                try {
                    switch (sceltaUtente) {
                        case 1:
                            azioneCompraBiglietto(scanner, puntoVenditaDao, acquistoViaggioDAO);
                            break;
                        case 2:
                            azioneGestisciAbbonamentoETessera(scanner, puntoVenditaDao, acquistoViaggioDAO, tesseraDao, tesseraLoggata);
                            break;
                        case 3:
                            System.out.println("\n[Validazione Titolo di Viaggio]");
                            System.out.print("Inserisci l'ID del biglietto da timbrare: ");
                            Long idBiglietto = leggiLongSicuro(scanner);
                            System.out.print("Inserisci l'ID del mezzo su cui sei salito: ");
                            Long idMezzo = leggiLongSicuro(scanner);

                            acquistoViaggioDAO.timbraBiglietto(idBiglietto, idMezzo);
                            System.out.println("Biglietto annullato e registrato sul mezzo ID: " + idMezzo);
                            break;
                        case 0:
                            inUtente = false;
                            System.out.println("\nUscita dal menu utente...");
                            break;
                        default:
                            System.out.println(" Opzione non valida! Inserisci un valore compreso tra 0 e 3.");
                    }
                } catch (DistributoreFuoriServizioException | TesseraScadutaException | ElementoNonTrovatoException e) {
                    System.out.println("Errore di Business: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Errore di sistema imprevisto: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Errore bloccante nella registrazione del profilo: " + e.getMessage());
        }
    }

    private static void azioneCompraBiglietto(Scanner scanner, PuntoVenditaDao puntoVenditaDao, AcquistoViaggioDAO acquistoViaggioDAO) {
        System.out.println("\n[Acquisto Biglietto]");
        System.out.print("Inserisci l'ID del punto vendita emettitore: ");
        Long idPuntoB = leggiLongSicuro(scanner);

        PuntoVendita pvB = puntoVenditaDao.findById(idPuntoB);

        Biglietto nuovoBiglietto = new Biglietto(LocalDate.now(), 1.50, pvB);
        acquistoViaggioDAO.save(nuovoBiglietto);
        System.out.println("ID: " + nuovoBiglietto.getId());
        System.out.println("Prezzo: " + nuovoBiglietto.getPrezzo() + "€");
        String qrCode = QRCodeGenerator.generateQRCode(nuovoBiglietto.getCodiceBiglietto().toString());
        System.out.println(qrCode);
    }

    private static void azioneGestisciAbbonamentoETessera(Scanner scanner, PuntoVenditaDao puntoVenditaDao, AcquistoViaggioDAO acquistoViaggioDAO, TesseraDao tesseraDao, Tessera tesseraLoggata) {
        System.out.println("\n[Servizi Abbonamento e Tessera]");
        System.out.println("[1] Acquista un nuovo Abbonamento");
        System.out.println("[2] Rinnova la validità della Tessera corrente");
        System.out.print("Scelta: ");
        int sceltaSottoOpzione = leggiNumeroSicuro(scanner);

        if (sceltaSottoOpzione == 1) {
            System.out.print("Inserisci l'ID del punto vendita: ");
            Long idPuntoA = leggiLongSicuro(scanner);
            PuntoVendita pvA = puntoVenditaDao.findById(idPuntoA);

            System.out.print("Seleziona la tipologia [1 per SETTIMANALE, 2 per MENSILE]: ");
            int tipoScelta = leggiNumeroSicuro(scanner);
            TipoAbbonamento tipo = (tipoScelta == 1) ? TipoAbbonamento.SETTIMANALE : TipoAbbonamento.MENSILE;

            double prezzo = (tipo == TipoAbbonamento.SETTIMANALE) ? 15.00 : 35.00;

            Abbonamento nuovoAbb = new Abbonamento(LocalDate.now(), prezzo, pvA, tipo, tesseraLoggata);
            acquistoViaggioDAO.save(nuovoAbb);

            System.out.println("Abbonamento acquistato con successo al prezzo di " + prezzo + "€!");
            String qrCode = QRCodeGenerator.generateQRCode(nuovoAbb.getCodiceAbbonamento().toString());
            System.out.println(qrCode);

        } else if (sceltaSottoOpzione == 2) {
            tesseraDao.rinnovaTessera(tesseraLoggata.getId());
        } else {
            System.out.println("Opzione non valida.");
        }
    }

    private static void gestisciMenuAdmin(Scanner scanner, AcquistoViaggioDAO acquistoViaggioDAO, TesseraDao tesseraDao, PuntoVenditaDao puntoVenditaDao, StatoMezzoDAO statoMezzoDao, PercorrenzaDao percorrenzaDao, TrattaDao trattaDao) {
        boolean inAdmin = true;
        while (inAdmin) {
            System.out.println("\n--- ACCESSO AMMINISTRATORE ---");
            System.out.println("[1] Guarda quanti biglietti totali sono stati venduti");
            System.out.println("[2] Guarda i biglietti venduti da un singolo negozio/macchinetta");
            System.out.println("[3] Controllo bigliettaio: verifica se una tessera è valida");
            System.out.println("[4] Controllo quante timbrature effettuate su un singolo mezzo");
            System.out.println("[5] Controllo biglietti timbrati dato un periodo");
            System.out.println("[6] Gestione distributori: cambia lo stato di servizio");
            System.out.println("[7] Storico di quante manutenzioni ha fatto un singolo Mezzo");
            System.out.println("[8] Controlla quante volte un mezzo ha percorso una tratta");
            System.out.println("[9] Calcola il tempo medio di viaggio di una linea");
            System.out.println("[0] Torna al menu principale");
            System.out.print("Seleziona operazione amministrativa: ");

            int sceltaAdmin = leggiNumeroSicuro(scanner);

            try {
                switch (sceltaAdmin) {
                    case 1:
                        adminStatisticheTotali(scanner, acquistoViaggioDAO);
                        break;
                    case 2:
                        adminStatistichePuntoVendita(scanner, acquistoViaggioDAO);
                        break;
                    case 3:
                        adminVerificaTessera(scanner, tesseraDao);
                        break;
                    case 4:
                        adminTimbratiInMezzoById(scanner, acquistoViaggioDAO);
                        break;
                    case 5:
                        adminBigliettiTimbratiByPeriodo(scanner, acquistoViaggioDAO);
                        break;
                    case 6:
                        adminModificaStatoDistributore(scanner, puntoVenditaDao);
                        break;
                    case 7:
                        adminStoricoMezzo(scanner, statoMezzoDao);
                        break;
                    case 8:
                        // DAO ZONA 3 - Task: Tenere traccia del numero di volte che un mezzo percorre una tratta
                        adminQuanteVolteMezzoSuTratta(scanner, percorrenzaDao, trattaDao);
                        break;
                    case 9:
                        // DAO ZONA 3 - Task: Calcolare il tempo medio effettivo di percorrenza di una tratta
                        adminTempoMedioTratta(scanner, percorrenzaDao, trattaDao);
                        break;
                    case 0:
                        inAdmin = false;
                        System.out.println("\nRitorno al menu principale...");
                        break;
                    default:
                        System.out.println("Opzione non valida! Inserisci un valore compreso tra 1 e 10.");
                }
            } catch (ElementoNonTrovatoException | EntityNotFoundException e) {
                System.out.println("Nota Informativa: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Errore imprevisto durante l'esecuzione: " + e.getMessage());
            }
        }
    }

    private static void adminStatisticheTotali(Scanner scanner, AcquistoViaggioDAO acquistoViaggioDAO) {
        System.out.println("\n[Statistica Emissioni Totali]");
        System.out.println("Data INIZIO periodo:");
        LocalDate inizioT = leggiDataSicura(scanner);
        System.out.println("Data FINE periodo:");
        LocalDate fineT = leggiDataSicura(scanner);

        long totaleTitoli = acquistoViaggioDAO.contaTotaleTitoliEmessi(inizioT, fineT);
        System.out.println("Titoli totali emessi nell'intervallo indicato: " + totaleTitoli);
    }

    private static void adminStatistichePuntoVendita(Scanner scanner, AcquistoViaggioDAO acquistoViaggioDAO) {
        System.out.println("\n[Statistica Emissioni per Punto Vendita]");
        System.out.print("ID del punto vendita (Negozio/Distributore): ");
        Long idPuntoV = leggiLongSicuro(scanner);
        System.out.println("Data INIZIO periodo:");
        LocalDate inizioP = leggiDataSicura(scanner);
        System.out.println("Data FINE periodo:");
        LocalDate fineP = leggiDataSicura(scanner);

        long totalePunto = acquistoViaggioDAO.contaTitoliPerPuntoVendita(idPuntoV, inizioP, fineP);
        System.out.println("Titoli emessi dal punto vendita specificato: " + totalePunto);
    }

    private static void adminVerificaTessera(Scanner scanner, TesseraDao tesseraDao) {
        System.out.println("\n[Verifica Validità Abbonamento]");
        UUID uuidVerifica = leggiUuidSicuro(scanner);

        boolean abbonamentoValido = tesseraDao.haAbbonamentoValido(uuidVerifica);
        if (abbonamentoValido) {
            System.out.println("Stato: REGOLARE. Trovato un abbonamento in corso di validità.");
        } else {
            System.out.println("Stato: IRREGOLARE. Nessun abbonamento attivo o tessera scaduta!");
        }
    }

    private static void adminModificaStatoDistributore(Scanner scanner, PuntoVenditaDao puntoVenditaDao) {
        System.out.println("\n[Manutenzione Apparati - Gestione Distributori]");
        System.out.print("Inserisci l'ID del distributore automatico: ");
        Long idDistributore = leggiLongSicuro(scanner);

        System.out.println("Imposta stato [1 per ATTIVO, 2 per FUORI_SERVIZIO]: ");
        int sceltaStato = leggiNumeroSicuro(scanner);
        StatoDistributore nuovoStato = (sceltaStato == 1) ? StatoDistributore.ATTIVO : StatoDistributore.FUORI_SERVIZIO;

        puntoVenditaDao.cambiaStatoDistributore(idDistributore, nuovoStato);
        System.out.println("Stato dell'apparato aggiornato sul Database.");
    }

    //Billy
//case 4
    private static void adminTimbratiInMezzoById(Scanner scanner, AcquistoViaggioDAO AcquistoViaggioDAO) {
        System.out.print("ID mezzo: ");

        Long idMezzo = leggiLongSicuro(scanner);

        if (idMezzo == null) {
            System.out.println("ID nonn presente nel DATABSE");
            return;
        }

        long timbrature = AcquistoViaggioDAO.countBigliettiTimbrati(idMezzo);
        System.out.println("Timbrature totali sul mezzo " + idMezzo + ": " + timbrature);
    }

    //case 5
    private static void adminBigliettiTimbratiByPeriodo(Scanner scanner, AcquistoViaggioDAO acquistoViaggioDAO) {
        System.out.println("\n[Biglietti Timbrati per Periodo]");

        System.out.println("Data INIZIO periodo:");
        LocalDate inizioP = leggiDataSicura(scanner);

        System.out.println("Data FINE periodo:");
        LocalDate fineP = leggiDataSicura(scanner);

        long timbrati = acquistoViaggioDAO.countBigliettiTimbratiByPeriodo(inizioP, fineP);
        if (timbrati == 0) {
            System.out.println("Im questo periodo non ci sono biglietti timbrati");
            return;
        }
        System.out.println("Biglietti timbrati nel periodo specificato: " + timbrati);
    }

    //case 7
    private static void adminStoricoMezzo(Scanner scanner, StatoMezzoDAO statoMezzoDAO) {
        System.out.println("\n[Storico Stati di un Mezzo]");

        System.out.print("ID mezzo: ");
        Long idMezzo = leggiLongSicuro(scanner);

        List<StatoMezzo> storico = statoMezzoDAO.findStoricoByMezzoId(idMezzo);

        if (storico.isEmpty()) {
            System.out.println("Nessuno stato trovato per il mezzo " + idMezzo);
            return;
        }

        System.out.println("Storico stati del mezzo " + idMezzo + ":");
        storico.forEach(System.out::println);
    }
    //Fine Billy


    //Davide

    private static void adminQuanteVolteMezzoSuTratta(Scanner scanner, PercorrenzaDao percorrenzaDao, TrattaDao trattaDao) {
        System.out.println("\n[Statistica Mezzo su Tratta]");
        System.out.print("Inserisci l'ID del Mezzo: ");
        Long idMezzo = leggiLongSicuro(scanner);
        System.out.print("Inserisci l'ID della Tratta: ");
        Long idTratta = leggiLongSicuro(scanner);

        Tratta tratta = trattaDao.findById(idTratta);

        long volte = percorrenzaDao.contaVolteMezzoHaPercorsoTratta(idMezzo, idTratta);
        System.out.println("\nIl mezzo con ID " + idMezzo + " ha percorso la tratta " + idTratta + " per " + volte + " volte.");
    }

    private static void adminTempoMedioTratta(Scanner scanner, PercorrenzaDao percorrenzaDao, TrattaDao trattaDao) {
        System.out.println("\n[Statistica Tempo Medio della Linea]");
        System.out.print("Inserisci l'ID della Tratta da analizzare: ");
        Long idTratta = leggiLongSicuro(scanner);

        Tratta tratta = trattaDao.findById(idTratta);
        Double tempoMedio = percorrenzaDao.calcolaTempoMedioEffettivo(idTratta);
        System.out.println("Il tempo medio effettivo di percorrenza per la tratta " + idTratta + " è di " + String.format("%.2f", tempoMedio) + " minuti.");
    }

    //Fine Davide

    //Metodi di controllo per gli input
    private static int leggiNumeroSicuro(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Input non conforme. Inserisci un valore numerico intero: ");
            }
        }
    }

    private static Long leggiLongSicuro(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.print("Identificativo non valido. Inserisci un valore numerico (ID): ");
            }
        }
    }

    private static LocalDate leggiDataSicura(Scanner scanner) {
        while (true) {
            try {
                System.out.print("   Anno (AAAA): ");
                int anno = leggiNumeroSicuro(scanner);
                System.out.print("   Mese (1-12): ");
                int mese = leggiNumeroSicuro(scanner);
                System.out.print("   Giorno (1-31): ");
                int giorno = leggiNumeroSicuro(scanner);

                return LocalDate.of(anno, mese, giorno);
            } catch (java.time.DateTimeException e) {
                System.out.println(" Combinazione di calendario errata (es. giorno non esistente). Riprova.");
            }
        }
    }

    private static UUID leggiUuidSicuro(Scanner scanner) {
        while (true) {
            System.out.print("Inserisci il codice identificativo UUID della tessera: ");
            String input = scanner.nextLine().trim();
            try {
                return UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println(" Formato stringa UUID non conforme allo standard del sistema. Riprova.");
            }
        }
    }
}
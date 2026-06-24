package team6;

import jakarta.persistence.EntityManager;
import team6.entities.*;
import team6.enums.StatoDiMezzo;
import team6.enums.StatoDistributore;
import team6.enums.TipoAbbonamento;
import team6.enums.tipoVeicolo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataSeeder {

    public static void populateDatabase(EntityManager em) {
        System.out.println("=== INIZIO POPOLAMENTO DATABASE ===");

        // ============================================================
        // 1. UTENTI
        // ============================================================
        em.getTransaction().begin();
        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 3, 15));
        Utente u2 = new Utente("Lucia", "Bianchi", LocalDate.of(1985, 7, 22));
        Utente u3 = new Utente("Giorgio", "Verdi", LocalDate.of(2000, 11, 5));
        Utente u4 = new Utente("Francesca", "Esposito", LocalDate.of(1995, 4, 18));
        Utente u5 = new Utente("Davide", "Ricci", LocalDate.of(1978, 9, 30));
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);
        em.persist(u5);
        em.getTransaction().commit();

        // ============================================================
        // 2. TESSERE
        // ============================================================
        em.getTransaction().begin();
        Tessera t1 = new Tessera(LocalDate.of(2025, 1, 10), u1);
        Tessera t2 = new Tessera(LocalDate.of(2025, 2, 1), u2);
        Tessera t3 = new Tessera(LocalDate.of(2025, 3, 15), u3);
        Tessera t4 = new Tessera(LocalDate.of(2025, 6, 1), u4);
        Tessera t5 = new Tessera(LocalDate.of(2025, 9, 1), u5);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.persist(t5);
        em.getTransaction().commit();


        // ============================================================
        // 3. DISTRIBUTORI
        // ============================================================
        em.getTransaction().begin();
        Distributore d1 = new Distributore("Via Roma", "1", "Milano", StatoDistributore.ATTIVO);
        Distributore d2 = new Distributore("Piazza Duomo", "5", "Milano", StatoDistributore.FUORI_SERVIZIO);
        Distributore d3 = new Distributore("Corso Buenos Aires", "12", "Milano", StatoDistributore.IN_MANUTENZIONE);
        Distributore d4 = new Distributore("Via Dante", "7", "Milano", StatoDistributore.ATTIVO);
        Distributore d5 = new Distributore("Viale Monza", "33", "Milano", StatoDistributore.ATTIVO);
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);
        em.persist(d5);
        em.getTransaction().commit(); // <-- Mancava il commit qui nel tuo vecchio codice!


        // ============================================================
        // 4. RIVENDITORI
        // ============================================================
        em.getTransaction().begin();
        Rivenditore r1 = new Rivenditore("Tabacchi Centrale", "Via Torino", "3", "Milano", 12345678901L, LocalTime.of(8, 0), LocalTime.of(20, 0));
        Rivenditore r2 = new Rivenditore("Edicola Stazione", "Via Sammartini", "88", "Milano", 98765432100L, LocalTime.of(6, 30), LocalTime.of(22, 0));
        Rivenditore r3 = new Rivenditore("Bar Sport", "Corso Lodi", "14", "Milano", 11122233344L, LocalTime.of(7, 0), LocalTime.of(21, 0));
        Rivenditore r4 = new Rivenditore("Tabacchi Nord", "Via Padova", "56", "Milano", 55566677788L, LocalTime.of(8, 30), LocalTime.of(19, 30));
        Rivenditore r5 = new Rivenditore("Giornalaio Express", "Piazza Loreto", "2", "Milano", 99988877766L, LocalTime.of(5, 30), LocalTime.of(23, 0));
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.persist(r5);
        em.getTransaction().commit();


        // ============================================================
        // 5. MEZZI
        // ============================================================
        em.getTransaction().begin();
        Mezzo m1 = new Mezzo(80, tipoVeicolo.BUS);
        Mezzo m2 = new Mezzo(120, tipoVeicolo.TRAM);
        Mezzo m3 = new Mezzo(60, tipoVeicolo.BUS);
        Mezzo m4 = new Mezzo(100, tipoVeicolo.TRAM);
        Mezzo m5 = new Mezzo(75, tipoVeicolo.BUS);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
        em.persist(m4);
        em.persist(m5);
        em.getTransaction().commit();


        // ============================================================
        // 6. STATO MEZZI
        // ============================================================
        em.getTransaction().begin();
        em.persist(new StatoMezzo(StatoDiMezzo.IN_SERVIZIO, LocalDate.of(2025, 1, 1), "Avvio stagione", null, m1));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_MANUTENZIONE, LocalDate.of(2025, 3, 10), "Sostituzione freni anteriori", LocalDate.of(2025, 3, 15), m2));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_SERVIZIO, LocalDate.of(2025, 3, 16), "Rientrato in servizio post manutenzione", null, m2));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_MANUTENZIONE, LocalDate.of(2025, 5, 1), "Guasto al motore", LocalDate.of(2025, 5, 8), m3));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_MANUTENZIONE, LocalDate.of(2025, 6, 5), "Revisione programmata annuale", LocalDate.of(2025, 6, 12), m4));
        em.getTransaction().commit();


        // ============================================================
        // 7. TRATTE
        // ============================================================
        em.getTransaction().begin();
        Tratta tr1 = new Tratta(25, "Capolinea Nord", "Piazza Duomo");
        Tratta tr2 = new Tratta(40, "Capolinea Est", "Loreto");
        Tratta tr3 = new Tratta(15, "Capolinea Ovest", "Cadorna");
        Tratta tr4 = new Tratta(30, "Capolinea Sud", "Famagosta");
        Tratta tr5 = new Tratta(20, "Capolinea Centro", "Centrale FS");
        em.persist(tr1);
        em.persist(tr2);
        em.persist(tr3);
        em.persist(tr4);
        em.persist(tr5);
        em.getTransaction().commit();


        // ============================================================
        // 8. PERCORRENZE
        // ============================================================
        em.getTransaction().begin();
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-01 08:00:00"), Timestamp.valueOf("2025-06-01 08:28:00"), m1, tr1));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-01 09:00:00"), Timestamp.valueOf("2025-06-01 09:45:00"), m2, tr2));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-02 07:30:00"), Timestamp.valueOf("2025-06-02 07:52:00"), m1, tr1));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-02 10:00:00"), Timestamp.valueOf("2025-06-02 10:14:00"), m3, tr3));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-03 11:00:00"), Timestamp.valueOf("2025-06-03 11:33:00"), m4, tr4));
        em.getTransaction().commit();


        // ============================================================
        // 9. BIGLIETTI
        // ============================================================
        em.getTransaction().begin();
        Biglietto b1 = new Biglietto(LocalDate.of(2025, 6, 1), 1.50, d1);

        Biglietto b2 = new Biglietto(LocalDate.of(2025, 6, 1), 1.50, d1);
        b2.setDataValidazione(LocalDate.of(2025, 6, 1));
        b2.setMezzo(m1);

        Biglietto b3 = new Biglietto(LocalDate.of(2025, 6, 2), 1.50, r1);
        b3.setDataValidazione(LocalDate.of(2025, 6, 2));
        b3.setMezzo(m2);

        Biglietto b4 = new Biglietto(LocalDate.of(2025, 6, 3), 1.50, r2);

        Biglietto b5 = new Biglietto(LocalDate.of(2025, 6, 3), 1.50, d4);
        b5.setDataValidazione(LocalDate.of(2025, 6, 3));
        b5.setMezzo(m1);

        em.persist(b1);
        em.persist(b2);
        em.persist(b3);
        em.persist(b4);
        em.persist(b5);
        em.getTransaction().commit();


        // ============================================================
        // 10. ABBONAMENTI
        // ============================================================
        em.getTransaction().begin();
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 20), 10.00, r1, TipoAbbonamento.SETTIMANALE, t3));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 1), 35.00, d1, TipoAbbonamento.MENSILE, t4));
        em.persist(new Abbonamento(LocalDate.of(2025, 5, 15), 35.00, d4, TipoAbbonamento.MENSILE, t5));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 17), 10.00, r3, TipoAbbonamento.SETTIMANALE, t3));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 5), 35.00, r5, TipoAbbonamento.MENSILE, t5));
        em.getTransaction().commit();


        System.out.println("===DATI INSERITI CON SUCCESSO NEL DATABASE ===\n");
    }
}
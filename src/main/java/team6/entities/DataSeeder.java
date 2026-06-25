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
        System.out.println("=== INIZIO POPOLAMENTO DATABASE...... ===");

        // ============================================================
        // 1. UTENTI
        // ============================================================
        em.getTransaction().begin();
        Utente u1 = new Utente("Mario", "Rossi", LocalDate.of(1990, 3, 15));
        Utente u2 = new Utente("Lucia", "Bianchi", LocalDate.of(1985, 7, 22));
        Utente u3 = new Utente("Giorgio", "Verdi", LocalDate.of(2000, 11, 5));
        Utente u4 = new Utente("Francesca", "Esposito", LocalDate.of(1995, 4, 18));
        Utente u5 = new Utente("Davide", "Ricci", LocalDate.of(1978, 9, 30));
        Utente u6 = new Utente("Elena", "Moretti", LocalDate.of(1992, 6, 12));
        Utente u7 = new Utente("Marco", "Ferrari", LocalDate.of(1988, 2, 28));
        Utente u8 = new Utente("Silvia", "Russo", LocalDate.of(2001, 12, 3));
        Utente u9 = new Utente("Paolo", "Conti", LocalDate.of(1975, 5, 19));
        Utente u10 = new Utente("Anna", "Galli", LocalDate.of(1997, 8, 25));
        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);
        em.persist(u5);
        em.persist(u6);
        em.persist(u7);
        em.persist(u8);
        em.persist(u9);
        em.persist(u10);
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
        Tessera t6 = new Tessera(LocalDate.of(2025, 1, 20), u6);
        Tessera t7 = new Tessera(LocalDate.of(2025, 4, 5), u7);
        Tessera t8 = new Tessera(LocalDate.of(2025, 7, 10), u8);
        Tessera t9 = new Tessera(LocalDate.of(2025, 10, 1), u9);
        Tessera t10 = new Tessera(LocalDate.of(2025, 11, 15), u10);
        em.persist(t1);
        em.persist(t2);
        em.persist(t3);
        em.persist(t4);
        em.persist(t5);
        em.persist(t6);
        em.persist(t7);
        em.persist(t8);
        em.persist(t9);
        em.persist(t10);
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
        Distributore d6 = new Distributore("Via Garibaldi", "15", "Milano", StatoDistributore.ATTIVO);
        Distributore d7 = new Distributore("Piazza San Babila", "2", "Milano", StatoDistributore.FUORI_SERVIZIO);
        Distributore d8 = new Distributore("Via Manzoni", "28", "Milano", StatoDistributore.ATTIVO);
        Distributore d9 = new Distributore("Corso Vittorio Emanuele", "45", "Milano", StatoDistributore.IN_MANUTENZIONE);
        Distributore d10 = new Distributore("Via Montenapoleone", "10", "Milano", StatoDistributore.ATTIVO);
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);
        em.persist(d5);
        em.persist(d6);
        em.persist(d7);
        em.persist(d8);
        em.persist(d9);
        em.persist(d10);
        em.getTransaction().commit();

        // ============================================================
        // 4. RIVENDITORI
        // ============================================================
        em.getTransaction().begin();
        Rivenditore r1 = new Rivenditore("Tabacchi Centrale", "Via Torino", "3", "Milano", 12345678901L, LocalTime.of(8, 0), LocalTime.of(20, 0));
        Rivenditore r2 = new Rivenditore("Edicola Stazione", "Via Sammartini", "88", "Milano", 98765432100L, LocalTime.of(6, 30), LocalTime.of(22, 0));
        Rivenditore r3 = new Rivenditore("Bar Sport", "Corso Lodi", "14", "Milano", 11122233344L, LocalTime.of(7, 0), LocalTime.of(21, 0));
        Rivenditore r4 = new Rivenditore("Tabacchi Nord", "Via Padova", "56", "Milano", 55566677788L, LocalTime.of(8, 30), LocalTime.of(19, 30));
        Rivenditore r5 = new Rivenditore("Giornalaio Express", "Piazza Loreto", "2", "Milano", 99988877766L, LocalTime.of(5, 30), LocalTime.of(23, 0));
        Rivenditore r6 = new Rivenditore("Tabacchi Porta Venezia", "Via Palestro", "7", "Milano", 12121212121L, LocalTime.of(7, 30), LocalTime.of(20, 30));
        Rivenditore r7 = new Rivenditore("Edicola Duomo", "Piazza Duomo", "12", "Milano", 34343434343L, LocalTime.of(6, 0), LocalTime.of(21, 0));
        Rivenditore r8 = new Rivenditore("Bar Centrale", "Corso Italia", "22", "Milano", 56565656565L, LocalTime.of(8, 0), LocalTime.of(22, 0));
        Rivenditore r9 = new Rivenditore("Tabacchi Loreto", "Piazza Loreto", "8", "Milano", 78787878787L, LocalTime.of(7, 0), LocalTime.of(19, 0));
        Rivenditore r10 = new Rivenditore("Giornalaio Nord", "Via Farini", "45", "Milano", 90909090909L, LocalTime.of(5, 0), LocalTime.of(23, 30));
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        em.persist(r4);
        em.persist(r5);
        em.persist(r6);
        em.persist(r7);
        em.persist(r8);
        em.persist(r9);
        em.persist(r10);
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
        Mezzo m6 = new Mezzo(90, tipoVeicolo.BUS);
        Mezzo m7 = new Mezzo(110, tipoVeicolo.TRAM);
        Mezzo m8 = new Mezzo(65, tipoVeicolo.BUS);
        Mezzo m9 = new Mezzo(95, tipoVeicolo.TRAM);
        Mezzo m10 = new Mezzo(85, tipoVeicolo.BUS);
        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
        em.persist(m4);
        em.persist(m5);
        em.persist(m6);
        em.persist(m7);
        em.persist(m8);
        em.persist(m9);
        em.persist(m10);
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
        em.persist(new StatoMezzo(StatoDiMezzo.IN_SERVIZIO, LocalDate.of(2025, 7, 1), "Nuovo mezzo in servizio", null, m6));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_MANUTENZIONE, LocalDate.of(2025, 8, 15), "Sostituzione batterie", LocalDate.of(2025, 8, 20), m7));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_SERVIZIO, LocalDate.of(2025, 9, 1), "Rientro in servizio", null, m7));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_MANUTENZIONE, LocalDate.of(2025, 10, 10), "Tagliando programmato", LocalDate.of(2025, 10, 15), m9));
        em.persist(new StatoMezzo(StatoDiMezzo.IN_SERVIZIO, LocalDate.of(2025, 11, 1), "Mezzo operativo", null, m10));
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
        Tratta tr6 = new Tratta(35, "Capolinea Nord-Est", "Porta Venezia");
        Tratta tr7 = new Tratta(28, "Capolinea Sud-Ovest", "Navigli");
        Tratta tr8 = new Tratta(22, "Capolinea Nord-Ovest", "Garibaldi");
        Tratta tr9 = new Tratta(18, "Capolinea Sud-Est", "Rogoredo");
        Tratta tr10 = new Tratta(32, "Capolinea Ovest-Nord", "San Siro");
        em.persist(tr1);
        em.persist(tr2);
        em.persist(tr3);
        em.persist(tr4);
        em.persist(tr5);
        em.persist(tr6);
        em.persist(tr7);
        em.persist(tr8);
        em.persist(tr9);
        em.persist(tr10);
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
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-04 08:30:00"), Timestamp.valueOf("2025-06-04 08:58:00"), m5, tr5));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-05 09:15:00"), Timestamp.valueOf("2025-06-05 09:50:00"), m6, tr6));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-06 07:45:00"), Timestamp.valueOf("2025-06-06 08:13:00"), m7, tr7));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-07 10:30:00"), Timestamp.valueOf("2025-06-07 10:52:00"), m8, tr8));
        em.persist(new Percorrenza(Timestamp.valueOf("2025-06-08 11:15:00"), Timestamp.valueOf("2025-06-08 11:47:00"), m9, tr9));
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

        Biglietto b6 = new Biglietto(LocalDate.of(2025, 6, 4), 1.50, r3);
        b6.setDataValidazione(LocalDate.of(2025, 6, 4));
        b6.setMezzo(m3);

        Biglietto b7 = new Biglietto(LocalDate.of(2025, 6, 5), 1.50, d6);

        Biglietto b8 = new Biglietto(LocalDate.of(2025, 6, 6), 1.50, r5);
        b8.setDataValidazione(LocalDate.of(2025, 6, 6));
        b8.setMezzo(m4);

        Biglietto b9 = new Biglietto(LocalDate.of(2025, 6, 7), 1.50, d8);

        Biglietto b10 = new Biglietto(LocalDate.of(2025, 6, 8), 1.50, r7);
        b10.setDataValidazione(LocalDate.of(2025, 6, 8));
        b10.setMezzo(m5);

        em.persist(b1);
        em.persist(b2);
        em.persist(b3);
        em.persist(b4);
        em.persist(b5);
        em.persist(b6);
        em.persist(b7);
        em.persist(b8);
        em.persist(b9);
        em.persist(b10);
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
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 10), 35.00, d6, TipoAbbonamento.MENSILE, t6));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 12), 10.00, r7, TipoAbbonamento.SETTIMANALE, t7));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 15), 35.00, d8, TipoAbbonamento.MENSILE, t8));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 18), 10.00, r9, TipoAbbonamento.SETTIMANALE, t9));
        em.persist(new Abbonamento(LocalDate.of(2025, 6, 22), 35.00, d10, TipoAbbonamento.MENSILE, t10));
        em.getTransaction().commit();

        System.out.println("=== DATI INSERITI CON SUCCESSO NEL DATABASE ===\n");
    }
}
package team6;

import org.junit.jupiter.api.Test;
import team6.entities.*;
import team6.enums.StatoDistributore;
import team6.enums.TipoAbbonamento;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TransportTest {

    // ========================
    // TESSERA
    // ========================

    @Test
    public void tesseraValida_nonScaduta() {
        Utente u = new Utente("Mario", "Rossi", LocalDate.of(1990, 1, 1));
        Tessera tessera = new Tessera(LocalDate.now(), u);
        assertFalse(tessera.isScaduta());
    }

    @Test
    public void tesseraScaduta_isScadutaTrue() {
        Utente u = new Utente("Mario", "Rossi", LocalDate.of(1990, 1, 1));
        Tessera tessera = new Tessera(LocalDate.of(2020, 1, 1), u);
        assertTrue(tessera.isScaduta());
    }

    @Test
    public void tesseraDataScadenza_calcolataCorrettamente() {
        Utente u = new Utente("Mario", "Rossi", LocalDate.of(1990, 1, 1));
        LocalDate emissione = LocalDate.of(2026, 1, 1);
        Tessera tessera = new Tessera(emissione, u);
        assertEquals(LocalDate.of(2027, 1, 1), tessera.getDataScadenza());
    }

    // ========================
    // BIGLIETTO
    // ========================

    @Test
    public void biglietto_nuovoNonVidimato() {
        Biglietto b = new Biglietto(LocalDate.now(), 1.50, null);
        assertFalse(b.isVidimato());
    }

    @Test
    public void biglietto_dopoTimbratura_isVidimato() {
        Biglietto b = new Biglietto(LocalDate.now(), 1.50, null);
        b.setDataValidazione(LocalDate.now());
        assertTrue(b.isVidimato());
    }

    // ========================
    // ABBONAMENTO
    // ========================

    @Test
    public void abbonamentoSettimanale_scadenzaCorretta() {
        Utente u = new Utente("Lucia", "Bianchi", LocalDate.of(1985, 7, 22));
        Tessera t = new Tessera(LocalDate.now(), u);
        LocalDate acquisto = LocalDate.of(2026, 6, 1);

        Abbonamento abb = new Abbonamento(acquisto, 10.0, null, TipoAbbonamento.SETTIMANALE, t);

        assertEquals(LocalDate.of(2026, 6, 8), abb.getDataScadenza()); // +7 giorni
    }

    @Test
    public void abbonamentoMensile_scadenzaCorretta() {
        Utente u = new Utente("Lucia", "Bianchi", LocalDate.of(1985, 7, 22));
        Tessera t = new Tessera(LocalDate.now(), u);
        LocalDate acquisto = LocalDate.of(2026, 6, 1);

        Abbonamento abb = new Abbonamento(acquisto, 35.0, null, TipoAbbonamento.MENSILE, t);

        assertEquals(LocalDate.of(2026, 7, 1), abb.getDataScadenza()); // +1 mese
    }

    // ========================
    // PERCORRENZA
    // ========================

    @Test
    public void percorrenzaEffettiva_calcolataInMinuti() {
        Percorrenza p = new Percorrenza(
                java.sql.Timestamp.valueOf("2026-06-01 08:00:00"),
                java.sql.Timestamp.valueOf("2026-06-01 08:28:00"),
                null, null
        );
        assertEquals(28, p.getPercorrenzaEffettiva());
    }

    // ========================
    // DISTRIBUTORE
    // ========================

    @Test
    public void distributore_statoFuoriServizio() {
        Distributore d = new Distributore("Via Roma", "1", "Milano", StatoDistributore.FUORI_SERVIZIO);
        assertEquals(StatoDistributore.FUORI_SERVIZIO, d.getStato());
    }

    @Test
    public void distributore_cambioStato() {
        Distributore d = new Distributore("Via Roma", "1", "Milano", StatoDistributore.ATTIVO);
        d.setStato(StatoDistributore.IN_MANUTENZIONE);
        assertEquals(StatoDistributore.IN_MANUTENZIONE, d.getStato());
    }
}

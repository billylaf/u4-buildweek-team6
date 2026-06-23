package team6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends AcquistoViaggio {

    private LocalDate data_validazione;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, PuntoVendita puntoVendita, LocalDate data_validazione) {
        super(dataEmissione, puntoVendita);
        this.data_validazione = data_validazione;// quando lo compro è nuovo quindi non validato/obliterato
    }

    public LocalDate getData_validazione() {
        return data_validazione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "data_validazione=" + data_validazione +
                ", mezzo=" + mezzo +
                '}';
    }
}

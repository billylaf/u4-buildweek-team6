package team6.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "biglietti")
public class Biglietto extends AcquistoViaggio {
    private boolean vidimato; // true o false se è obliterato/validato


    @OneToMany(mappedBy = "biglietto", cascade = CascadeType.ALL)
    private List<Timbratura> timbrature;

    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, PuntoVendita puntoVendita) {
        super(dataEmissione, puntoVendita);
        this.vidimato = false; // quando lo compro è nuovo quindi non validato/obliterato

    }

    public boolean isVidimato() {
        return vidimato;
    }

    public void setVidimato(boolean vidimato) {
        this.vidimato = vidimato;
    }
}

package team6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "biglietto")
public class Biglietto extends AcquistoViaggio{
    private boolean vidimato; // true o false se è obliterato/validato
    private UUID codiceUnivoco;

    public Biglietto(){}

    public Biglietto(LocalDate dataEmissione, PuntoVendita puntoVendita){
        super(dataEmissione, puntoVendita);
        this.vidimato=false; // quando lo compro è nuovo quindi non validato/obliterato
        this.codiceUnivoco = UUID.randomUUID();
    }

    public boolean isVidimato() {
        return vidimato;
    }
    public void setVidimato(boolean vidimato) {
        this.vidimato = vidimato;
    }
}

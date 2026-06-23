package team6.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends AcquistoViaggio {

    @Column(name = "data_validazione")
    private LocalDate dataValidazione;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    public Biglietto() {}

    //Quando compri il biglietto, non è ancora timbrato quindi data e mezzo sono null
    public Biglietto(LocalDate dataAcquisto, double prezzo, PuntoVendita puntoVendita) {
        super(dataAcquisto, prezzo, puntoVendita);
        this.dataValidazione = null;
        this.mezzo = null;
    }

    //Metodo per sapere se è stato usato
    public boolean isVidimato() {
        return this.dataValidazione != null;
    }

    public LocalDate getDataValidazione() {
        return dataValidazione;
    }

    public void setDataValidazione(LocalDate dataValidazione) {
        this.dataValidazione = dataValidazione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "dataValidazione=" + dataValidazione +
                ", mezzo=" + mezzo +
                '}';
    }
}

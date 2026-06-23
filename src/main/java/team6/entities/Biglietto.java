package team6.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
public class Biglietto extends AcquistoViaggio {

    @Column(name = "data_validazione")
    private LocalDate dataValidazione; // Se è NULL il biglietto è nuovo. Se c'è una data, significa che è timbrato.

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo; // Il bus o tram su cui viene timbrato il biglietto


    public Biglietto() {}


    public Biglietto(LocalDate dataAcquisto, double prezzo, PuntoVendita puntoVendita) {
        super(dataAcquisto, prezzo, puntoVendita); // Passa i dati alla classe papà (AcquistoViaggio)
        this.dataValidazione = null; // All'inizio è nuovo, quindi non ha una data di timbratura
        this.mezzo = null;           // All'inizio non è ancora salito su nessun mezzo
    }

    //Metodo per capire se il biglietto è stato già usato o no
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
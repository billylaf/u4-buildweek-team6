package team6.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;
@Entity
@Table(name = "acquisto_viaggio")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AcquistoViaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_acquisto")
    private LocalDate dataAcquisto;

    private double prezzo;

    @ManyToOne
    @JoinColumn(name = "id_punto_vendita")
    private PuntoVendita puntoVendita; // serve per tracciare dov'è stato emesso l'abbonamento

    public AcquistoViaggio() {}

    public AcquistoViaggio(LocalDate dataAcquisto, double prezzo, PuntoVendita puntoVendita) {
        this.dataAcquisto = dataAcquisto;
        this.prezzo = prezzo;
        this.puntoVendita = puntoVendita;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    @Override
    public String toString() {
        return "AcquistoViaggio{" +
                "id=" + id +
                ", dataAcquisto=" + dataAcquisto +
                ", prezzo=" + prezzo +
                ", puntoVendita=" + puntoVendita +
                '}';
    }
}

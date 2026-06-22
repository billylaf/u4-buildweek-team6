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

    @Column(name = "codice_univoco", unique = true, nullable = false)
    private UUID codiceUnivoco;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @ManyToOne
    @JoinColumn(name = "id_punto_vendita")
    private PuntoVendita puntoVendita; // serve per tracciare dov'è stato emesso l'abbonamento

    public AcquistoViaggio() {}

    public AcquistoViaggio(LocalDate dataEmissione, PuntoVendita puntoVendita) {
        this.codiceUnivoco = UUID.randomUUID(); // metodo della classe uuid per generare random il codice
        this.dataEmissione = dataEmissione;
        this.puntoVendita = puntoVendita;
    }

    public Long getId() {
        return id;
    }
    public UUID getCodiceUnivoco() {
        return codiceUnivoco;
    }
    public LocalDate getDataEmissione() {
        return dataEmissione;
    }
    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    @Override
    public String toString() {
        return "AcquistoViaggio{" +
                "id=" + id +
                ", codiceUnivoco=" + codiceUnivoco +
                ", dataEmissione=" + dataEmissione +
                ", puntoVendita=" + puntoVendita +
                '}';
    }
}

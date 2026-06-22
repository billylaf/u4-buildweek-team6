package team6.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_tessera", unique = true, nullable = false)
    private UUID codTessera;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name="data_scadenza")
    private LocalDate dataScadenza;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Tessera(){}

    public Tessera(LocalDate dataEmissione, Utente utente){
        this.codTessera=UUID.randomUUID(); // utilizzo il metodo della classe uuid per generarlo randomicamente
        this.dataEmissione=dataEmissione;
        this.dataScadenza=dataEmissione.plusYears(1); // aggiungo con il metodo plusyear 1 anno all'emissione
        this.utente=utente;
    }

    public Long getId() {
        return id;
    }

    public UUID getCodTessera() {
        return codTessera;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", codTessera=" + codTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza + '}';
    }
}

package team6.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "punto_vendita")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String via;
    private String civico;
    private String citta;

    public PuntoVendita(){};

    public PuntoVendita(String via, String civico, String citta) {
        this.via = via;
        this.civico = civico;
        this.citta = citta;

}

    public Long getId() {
        return id;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
}

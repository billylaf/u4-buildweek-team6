package team6.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "punto_vendita")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoVendita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String indirizzo;

    public PuntoVendita(){};

    public PuntoVendita(String indirizzo){
        this.indirizzo=indirizzo;
    }
    public Long getId() {
        return id;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}

package team6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "rivenditore")
public class Rivenditore extends PuntoVendita{
    private String nomeNegozio;
    private Long pIva;
    private java.time.LocalTime orarioApertura;
    private java.time.LocalTime orarioChiusura;

    public Rivenditore(){}

    public Rivenditore(String nomeNegozio,String via, String civico, String citta, Long pIva, java.time.LocalTime orarioApertura, java.time.LocalTime orarioChiusura) {
        super(via, civico, citta);
        this.nomeNegozio=nomeNegozio;
        this.pIva = pIva;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }
    public String getNomeNegozio() {
        return nomeNegozio;
    }
    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    public LocalTime getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "nomeNegozio='" + nomeNegozio + '\'' +
                ", pIva=" + pIva +
                ", orarioApertura=" + orarioApertura +
                ", orarioChiusura=" + orarioChiusura +
                '}';
    }
}

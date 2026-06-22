package team6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rivenditore")
public class Rivenditore extends PuntoVendita{
    private String nomeNegozio;

    public Rivenditore(){}

    public Rivenditore(String indirizzo, String nomeNegozio){
        super(indirizzo);
        this.nomeNegozio=nomeNegozio;
    }
    public String getNomeNegozio() {
        return nomeNegozio;
    }
    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "nomeNegozio='" + nomeNegozio + '\'' +
                '}';
    }
}

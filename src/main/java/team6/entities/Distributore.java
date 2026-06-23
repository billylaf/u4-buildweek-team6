package team6.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import team6.enums.StatoDistributore;

@Entity
@Table(name = "distributore")
public class Distributore extends PuntoVendita{
    @Enumerated(EnumType.STRING)
    private StatoDistributore stato;


    public Distributore(){}

    public Distributore(String via, String civico, String citta, StatoDistributore stato) {
        super(via, civico, citta);
        this.stato = stato;
    }

    public StatoDistributore getStato() {
        return stato;
    }
    public void setStato(StatoDistributore stato) {
        this.stato = stato;
    }


    @Override
    public String toString() {
        return "Distributore{" +
                "stato=" + stato +
                '}';
    }
}

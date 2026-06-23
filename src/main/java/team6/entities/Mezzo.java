package team6.entities;


import jakarta.persistence.*;
import team6.enums.tipoVeicolo;

import java.util.List;

@Entity
@Table(name = "mezzi")
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int capienza;

    private tipoVeicolo veicolo;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<StatoMezzo> stato_mezzi;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Biglietto> biglietti;

    public Mezzo() {

    }

    public Mezzo(int capienza, tipoVeicolo veicolo) {
        this.capienza = capienza;
        this.veicolo = veicolo;
    }

    public long getId() {
        return id;
    }

    public int getCapienza() {
        return capienza;
    }

    public tipoVeicolo getVeicolo() {
        return veicolo;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", veicolo=" + veicolo +
                '}';
    }
}

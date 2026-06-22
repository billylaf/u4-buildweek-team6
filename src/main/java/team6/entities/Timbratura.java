package team6.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "timbrature")
public class Timbratura {
    LocalDate data_timbratura;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_biglietto", nullable = false)
    private Biglietto biglietto;

    public Timbratura() {

    }

    public Timbratura(LocalDate data_timbratura) {
        this.data_timbratura = data_timbratura;
        this.mezzo = mezzo;
        this.biglietto = biglietto;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData_timbratura() {
        return data_timbratura;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public Biglietto getBiglietto() {
        return biglietto;
    }

    @Override
    public String toString() {
        return "Timbratura{" +
                "data_timbratura=" + data_timbratura +
                ", id=" + id +
                ", mezzo=" + mezzo +
                ", biglietto=" + biglietto +
                '}';
    }
}

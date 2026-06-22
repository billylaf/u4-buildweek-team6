package team6.entities;

import jakarta.persistence.*;
import team6.enums.StatoDiMezzo;

import java.time.LocalDate;

@Entity
@Table(name = "stato_mezzo")
public class StatoMezzo {

    LocalDate data_inizio;
    LocalDate data_fine;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private StatoDiMezzo stato;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;


    public StatoMezzo() {

    }

    public StatoMezzo(StatoDiMezzo stato, LocalDate data_inizio, LocalDate data_fine) {
        this.stato = stato;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.mezzo = mezzo;
    }

    public long getId() {
        return id;
    }

    public StatoDiMezzo getStato() {
        return stato;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", id=" + id +
                ", stato=" + stato +
                ", mezzo=" + mezzo +
                '}';
    }
}


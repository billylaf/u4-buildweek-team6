package team6.entities;

import jakarta.persistence.*;
import team6.enums.StatoDiMezzo;

import java.time.LocalDate;

@Entity
@Table(name = "stato_mezzi")
public class StatoMezzo {

    LocalDate data_inizio;
    LocalDate data_fine;
    private String report;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private StatoDiMezzo stato;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;


    public StatoMezzo() {

    }

    public StatoMezzo(StatoDiMezzo stato, LocalDate data_inizio, String report, LocalDate data_fine, Mezzo mezzo) {
        this.stato = stato;
        this.data_inizio = data_inizio;
        this.data_fine = data_fine;
        this.report = report;
        this.mezzo = mezzo;
    }

    public long getId() {
        return id;
    }

    public StatoDiMezzo getStato() {
        return stato;
    }

    public void setStato(StatoDiMezzo stato) {
        this.stato = stato;
    }

    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "data_inizio=" + data_inizio +
                ", data_fine=" + data_fine +
                ", report='" + report + '\'' +
                ", id=" + id +
                ", stato=" + stato +
                ", mezzo=" + mezzo +
                '}';
    }
}


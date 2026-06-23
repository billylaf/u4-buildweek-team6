package team6.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "percorrenze")
public class Percorenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ora_partenza", nullable = false)
    private Timestamp oraPartenza;

    @Column(name = "ora_arrivo", nullable = false)
    private Timestamp oraArrivo;

    @ManyToOne
    @JoinColumn(name = "id_mezzo", nullable = false)
    private Mezzo mezzo;

    @Column(name = "id_tratta", nullable = false)
    private Long idTratta;

    public Percorenza(){}

    public Percorenza(Timestamp oraPartenza, Timestamp oraArrivo, Mezzo mezzo, Long idTratta) {
        this.oraPartenza = oraPartenza;
        this.oraArrivo = oraArrivo;
        this.idTratta = idTratta;
        this.mezzo = mezzo;

    }


    //Si calcola al volo la percorrenza effettiva, transient serve per dire a hibernate di ignorare il metodo in modo tale
    //da non creare errori nella creazione
    @Transient
    public int getPercorrenzaEffettiva() {
        if (this.oraPartenza != null && this.oraArrivo != null) {
            long minuti = ChronoUnit.MINUTES.between(this.oraPartenza.toInstant(), this.oraArrivo.toInstant());
            return (int) minuti;
        }
        return 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOraPartenza() {
        return oraPartenza;
    }

    public void setOraPartenza(Timestamp oraPartenza) {
        this.oraPartenza = oraPartenza;
    }

    public Timestamp getOraArrivo() {
        return oraArrivo;
    }

    public void setOraArrivo(Timestamp oraArrivo) {
        this.oraArrivo = oraArrivo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Long getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Long idTratta) {
        this.idTratta = idTratta;
    }

    @Override
    public String toString() {
        return "Percorenza{" +
                "id=" + id +
                ", oraPartenza=" + oraPartenza +
                ", oraArrivo=" + oraArrivo +
                ", percorrenzaEffettiva=" + getPercorrenzaEffettiva() + " min" +
                ", idMezzo=" + (mezzo != null ? mezzo.getId() : "null") +
                ", idTratta=" + idTratta +
                '}';
    }
}

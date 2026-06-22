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

    @Column(name = "percorrenza_effettiva")
    private int percorrenzaEffettiva;

    @Column(name = "id_mezzo", nullable = false)
    private Long idMezzo;

    @Column(name = "id_tratta", nullable = false)
    private Long idTratta;

    public Percorenza(){}

    public Percorenza(Timestamp oraPartenza, Timestamp oraArrivo, Long idMezzo, Long idTratta) {
        this.oraPartenza = oraPartenza;
        this.oraArrivo = oraArrivo;
        this.idMezzo = idMezzo;
        this.idTratta = idTratta;


        if (oraPartenza !=null && oraArrivo != null){
            long minuti = ChronoUnit.MINUTES.between(oraPartenza.toInstant(), oraArrivo.toInstant());
            this.percorrenzaEffettiva = (int) minuti;
        } else {
            this.percorrenzaEffettiva = 0;
        }
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

    public int getPercorrenzaEffettiva() {
        return percorrenzaEffettiva;
    }

    public void setPercorrenzaEffettiva(int percorrenzaEffettiva) {
        this.percorrenzaEffettiva = percorrenzaEffettiva;
    }

    public Long getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Long idMezzo) {
        this.idMezzo = idMezzo;
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
                ", percorrenzaEffettiva=" + percorrenzaEffettiva +
                ", idMezzo=" + idMezzo +
                ", idTratta=" + idTratta +
                '}';
    }
}

package team6.entities;

import jakarta.persistence.*;
import team6.enums.TipoAbbonamento;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamento")
public class Abbonamento extends AcquistoViaggio{
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipo;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    public Abbonamento(){}

    public Abbonamento(LocalDate dataAcquisto, double prezzo, PuntoVendita puntoVendita, TipoAbbonamento tipo, Tessera tessera){
        super(dataAcquisto, prezzo, puntoVendita);
        this.tipo=tipo;
        this.tessera=tessera;
        // Calcolo con un if se è settimanale scadenza a sette giorni mensile 1 mese utilizzo i metodi di localdate confrontandolo con l'enum
        if (tipo == TipoAbbonamento.SETTIMANALE) {
            this.dataScadenza = dataAcquisto.plusDays(7);
        } else {
            this.dataScadenza = dataAcquisto.plusMonths(1);
        }
    }

    public TipoAbbonamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAbbonamento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "tipo=" + tipo +
                ", dataScadenza=" + dataScadenza +
                ", tessera=" + tessera +
                '}';
    }
}




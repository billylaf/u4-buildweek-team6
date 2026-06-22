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

    public Abbonamento(LocalDate dataEmissione, PuntoVendita puntoVendita, TipoAbbonamento tipo, Tessera tessera){
        super(dataEmissione, puntoVendita);
        this.tipo=tipo;
        this.tessera=tessera;
        // Calcolo con un if se è settimanale scadenza a sette giorni mensile 1 mese utilizzo i metodi di localdate confrontandolo con l'enum
        if (tipo==TipoAbbonamento.SETTIMANALE){
            this.dataScadenza=dataEmissione.plusDays(7);
        } else { // è mensile aggiungo un mese
            this.dataScadenza=dataEmissione.plusMonths(1);
        }
    }

    public boolean isValid() {
        boolean abbonamentoNonScaduto = java.time.LocalDate.now().isBefore(this.dataScadenza)
                || java.time.LocalDate.now().isEqual(this.dataScadenza);

        //L'abbonamento è valido solo se non è scaduto E se la tessera è ancora attiva
        return abbonamentoNonScaduto && !this.tessera.isScaduta();
    }

    public TipoAbbonamento getTipo() {
        return tipo;
    }
    public LocalDate getDataScadenza() {
        return dataScadenza;
    }
    public Tessera getTessera() {
        return tessera;
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

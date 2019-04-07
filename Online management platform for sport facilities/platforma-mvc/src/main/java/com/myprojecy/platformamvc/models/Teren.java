package com.myprojecy.platformamvc.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@IdClass(Teren.class)
public class Teren implements Serializable {

    @Id
    @ManyToOne
    private BazaSportiva bazaSportiva;

    @Id
    @NotNull
    private int numar;

    @NotNull
    private Sport sport;

    @NotNull
    private int numarUtilizari;

    @NotNull
    private double tarif;

    public Teren(BazaSportiva bazaSportiva, int numar, Sport sport, int numarUtilizatori, double tarif) {
        this.bazaSportiva = bazaSportiva;
        this.numar = numar;
        this.sport = sport;
        this.numarUtilizari = numarUtilizatori;
        this.tarif = tarif;
    }

    public Teren() {}

    public BazaSportiva getBazaSportiva() {
        return bazaSportiva;
    }

    public void setBazaSportiva(BazaSportiva bazaSportiva) {
        this.bazaSportiva = bazaSportiva;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public int getNumarUtilizatori() {
        return numarUtilizari;
    }

    public void setNumarUtilizatori(int numarUtilizatori) {
        this.numarUtilizari = numarUtilizatori;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }
}

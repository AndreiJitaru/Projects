package com.myprojecy.platformamvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BazaSportiva {

    @Id
    @GeneratedValue
    private int idBazaSportiva;

    @NotNull
    @Size(min = 10, max = 100)
    private String nume;

    @NotNull
    @Size(min = 10, max = 100)
    private String oras;

    @NotNull
    private double incasari;

    @NotNull
    private double latitudine;

    @NotNull
    private double longitudine;

    @OneToMany
    @JoinColumn(name = "bazaSportiva")
    private List<Teren> terenuri= new ArrayList<Teren>();

    public BazaSportiva() {}

    public BazaSportiva(String nume, String oras, double incasari, double latitudine, double longitudine) {
        this.nume = nume;
        this.oras = oras;
        this.incasari = incasari;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getOras() {
        return oras;
    }

    public void setAdresa(String oras) {
        this.oras = oras;
    }

    public double getIncasari() {
        return incasari;
    }

    public void setIncasari(double incasari) {
        this.incasari = incasari;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public List<Teren> getTerenuri() {
        return terenuri;
    }

    public void setTerenuri(ArrayList<Teren> terenuri) {
        this.terenuri = terenuri;
    }

    public int getIdBazaSportiva() { return idBazaSportiva; }

    @Override
    public boolean equals(Object obj) {
        return idBazaSportiva == ((BazaSportiva) obj).getIdBazaSportiva();
    }
}

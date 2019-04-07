package com.myprojecy.platformamvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Rezervare {

    @Id
    @GeneratedValue
    private int idRezervare;

    @NotNull
    private String numeUtilizator;

    @NotNull
    private String data;

    @NotNull
    private String ora;

    @NotNull
    private int numarTeren;

    @NotNull
    private String numeBazaSportiva;

    @NotNull
    private Sport sport;

    public Rezervare() {
    }

    public Rezervare(String numeUtilizator, String data, String ora, int numarTeren, String numeBazaSportiva,Sport sport) {
        this.numeUtilizator = numeUtilizator;
        this.data = data;
        this.ora = ora;
        this.numarTeren = numarTeren;
        this.numeBazaSportiva = numeBazaSportiva;
        this.sport = sport;
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getNumarTeren() {
        return numarTeren;
    }

    public void setNumarTeren(int numarTeren) {
        this.numarTeren = numarTeren;
    }

    public String getNumeBazaSportiva() {
        return numeBazaSportiva;
    }

    public void setNumeBazaSportiva(String numeBazaSportiva) {
        this.numeBazaSportiva = numeBazaSportiva;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }


}

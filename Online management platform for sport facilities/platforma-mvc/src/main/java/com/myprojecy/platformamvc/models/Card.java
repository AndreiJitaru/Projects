package com.myprojecy.platformamvc.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Card {

    @Id
    @NotNull
    //@Size(min = 1, max = 16)
    private String numarCard;

    @NotNull
    private String nume;

    @NotNull
    private String prenume;

    @NotNull
    private float suma;

    @NotNull
    private int codSecuritate;

    public Card() { }

    public Card(String numarCard, String nume, String prenume, int codSecuritate) {
        this.numarCard = numarCard;
        this.nume = nume;
        this.prenume = prenume;
        this.codSecuritate = codSecuritate;
    }

    public String getNumarCard() {
        return numarCard;
    }

    public void setNumarCard(String numarCard) {
        this.numarCard = numarCard;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    public int getCodSecuritate() {
        return codSecuritate;
    }

    public void setCodSecuritate(int codSecuritate) {
        this.codSecuritate = codSecuritate;
    }
}

package com.myprojecy.platformamvc.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @NotNull
    private String numeUtilizator;

    @NotNull
    private String nume;

    @NotNull
    private String prenume;

    @NotNull
    private String email;

    @NotNull
    private String numarTelefon;

    @NotNull
    private String parola;

    @NotNull
    private int numarRezervari;

    public Client() {}

    public Client(String nume, String prenume, String email, String numarTelefon, String numeUtilizator, String parola) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.numarTelefon = numarTelefon;
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
        this.numarRezervari = 0;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getNumarRezervari() {
        return numarRezervari;
    }

    public void setNumarRezervari(int numarRezervari) {
        this.numarRezervari = numarRezervari;
    }
}

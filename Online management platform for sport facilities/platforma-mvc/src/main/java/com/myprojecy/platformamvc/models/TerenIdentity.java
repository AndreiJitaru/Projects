package com.myprojecy.platformamvc.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TerenIdentity implements Serializable {

    @NotNull
    private int idBazaSportiva;

    @NotNull
    private int numarTeren;

    public TerenIdentity(int idBazaSportiva, int numarTeren) {
        this.idBazaSportiva = idBazaSportiva;
        this.numarTeren = numarTeren;
    }

    public TerenIdentity() { }

    public int getIdBazaSportiva() {
        return idBazaSportiva;
    }

    public void setIdBazaSportiva(int idBazaSportiva) {
        this.idBazaSportiva = idBazaSportiva;
    }

    public int getNumarTeren() {
        return numarTeren;
    }

    public void setNumarTeren(int numarTeren) {
        this.numarTeren = numarTeren;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       TerenIdentity that = (TerenIdentity) o;

        if (idBazaSportiva != that.idBazaSportiva) return false;
        return numarTeren == that.numarTeren;
    }

}

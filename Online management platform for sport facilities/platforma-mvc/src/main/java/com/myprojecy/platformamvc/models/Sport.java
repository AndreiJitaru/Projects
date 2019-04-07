package com.myprojecy.platformamvc.models;

public enum Sport {

    FOTBAL ("Fotbal"),
    BASCHET ("Baschet"),
    TENIS ("Tenis"),
    VOLEI ("Volei"),
    HANDBAL ("Handbal");

    private String name;

    Sport(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

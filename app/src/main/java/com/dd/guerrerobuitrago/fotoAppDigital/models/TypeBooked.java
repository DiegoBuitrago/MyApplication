package com.dd.guerrerobuitrago.fotoAppDigital.models;

public enum TypeBooked {
    PHOTO_DESIGN("Estudio a Diseño"), PRINCESS("Princesitas"), MAMELUCO("Mameluco"), STARS("Estrellitas"), NO_DESIGN("Sin Diseño");

    private String myName;

    private TypeBooked(String myName){
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }
}
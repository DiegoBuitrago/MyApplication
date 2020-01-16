package com.dd.guerrerobuitrago.fotoAppDigital.models;

public enum TypeBooked {
    PHOTO_DOCUMENT("Foto Documento"), PHOTO_DESIGN("Diseños"), RESTAURATION("Restauraciones");

    private String myName;

    private TypeBooked(String myName){
        this.myName = myName;
    }

    public String getMyName() {
        return myName;
    }
}
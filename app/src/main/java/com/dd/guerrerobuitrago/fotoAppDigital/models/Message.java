package com.dd.guerrerobuitrago.fotoAppDigital.models;

public class Message {
    private String nameUser;
    private String message;

    public Message(String nameUser, String message){
        this.nameUser = nameUser;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getMessage() {
        return message;
    }

    public String getNameUser() {
        return nameUser;
    }
}
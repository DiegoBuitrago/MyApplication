package com.dd.guerrerobuitrago.fotoAppDigital.models;

public class Message {
    private int id;
    private String nameUser;
    private String message;

    public Message(int id, String nameUser, String message){
        this.id = id;
        this.nameUser = nameUser;
        this.message = message;
    }

    public Message(String nameUser, String message){
        this.id = id;
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
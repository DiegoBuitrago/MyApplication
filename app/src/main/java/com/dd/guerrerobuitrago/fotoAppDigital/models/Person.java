package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.net.Uri;

import java.io.Serializable;

public class Person implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String photo;
    private String typeUser;

    public Person(int id, String firstName, String lastName, String userName, String password, Uri photo, String typeUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.photo = photo.toString();
        this.typeUser = typeUser;
    }

    public Person(int id, String firstName, String lastName, String userName, String password, String typeUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.photo = null;
        this.typeUser = typeUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo.toString();
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getUserName() {
        return userName;
    }
}
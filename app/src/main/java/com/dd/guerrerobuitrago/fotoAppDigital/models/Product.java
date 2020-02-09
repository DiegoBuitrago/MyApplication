package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.net.Uri;

public class Product {

    private int id;
    private String name;
    private String description;
    private Uri photo;

    public Product(int id, String name, String description, Uri photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
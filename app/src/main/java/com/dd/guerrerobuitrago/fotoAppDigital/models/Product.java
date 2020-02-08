package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.net.Uri;

public class Product {

    private String name;
    private String description;
    private Uri photo;

    public Product(String name, String description, Uri photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
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
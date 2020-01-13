package com.dd.guerrerobuitrago.fotoAppDigital.models;

public class Booked {

    private int id;
    private String date;
    private String hour;
    private TypeBooked typeBooked;

    public Booked(int id, String date, String hour, TypeBooked typeBooked){
        this.id = id;
        this.date = date;
        this.hour = hour;
        this.typeBooked = typeBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TypeBooked getTypeBooked() {
        return typeBooked;
    }

    public void setTypeBooked(TypeBooked typeBooked) {
        this.typeBooked = typeBooked;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

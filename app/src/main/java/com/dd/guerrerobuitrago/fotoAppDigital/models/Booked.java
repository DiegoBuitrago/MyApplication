package com.dd.guerrerobuitrago.fotoAppDigital.models;

public class Booked {

    private int id;
    private int year;
    private int month;
    private int day;
    private String hour;
    private TypeBooked typeBooked;

    public Booked(int id, int year, int month, int day, String hour, TypeBooked typeBooked){
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.typeBooked = typeBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
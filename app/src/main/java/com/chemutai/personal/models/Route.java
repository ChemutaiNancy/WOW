package com.chemutai.personal.models;

public class Route {
    private int id;
    private String name;
    private double cost;
    private String time;
    private String seats;
    private boolean checked=false;

    public Route() {
    }


    public Route(int id, String name, double cost, String time, String seats) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.time = time;
        this.seats = seats;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}

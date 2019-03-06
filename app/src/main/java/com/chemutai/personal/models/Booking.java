package com.chemutai.personal.models;


public class Booking {
    private int book_id;
    private int route_id;
    private String travel_date;
    private int no_of_seat;
    private boolean return_ticket = false;
    private String pick_up_location;

    public Booking() {
    }


    public Booking(int book_id, int route_id, String travel_date, int no_of_seat, boolean return_ticket, String pick_up_location) {
        this.book_id = book_id;
        this.route_id = route_id;
        this.travel_date = travel_date;
        this.no_of_seat = no_of_seat;
        this.return_ticket = return_ticket;
        this.pick_up_location = pick_up_location;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public int getNo_of_seat() {
        return no_of_seat;
    }

    public void setNo_of_seat(int no_of_seat) {
        this.no_of_seat = no_of_seat;
    }

    public boolean isReturn_ticket() {
        return return_ticket;
    }

    public void setReturn_ticket(boolean return_ticket) {
        this.return_ticket = return_ticket;
    }

    public String getPick_up_location() {
        return pick_up_location;
    }

    public void setPick_up_location(String pick_up_location) {
        this.pick_up_location = pick_up_location;
    }
}
package com.chemutai.personal.models;

public class Seat {
    private int seat_id;
    private int seat_no;

    public Seat() {
    }

    public Seat(int seat_id, int seat_no) {
        this.seat_id = seat_id;
        this.seat_no = seat_no;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getSeat_no() {
        return seat_no;
    }

    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }
}

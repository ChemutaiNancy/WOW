package com.chemutai.personal.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chemutai.personal.models.Booking;

import java.util.ArrayList;

public class SaveBookingDetails extends SQLiteOpenHelper {


    public SaveBookingDetails(@Nullable Context context) {
        super(context, "booking.db", null, 1);
    }

    public SaveBookingDetails(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS booking_details (book_id INTEGER PRIMARY KEY AUTOINCREMENT, route_id, travel_date, no_of_seats, return_date, pick_up_location)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE booking_details");
        sqLiteDatabase.execSQL("CREATE TABLE booking_details (book_id INTEGER PRIMARY KEY AUTOINCREMENT, route_id, travel_date, no_of_seats, return_date, pick_up_location)");
    }

    public void insert(Booking booking){
        //create content values
        ContentValues values = new ContentValues();
        values.put("route_id", booking.getRoute_id());
        values.put("travel_date", booking.getTravel_date());
        values.put("no_of_seats", booking.getNo_of_seat());
        values.put("return_ticket", booking.isReturn_ticket());
        values.put("pick_up_location", booking.getPick_up_location());

        this.getWritableDatabase().insert("booking_details", "", values);
    }

    public ArrayList<Booking> show(){
        String sql = "SELECT * FROM booking_details";
        Cursor cursor = this.getReadableDatabase().rawQuery(sql, null);

        ArrayList<Booking> booking = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Log.d("BOOKING", "ID: "+cursor.getInt(0));
                Log.d("BOOKING", "TRAVEL_DATE: "+cursor.getString(1));
                Log.d("BOOKING", "NO_OF_SEATS: "+cursor.getInt(2));
                Log.d("BOOKING", "RETURN_TICKET: "+cursor.getString(3));
                Log.d("BOOKING", "PICK_UP_LOCATION: "+cursor.getString(4));
                Log.d("BOOKING", "............................................. ");

                Booking b = new Booking(cursor.getInt(0), 1, cursor.getString(1), cursor.getInt(2), false, cursor.getString(4));
             //                           int book_id, int route_id, String travel_date, int no_of_seat, boolean return_ticket, String pick_up_location
                booking.add(b);
            }
            while (cursor.moveToNext());
        }
        return booking;
    }

   /* public void update(Booking book){
        ContentValues values = new ContentValues();
        values.put("route_id", book.getRoute_id());
        values.put("travel_date", book.getTravel_date()+"");
        values.put("no_of_seats", book.getNo_of_seat());
        values.put("return_ticket", book.isReturn_ticket());
        values.put("pick_up_location", book.getPick_up_location());

        this.getWritableDatabase().update("booking_details", values, "ID="+book.getBook_id(), null);
    }*/
}

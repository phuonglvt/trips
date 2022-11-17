package com.example.trips.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.Context;
import android.content.ContentValues;


import java.util.List;
import java.util.ArrayList;

import com.example.trips.model.Trip;

public class TripDatabase {
    private SQLiteDatabase db;

    public TripDatabase(Context context) {
        DBHelper helper = new DBHelper(context);

        db = helper.getWritableDatabase();
    }

    public List<Trip> get(String sql, String ...selectArgs){
        List<Trip> list = new ArrayList();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while(cursor.moveToNext()){
            Trip trip = new Trip();
            trip.setId(cursor.getString(cursor.getColumnIndex("id")));
            trip.setName(cursor.getString(cursor.getColumnIndex("name")));
            trip.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            trip.setDateOfTheTrip(cursor.getString(cursor.getColumnIndex("dateOfTheTrip")));
            trip.setDescription(cursor.getString(cursor.getColumnIndex("description")));

            list.add(trip);
        }
        return list;
    }

    public List<Trip> getAll(){
        String sql = "SELECT * FROM trips";

        return get(sql);
    }

    public Trip getById(String id){
        String sql = "SELECT * FROM trips WHERE id = ?";

        List<Trip> list = get(sql, id);

        return list.get(0);
    }

    public long insert(Trip trip){
        ContentValues values = new ContentValues();
        values.put("id", trip.getId());
        values.put("name", trip.getName());
        values.put("destination", trip.getDestination());
        values.put("dateOfTheTrip", trip.getDateOfTheTrip());
        values.put("requireAssessment", trip.getRequireAssessment());
        values.put("description", trip.getDescription());

        return db.insert("trips", null, values);
    }

    public long update(Trip trip){
        ContentValues values = new ContentValues();
        values.put("name", trip.getName());
        values.put("destination", trip.getDestination());
        values.put("dateOfTheTrip", trip.getDateOfTheTrip());
        values.put("requireAssessment", trip.getRequireAssessment());
        values.put("description", trip.getDescription());

        //String[]{trip.getId(): mang truyen gia tri dua vao, dua vao ID de thuc hien. Gia tri dieu kien update
        return db.update("trips", values, "id = ?", new String[]{trip.getId()});
    }

    public int delete(String id){
        return db.delete("trips", "id=?", new String[]{id});
    }
}

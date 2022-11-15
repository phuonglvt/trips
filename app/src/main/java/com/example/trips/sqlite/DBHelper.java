package com.example.trips.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MyTrips";
    public static final int DB_VERSION = 4;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        String sql = "CREATE TABLE trips(id TEXT PRIMARY KEY, destination TEXT not null,"+
                "dateOfTheTrip TEXT not null, requireAssessment BOOLEAN not null, description TEXT not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop Existing Table
        String sql = "DROP TABLE IF EXISTS trips";
        db.execSQL(sql);
        onCreate(db);
    }

}

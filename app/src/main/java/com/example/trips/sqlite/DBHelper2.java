package com.example.trips.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {
    public static final String DB_NAME = "Expenses";
    public static final int DB_VERSION = 1;

    public DBHelper2(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        String sql = "CREATE TABLE expenses(id TEXT PRIMARY KEY, type TEXT not null, amount TEXT not null,"+
                "timeExpense TEXT not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Drop Existing Table
        String sql = "DROP TABLE IF EXISTS expenses";
        db.execSQL(sql);
        onCreate(db);
    }

}

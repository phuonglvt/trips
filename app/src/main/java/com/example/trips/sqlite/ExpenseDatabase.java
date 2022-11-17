package com.example.trips.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.Context;
import android.content.ContentValues;

import java.util.List;
import java.util.ArrayList;

import com.example.trips.model.Expense;

public class ExpenseDatabase {
    private SQLiteDatabase db;

    public ExpenseDatabase(Context context) {
        DBHelper2 helper = new DBHelper2(context);

        db = helper.getWritableDatabase();
    }

    public List<Expense> get(String sql, String ...selectArgs){
        List<Expense> list = new ArrayList();
        Cursor cursor = db.rawQuery(sql, selectArgs);

        while(cursor.moveToNext()){
            Expense expense = new Expense();
            expense.setId(cursor.getString(cursor.getColumnIndex("id")));
            expense.setType(cursor.getString(cursor.getColumnIndex("type")));
            expense.setAmount(cursor.getString(cursor.getColumnIndex("amount")));
            expense.setTimeExpense(cursor.getString(cursor.getColumnIndex("timeExpense")));

            list.add(expense);
        }
        return list;
    }

    public List<Expense> getAll(){
        String sql = "SELECT * FROM expenses";

        return get(sql);
    }

    public Expense getById(String id){
        String sql = "SELECT * FROM expenses WHERE id = ?";

        List<Expense> list = get(sql, id);

        return list.get(0);
    }

    public long insert(Expense expense){
        ContentValues values = new ContentValues();
        values.put("id", expense.getId());
        values.put("type", expense.getType());
        values.put("amount", expense.getAmount());
        values.put("timeExpense", expense.getTimeExpense());

        return db.insert("expenses", null, values);
    }

    public long update(Expense expense){
        ContentValues values = new ContentValues();
        values.put("type", expense.getType());
        values.put("amount", expense.getAmount());
        values.put("timeExpense", expense.getTimeExpense());

        //String[]{expense.getId(): mang truyen gia tri dua vao, dua vao ID de thuc hien. Gia tri dieu kien update
        return db.update("expenses", values, "id = ?", new String[]{expense.getId()});
    }

    public int delete(String id){
        return db.delete("expenses", "id=?", new String[]{id});
    }
}


package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Toast;


import com.example.trips.adapter.ExpenseAdapter;
import com.example.trips.model.Expense;
import com.example.trips.model.Trip;
import com.example.trips.sqlite.DBHelper;
import com.example.trips.sqlite.ExpenseDatabase;
import com.example.trips.sqlite.TripDatabase;

public class MainActivityExpenses extends AppCompatActivity implements View.OnClickListener{
    private ExpenseAdapter expenseAdapter;
    private ListView listExpenses;
    private String expenseId;
    private List<Expense> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expenses);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.close();
        findViewById(R.id.btEdit2).setOnClickListener(this);
        findViewById(R.id.btDelete2).setOnClickListener(this);
        findViewById(R.id.btInsert2).setOnClickListener(this);
        listExpenses = findViewById(R.id.listExpenses);

        ExpenseDatabase database2 = new ExpenseDatabase(this);
        list = database2.getAll();
        ExpenseAdapter expenseAdapter = new ExpenseAdapter(this, list);
        listExpenses.setAdapter(expenseAdapter);

        listExpenses.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Expense expense = list.get(i);
                expenseId= expense.getId();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ExpenseDatabase database = new ExpenseDatabase(this);
        List<Expense> updateList = database.getAll();

        list.clear();
        updateList.forEach(item ->list.add(item));

        expenseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, AddOrEditExpenseActivity.class);
        switch (view.getId()){
            case R.id.btInsert2:
                startActivity(intent);
                break;
            case R.id.btEdit2:
                if(expenseId == null){
                    Toast.makeText(this, "Expense must be selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("id", expenseId);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;
            case R.id.btDelete2:
                if(expenseId == null){
                    Toast.makeText(this, "Expense must be selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                ExpenseDatabase database = new ExpenseDatabase(this);
                database.delete(expenseId);
                expenseId = null;
                Toast.makeText(this, "Expense was deleted!", Toast.LENGTH_SHORT).show();

                break;

        }
    }
}
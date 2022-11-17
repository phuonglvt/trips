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


import com.example.trips.adapter.TripAdapter;
import com.example.trips.model.Trip;
import com.example.trips.sqlite.DBHelper;
import com.example.trips.sqlite.TripDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TripAdapter tripAdapter;
    private ListView listTrips2;
    private String tripId;
    private List<Trip> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.close();
        findViewById(R.id.btEdit2).setOnClickListener(this);
        findViewById(R.id.btDelete2).setOnClickListener(this);
        findViewById(R.id.btInsert2).setOnClickListener(this);
        findViewById(R.id.btExpenses).setOnClickListener(this);
        listTrips2 = findViewById(R.id.listTrips2);

        TripDatabase database1 = new TripDatabase(this);
        list = database1.getAll();
        tripAdapter = new TripAdapter(this, list);
        listTrips2.setAdapter(tripAdapter);


        listTrips2.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Trip trip = list.get(i);
                tripId = trip.getId();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        TripDatabase database = new TripDatabase(this);
        List<Trip> updateList = database.getAll();

        list.clear();
        updateList.forEach(item ->list.add(item));

        tripAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, AddOrEditTripActivity.class);
        switch (view.getId()){
            case R.id.btInsert2:
                startActivity(intent);
                break;
            case R.id.btEdit2:
                if(tripId == null){
                    Toast.makeText(this, "Trip must be selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("id", tripId);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;
            case R.id.btExpenses:
                Intent intent2 = new Intent(this, AddOrEditExpenseActivity.class);
//                Bundle bundle = new Bundle();
                startActivity(intent2);
                break;
            case R.id.btDelete2:
                if(tripId == null){
                    Toast.makeText(this, "Trip must be selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                TripDatabase database = new TripDatabase(this);
                database.delete(tripId);
                tripId = null;
                onResume();
                Toast.makeText(this, "Trip was deleted!", Toast.LENGTH_SHORT).show();

                break;

        }
    }
}
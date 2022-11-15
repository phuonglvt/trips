package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import java.util.List;
import android.widget.AdapterView;


import com.example.trips.adapter.TripAdapter;
import com.example.trips.model.Trip;
import com.example.trips.sqlite.DBHelper;
import com.example.trips.sqlite.TripDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TripAdapter tripAdapter;
    private ListView listTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DBHelper dbHelper = new DBHelper(this);
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        database.close();
        findViewById(R.id.btEdit).setOnClickListener(this);
        findViewById(R.id.btDelete).setOnClickListener(this);
        findViewById(R.id.btInsert).setOnClickListener(this);
        listTrips = findViewById(R.id.listTrips);

        TripDatabase database = new TripDatabase(this);
        List<Trip> list = database.getAll();
        tripAdapter = new TripAdapter(this, list);
        listTrips.setAdapter(tripAdapter);

        listTrips.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Trip trip = list.get(i);
//                 = trip.getId();
            }
        });
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, AddOrEditTripActivity.class);
        switch (view.getId()){
            case R.id.btInsert:

                startActivity(intent);
                break;
            case R.id.btEdit:
//                if(tripId == null){
//                    Toast.makeText(this, "Trip id must be selected", Toast.LENGTH_LONG).show();
//                    return;
//                }
                Bundle bundle = new Bundle();
//                bundle.putString("id", tripId);
//                intent.putExtras("data", bundle);
                startActivity(intent);
                break;

        }
    }
}
package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trips.model.Trip;
import com.example.trips.sqlite.TripDatabase;


public class AddOrEditTripActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTripId, etDestination, editDateOfTheTrip, editDescription;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_trip);

        editTripId = findViewById(R.id.editTripId);
        etDestination = findViewById(R.id.etDestination);
        editDateOfTheTrip = findViewById(R.id.editDateOfTheTrip);
        editDescription = findViewById(R.id.editDescription);
        radioGroup = findViewById(R.id.radioGroup);


        findViewById(R.id.btSubmit).setOnClickListener(this);
        findViewById(R.id.btList).setOnClickListener(this);

        radioGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
            }
        });

//        readTrip();
    }

//    private void readTrip(){
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("data");
//        if(bundle == null){
//            return;
//        }
//        String id = bundle.getString("id");
//
//        TripDatabase database = new TripDatabase(this);
//        Trip trip = database.getById(id);
//
//        editTripId.setText(trip.getId());
//        etDestination.setText(trip.getDestination());
//        editDateOfTheTrip.setText(trip.getDateOfTheTrip());
//        radioGroup.setText(""+trip.getRadioButtonId());
//        editDescription.setText(trip.getDescription());
//    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btSubmit:
                TripDatabase database = new TripDatabase(this);
                Trip trip = new Trip();

                trip.setId(editTripId.getText().toString());
                trip.setDestination(etDestination.getText().toString());
                trip.setDateOfTheTrip(editDateOfTheTrip.getText().toString());
                trip.setDescription(editDescription.getText().toString());
//                trip.setRequireAssessment(Boolean.parseBoolean(radioGroup.getText.toString()));

//                int radioId = radioGroup.getCheckedRadioButtonId();
//
//                trip.radioButton = findViewById(radioId);

                database.insert(trip);
                Toast.makeText(this, "New trip has been submit!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
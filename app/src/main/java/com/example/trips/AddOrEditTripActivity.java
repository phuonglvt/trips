package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;
import android.widget.Button;

import com.example.trips.model.Trip;
import com.example.trips.sqlite.TripDatabase;



public class AddOrEditTripActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTripId, etName, etDestination, editDateOfTheTrip, editDescription;
    private RadioButton r_yes;
    private RadioGroup radioGroup;
    private Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_trip);

        editTripId = findViewById(R.id.editTripId);
        etName = findViewById(R.id.etAmount);
        etDestination = findViewById(R.id.etTimeExpense);
        editDateOfTheTrip = findViewById(R.id.editDateOfTheTrip);
        editDescription = findViewById(R.id.editDescription);
        radioGroup = findViewById(R.id.radioGroup);


        findViewById(R.id.btSubmit).setOnClickListener(this);
        findViewById(R.id.btCancel).setOnClickListener(this);
        radioGroup.check(R.id.radioYes);

        radioGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int radioId = radioGroup.getCheckedRadioButtonId();

                r_yes = findViewById(radioId);
            }
        });

        readTrip();
    }

    private void readTrip(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null){
            return;
        }
        String id = bundle.getString("id");

        TripDatabase database = new TripDatabase(this);
        Trip trip = database.getById(id);

        editTripId.setText(trip.getId());
        etDestination.setText(trip.getDestination());
        editDateOfTheTrip.setText(trip.getDateOfTheTrip());
        editDescription.setText(trip.getDescription());
        btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setText("Update");

        if(btSubmit.getText().equals("Submit")){
            database.insert(trip);
        } else{
            database.update(trip);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btSubmit:
                TripDatabase database = new TripDatabase(this);
                Trip trip = new Trip();

                trip.setId(editTripId.getText().toString());
                trip.setName(etName.getText().toString());
                trip.setDestination(etDestination.getText().toString());
                trip.setDateOfTheTrip(editDateOfTheTrip.getText().toString());
                trip.setDescription(editDescription.getText().toString());
                r_yes = findViewById(R.id.radioYes);
                trip.setRequireAssessment(r_yes.isChecked());

                if(btSubmit.getText().equals("Submit")){
                    database.insert(trip);
                    Toast.makeText(this, "This trip has been submit!", Toast.LENGTH_SHORT).show();
                } else{
                    database.update(trip);
                    Toast.makeText(this, "New trip has been submit!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
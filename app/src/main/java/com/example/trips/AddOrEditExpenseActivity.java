package com.example.trips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import android.widget.Toast;

import com.example.trips.model.Expense;
import com.example.trips.model.Trip;
import com.example.trips.sqlite.ExpenseDatabase;
import com.example.trips.sqlite.TripDatabase;

public class AddOrEditExpenseActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etType, etAmount, etTimeExpense;
    private Button btSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_expense);

        etType = findViewById(R.id.etType);
        etAmount = findViewById(R.id.etAmount);
        etTimeExpense = findViewById(R.id.etTimeExpense);

        findViewById(R.id.btSubmit).setOnClickListener(this);
        findViewById(R.id.btCancel).setOnClickListener(this);

        readExpense();
    }

    private void readExpense(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if(bundle == null){
            return;
        }
        String id = bundle.getString("id");

        ExpenseDatabase database = new ExpenseDatabase(this);
        Expense expense = database.getById(id);

        etType.setText(expense.getType());
        etAmount.setText(expense.getAmount());
        etTimeExpense.setText(expense.getTimeExpense());

        btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setText("Update");

        if(btSubmit.getText().equals("Submit")){
            database.insert(expense);
        } else{
            database.update(expense);
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btSubmit:
                ExpenseDatabase database = new ExpenseDatabase(this);
                Expense expense = new Expense();

                expense.setType(etType.getText().toString());
                expense.setAmount(etAmount.getText().toString());
                expense.setTimeExpense(etTimeExpense.getText().toString());

                if(btSubmit.getText().equals("Submit")){
                    database.insert(expense);
                    Toast.makeText(this, "This trip has been submit!", Toast.LENGTH_SHORT).show();
                } else{
                    database.update(expense);
                    Toast.makeText(this, "New trip has been submit!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

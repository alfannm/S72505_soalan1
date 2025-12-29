package com.example.s72505_soalan1;

// Imports
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    Init variables
    private EditText billAmountInput;
    private RadioGroup tipPercentageGroup;
    private Button calculateButton;

//    Assign the button and input from xml to the variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billAmountInput = findViewById(R.id.bill_amount_input);
        tipPercentageGroup = findViewById(R.id.tip_percentage_group);
        calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTip();
            }
        });
    }

    private void calculateTip() {
        String billAmountStr = billAmountInput.getText().toString();

        // Bill amount must not be empty
        if (billAmountStr.isEmpty()) {
            Toast.makeText(this, "Bill amount must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        double billAmount = Double.parseDouble(billAmountStr);

        // Bill amount must be greater than 0
        if (billAmount <= 0) {
            Toast.makeText(this, "Bill amount must be greater than 0", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedTipPercentageId = tipPercentageGroup.getCheckedRadioButtonId();

        // handle if user does not select tip amount
        if (selectedTipPercentageId == -1) {
            Toast.makeText(this, "A tip percentage must be selected", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate the tip
        double tipPercentage = 0;
        if (selectedTipPercentageId == R.id.tip_5_percent) {
            tipPercentage = 0.05;
        } else if (selectedTipPercentageId == R.id.tip_10_percent) {
            tipPercentage = 0.10;
        } else if (selectedTipPercentageId == R.id.tip_15_percent) {
            tipPercentage = 0.15;
        }

        // The FORMULA!
        double tipAmount = billAmount * tipPercentage;

        Toast.makeText(this, String.format("Tip Amount: RM%.2f", tipAmount), Toast.LENGTH_SHORT).show();
    }
}

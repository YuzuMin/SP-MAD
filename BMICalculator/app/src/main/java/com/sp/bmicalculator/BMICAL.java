package com.sp.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMICAL extends AppCompatActivity {
    private TextView Result;
    private EditText Weight;
    private Button buttonCalculate;
    private EditText Height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Weight =findViewById(R.id.weight);
        Height =findViewById(R.id.height);
        Result =findViewById(R.id.resultLabel);
        buttonCalculate =findViewById(R.id.button_calculate);
        buttonCalculate.setOnClickListener(calculate_value);
    }


    private View.OnClickListener calculate_value= new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            double weight;
            weight = 0;
            double height;
            height = 0;

            // get the users values from the widget references
            if (!(Weight.getText().toString().equals(""))) {
                weight = Double.parseDouble(Weight.getText().toString());
            }

            if (!(Height.getText().toString().equals(""))) {
                height = Double.parseDouble(Height.getText().toString());
            }

            double bmi;
            bmi = calculateBMI(weight, height);


            // round to 1 digit
            double newBMI = Math.round(bmi*100.0)/100.0;
            DecimalFormat f = new DecimalFormat("##.##");

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(newBMI);

            Result.setText("BMI - " + newBMI + " " + bmiInterpretation);
            //String bmivalue_s = ("BMI - " + newBMI + "\n" + bmiInterpretation);
            //Toast.makeText(v.getContext(),bmivalue_s,Toast.LENGTH_LONG).show();
        }
    };

    private double getTextAsDouble(EditText editText) {
        String input = editText.getText().toString().replace(',', '.');
        try {
            return Double.valueOf(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // the formula to calculate the BMI index
    private double calculateBMI (double weight, double height) {
        return (double) (((weight) / (height)) / (height));
    }
    // interpret what BMI means
    private String interpretBMI(double bmi) {

        if (bmi == 0) {
            return "Enter your details";
        } else if (bmi < 18.5) {
            return "underweight";
        } else if (bmi < 25) {
            return "Healthy";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

}
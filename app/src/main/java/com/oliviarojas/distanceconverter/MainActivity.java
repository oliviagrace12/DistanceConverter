package com.oliviarojas.distanceconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private TextView outputValue;
    private TextView historyValue;
    private DecimalFormat format = new DecimalFormat("#.#");

    private static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        outputValue = findViewById(R.id.resultValue);
        historyValue = findViewById(R.id.conversionHistoryText);
    }

    public void convertButtonClicked(View view) {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            outputValue.setText("");
            return;
        }

        String output = convertMilesToKilometers(input);
        outputValue.setText(output);
        setHistory(input, output);
    }

    private String convertMilesToKilometers(String inputString) {
        return format.format(Double.parseDouble(inputString) * 1.60934);
    }

    private String convertKilometersToMiles(String input) {
        return format.format(Double.parseDouble(input) * 0.621371);
    }

    private void setHistory(String input, String output) {
        historyValue.setText("Mi to Km: " + input + " ==> " + output + "\n" + historyValue.getText().toString());
    }

    public void clearButtonClicked(View view) {
        historyValue.setText("");
    }
}
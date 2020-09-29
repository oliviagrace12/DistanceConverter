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
        String inputString = inputValue.getText().toString();
        Log.d(TAG, "Got string value: " + inputString);
        if (inputString == null || inputString.isEmpty()) {
            return;
        }
        Double input = Double.valueOf(inputString);
        String output = format.format(input * 1.60934);

        outputValue.setText(output);
        if (historyValue.getText().toString().isEmpty()) {
            historyValue.setText(output);
        } else {
            StringBuilder builder = new StringBuilder(output);
            builder.append("\n").append(historyValue.getText().toString());
            historyValue.setText(builder.toString());
        }
    }

    public void clearButtonClicked(View view) {
        historyValue.setText("");
    }
}
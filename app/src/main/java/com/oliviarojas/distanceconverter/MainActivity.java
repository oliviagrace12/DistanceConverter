package com.oliviarojas.distanceconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView inputLabel;
    private EditText inputValue;
    private TextView resultLabel;
    private TextView resultValue;
    private TextView historyValue;
    private DecimalFormat format = new DecimalFormat("#.#");
    private boolean isMiToKm = true;

    private static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLabel = findViewById(R.id.inputLabel);
        inputValue = findViewById(R.id.inputValue);
        resultLabel = findViewById(R.id.resultLabel);
        resultValue = findViewById(R.id.resultValue);
        historyValue = findViewById(R.id.conversionHistoryText);
        historyValue.setMovementMethod(new ScrollingMovementMethod());
    }

    public void convertButtonClicked(View view) {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            resultValue.setText("");
            return;
        }

        String output = convert(input);
        resultValue.setText(output);
        setHistory(input, output);
    }

    private String convert(String input) {
        if (isMiToKm) {
            return convertMilesToKilometers(input);
        } else {
            return convertKilometersToMiles(input);
        }
    }

    private String convertMilesToKilometers(String inputString) {
        return format.format(Double.parseDouble(inputString) * 1.60934);
    }

    private String convertKilometersToMiles(String input) {
        return format.format(Double.parseDouble(input) * 0.621371);
    }

    private void setHistory(String input, String output) {
        if (isMiToKm) {
            historyValue.setText(String.format("%s mi ==> %s km\n%s", input, output, historyValue.getText().toString()));
        } else {
            historyValue.setText(String.format("%s km ==> %s mi\n%s", input, output, historyValue.getText().toString()));
        }
    }

    public void clearButtonClicked(View view) {
        historyValue.setText("");
    }

    public void radioButtonClicked(View view) {
        if (R.id.milesToKilometers == view.getId()) {
            isMiToKm = true;
            inputLabel.setText(R.string.miles_value);
            resultLabel.setText(R.string.kilometers_value);
        } else {
            isMiToKm = false;
            inputLabel.setText(R.string.kilometers_value);
            resultLabel.setText(R.string.miles_value);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(getString(R.string.history_state), historyValue.getText());
        outState.putCharSequence(getString(R.string.result_state), resultValue.getText());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        historyValue.setText(savedInstanceState.getCharSequence(getString(R.string.history_state)));
        resultValue.setText(savedInstanceState.getCharSequence(getString(R.string.result_state)));
    }
}
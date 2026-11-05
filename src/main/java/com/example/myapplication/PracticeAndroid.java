package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeAndroid extends AppCompatActivity {

    TextView txt2;
    Button bt1;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_android);

        txt2 = findViewById(R.id.txt2);
        bt1 = findViewById(R.id.bt1);

        Log.d("MyCustomLog", "App Started");

        bt1.setOnClickListener(v -> {
            counter++;

            txt2.setText(String.valueOf(counter));

            Log.d("MyCustomLog", "Button Clicked");
            Log.d("MyCustomLog", "Counter = " + counter);
        });
    }
}
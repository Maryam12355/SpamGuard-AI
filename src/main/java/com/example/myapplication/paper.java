
package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class paper extends AppCompatActivity {

    TextView txtCount;
    Button btnIncrease, btnDecrease, btnReset;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);

        txtCount = findViewById(R.id.txtCount);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnReset = findViewById(R.id.btnReset);

        btnIncrease.setOnClickListener(v -> {
            counter++;
            txtCount.setText(String.valueOf(counter));
        });

        btnDecrease.setOnClickListener(v -> {
            if (counter > 0) {
                counter--;
                txtCount.setText(String.valueOf(counter));
            }
        });

        btnReset.setOnClickListener(v -> {
            counter = 0;
            txtCount.setText(String.valueOf(counter));
        });
    }
}

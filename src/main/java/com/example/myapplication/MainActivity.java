package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //1 create object
        Button btnCount;
        TextView txtCount;
        Button btnReset;
            //bind ui with java object
        btnCount=findViewById(R.id.btnCount);//bind karta kart buton ui k component k sath
        txtCount=findViewById(R.id.txtCount);
        btnReset=findViewById(R.id.btnReset);
//click listner
        btnCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                count++;
                txtCount.setText("" + count);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=0;
                txtCount.setText("" + count);
            }
        });
    }
}
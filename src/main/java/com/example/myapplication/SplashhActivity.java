package com.example.miniiapp;
import com.example.myapplication.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashhActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect splash XML
        setContentView(R.layout.splashh_activity);

        // Wait for 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            // Open main application
            Intent intent = new Intent(
                    SplashhActivity.this,
                    Miniiapp.class
            );

            startActivity(intent);

            // Close splash screen
            finish();

        }, 3000);
    }
}


package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText name = findViewById(R.id.fullName);
        EditText email = findViewById(R.id.signupEmail);
        EditText password = findViewById(R.id.signupPassword);
        EditText confirmPassword = findViewById(R.id.confirmPassword);

        Button signupButton = findViewById(R.id.signupButton);
        TextView loginLink = findViewById(R.id.backToLogin);

        signupButton.setOnClickListener(v -> {

            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString();
            String confirm = confirmPassword.getText().toString();

            if (userName.isEmpty() || userEmail.isEmpty()
                    || userPassword.isEmpty() || confirm.isEmpty()) {

                Toast.makeText(this, "Please fill all fields",
                        Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {

                Toast.makeText(this, "Enter a valid email address",
                        Toast.LENGTH_SHORT).show();

            } else if (userPassword.length() < 6) {

                Toast.makeText(this, "Password must be at least 6 characters",
                        Toast.LENGTH_SHORT).show();

            } else if (!userPassword.equals(confirm)) {

                Toast.makeText(this, "Passwords do not match",
                        Toast.LENGTH_SHORT).show();

            } else {

                // Save account details on this device for demo purposes
                SharedPreferences prefs = getSharedPreferences(
                        "MaryamWorld", MODE_PRIVATE);

                prefs.edit()
                        .putString("name", userName)
                        .putString("email", userEmail)
                        .putString("password", userPassword)
                        .apply();

                Toast.makeText(this, "Account created! Please login.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(
                        SignupActivity.this, LoginSignupApp.class);

                startActivity(intent);
                finish();
            }
        });

        loginLink.setOnClickListener(v -> finish());
    }
}

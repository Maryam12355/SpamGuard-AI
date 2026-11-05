
package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSignupApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showSplash();

        new Handler(Looper.getMainLooper()).postDelayed(
                this::showLogin, 2000);
    }

    private void showSplash() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundColor(Color.rgb(70, 87, 217));

        TextView logo = new TextView(this);
        logo.setText("M");
        logo.setTextSize(65);
        logo.setTextColor(Color.WHITE);
        logo.setGravity(Gravity.CENTER);

        TextView title = new TextView(this);
        title.setText("Maryam World");
        title.setTextSize(25);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);

        layout.addView(logo);
        layout.addView(title);

        setContentView(layout);
    }

    private void showLogin() {
        setContentView(R.layout.activity_login_signup_app);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        Button login = findViewById(R.id.loginButton);
        Button google = findViewById(R.id.googleButton);
        TextView signup = findViewById(R.id.signupLink);

        login.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString();

            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter email and password",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences(
                    "MaryamWorld", MODE_PRIVATE);

            String savedEmail = prefs.getString("email", "");
            String savedPassword = prefs.getString("password", "");
            String savedName = prefs.getString("name", "Maryam");

            if (savedEmail.isEmpty()) {
                Toast.makeText(this, "Please create an account first",
                        Toast.LENGTH_SHORT).show();

            } else if (userEmail.equalsIgnoreCase(savedEmail)
                    && userPassword.equals(savedPassword)) {

                showWelcome(savedName);

            } else {
                Toast.makeText(this, "Incorrect email or password",
                        Toast.LENGTH_SHORT).show();
            }
        });

        google.setOnClickListener(v ->
                Toast.makeText(this,
                        "Google Sign-in is not connected yet",
                        Toast.LENGTH_SHORT).show());

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(
                    LoginSignupApp.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void showWelcome(String name) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(28, 28, 28, 28);
        layout.setBackgroundColor(Color.rgb(245, 247, 255));

        TextView welcome = new TextView(this);
        welcome.setText("Welcome to Maryam World, " + name + "!");
        welcome.setTextSize(27);
        welcome.setTextColor(Color.rgb(32, 36, 72));
        welcome.setGravity(Gravity.CENTER);

        Button logout = new Button(this);
        logout.setText("Logout");
        logout.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(70, 87, 217)));

        layout.addView(welcome);
        layout.addView(logout);

        setContentView(layout);

        logout.setOnClickListener(v -> showLogin());
    }
}

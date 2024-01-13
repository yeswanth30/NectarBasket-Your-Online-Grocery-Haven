package com.nectar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome extends AppCompatActivity {

    private TextView buttonSubmit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUserLoggedIn()) {
                    redirectToMainActivity();
                } else {
                    redirectToLoginActivity();
                }
            }
        });
    }

    private boolean isUserLoggedIn() {

        String authToken = sharedPreferences.getString("token", "");

        return !authToken.isEmpty();
    }

    private void redirectToMainActivity() {
        Intent intent = new Intent(welcome.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void redirectToLoginActivity() {
        Intent intent = new Intent(welcome.this, login.class);
        startActivity(intent);
    }
}

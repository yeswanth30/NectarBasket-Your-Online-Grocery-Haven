package com.nectar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Delay transition to next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after the delay
                startActivity(new Intent(splash.this, welcome.class));
                finish(); // Optional: finish this activity to prevent going back to it on back press
            }
        }, SPLASH_DELAY);
    }
}


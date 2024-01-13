package com.nectar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nectar.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

// MainActivity.java
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        // Set the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentOne()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_item_1) {
                selectedFragment = new FragmentOne();
            } else if (item.getItemId() == R.id.nav_item_2) {
                selectedFragment = new FragmentTwo();
            } else if (item.getItemId() == R.id.nav_item_3) {
                selectedFragment = new FragmentThree();
            } else if (item.getItemId() == R.id.nav_item_4) {
                selectedFragment = new FragmentFour();
            } else if (item.getItemId() == R.id.nav_item_5) {
                selectedFragment = new FragmentFive();
            }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }

                return false;
            }
        };


    }



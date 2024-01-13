package com.nectar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class location extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Spinner yourZoneSpinner = findViewById(R.id.your_zone_spinner);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Spinner yourAreaSpinner = findViewById(R.id.your_area_spinner);

        // Mock data for the Spinners (replace with your actual data)
        List<String> zoneDataList = new ArrayList<>();
        zoneDataList.add("Zone 1");
        zoneDataList.add("Zone 2");
        // ... Add more zones as needed

        List<String> areaDataList = new ArrayList<>();
        areaDataList.add("Area 1");
        areaDataList.add("Area 2");
        // ... Add more areas as needed

        // Set adapters for the Spinners
        ArrayAdapter<String> zoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zoneDataList);
        zoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yourZoneSpinner.setAdapter(zoneAdapter);

        ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areaDataList);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yourAreaSpinner.setAdapter(areaAdapter);

        // Set listeners for the Spinners
        yourZoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedZone = parent.getItemAtPosition(position).toString();
                // Handle the selected zone
                Toast.makeText(location.this, "Selected Zone: " + selectedZone, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when no zone is selected
            }
        });

        yourAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedArea = parent.getItemAtPosition(position).toString();
                // Handle the selected area
                Toast.makeText(location.this, "Selected Area: " + selectedArea, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when no area is selected
            }
        });

        // Handle Submit button click
        TextView buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(location.this, Page3.class);
                startActivity(intent);
            }
        });
    }
}


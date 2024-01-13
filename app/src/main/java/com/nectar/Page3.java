package com.nectar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page3 extends AppCompatActivity {

    TextView google;

    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.page3);

        google = findViewById(R.id.google);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page3.this, login.class);
                startActivity(intent);
            }
        });
    }
}

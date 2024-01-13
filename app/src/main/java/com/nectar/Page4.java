package com.nectar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page4 extends AppCompatActivity {

    ImageView ahead1, back1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.page4);

        ahead1 = findViewById(R.id.ahead1);
        back1 = findViewById(R.id.back1);

        ahead1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page4.this, Page6.class);
                startActivity(intent);
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page4.this, Page3.class);
                startActivity(intent);
            }
        });
    }
}

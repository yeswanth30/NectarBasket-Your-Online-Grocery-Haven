package com.nectar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Page6 extends AppCompatActivity {

    ImageView ahead2, back2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.page6);

        ahead2 = findViewById(R.id.ahead2);
        back2 = findViewById(R.id.back2);

        ahead2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page6.this, MainActivity.class);
                startActivity(intent);
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page6.this, Page4.class);
                startActivity(intent);
            }
        });
    }
}

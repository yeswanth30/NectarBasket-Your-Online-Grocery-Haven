package com.nectar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.PaymentModel;
import com.nectar.Retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAcceptedPage extends AppCompatActivity {

    String token;
    TextView backtohome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderaccepted_page);

        backtohome = findViewById(R.id.backtohome);


        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");

        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderAcceptedPage.this, MainActivity.class);
                startActivity(intent);
            }
        });



}

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


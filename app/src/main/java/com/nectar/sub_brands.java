package com.nectar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sub_brands extends AppCompatActivity {
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    int brandId;
    int defaultValue;
    String brandname;
    private ArrayList<category.itemData> alldata;
    private ArrayList<category.itemData4> alldata1;
    private ArrayList<category.itemData2> alldata2;
    private ArrayList<category.itemData3> alldata3;
    private ArrayList<category.itemData4> alldata4;
    String token;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subbrands);

        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView2 = findViewById(R.id.recyclerView2);
        setRecyclerview();
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        Log.d("TokenCheck", "Retrieved token: " + token);
    }
    private void setRecyclerview() {
        // Retrieve categoryId from SharedPreferences in the next page
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int storedCategoryId = sharedPreferences.getInt("categoryId", defaultValue); // defaultValue is optional

        // Use the retrieved categoryId as needed in this method
        Log.d("YourActivity", "Retrieved Category ID: " + storedCategoryId);

        String authorizationHeader = "Bearer " + token;
        Log.e("brandId","brandId");
        Log.e("brandname","brandname");


        Call<category> call = RetrofitClient.getInstance().getMyApi().product(authorizationHeader, storedCategoryId);

        call.enqueue(new Callback<category>() {
            @Override
            public void onResponse(Call<category> call, Response<category> response) {
                if (response.isSuccessful()) {
                    category categoryResponse = response.body();
                    if (categoryResponse != null && categoryResponse.getData() != null) {
                        alldata = categoryResponse.getData();

                        // Retrieving data for recyclerView11 (itemData4)
                        for (int i = 0; i < alldata.size(); i++) {
                            alldata1 = categoryResponse.getData().get(i).getProductlist();
                        }

                        // Retrieving data for recyclerView12 (itemData2)
                        for (int i = 0; i < alldata.size(); i++) {
                            alldata2 = categoryResponse.getData().get(i).getBrand();
                        }
                        for (int i = 0; i < alldata.size(); i++) {
                            alldata3 = categoryResponse.getData().get(i).getSubcat();
                        }


                        // Set adapter for recyclerView11 (itemData4)
                        deinks_adpo adapter1 = new deinks_adpo(alldata1);
                        recyclerView2.setLayoutManager(new GridLayoutManager(sub_brands.this, 2));
                        recyclerView2.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();

                        // Set adapter for recyclerView12 (itemData2)
                        subCat__Adpoter adapter3 = new subCat__Adpoter(alldata3);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(sub_brands.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView1.setAdapter(adapter3);
                        adapter3.notifyDataSetChanged();


                    }
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {
                Log.e("error", t.getMessage());
                Toast.makeText(sub_brands.this, "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

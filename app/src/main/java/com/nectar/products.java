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

import com.nectar.Retrofitclient.GridSpacingItemDecoration;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class products  extends AppCompatActivity {
    RecyclerView recyclerView1;
    int defaultValue;
    private ArrayList<category.itemData> alldata;
    private ArrayList<category.itemData4> alldata1;
    String token;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        recyclerView1 = findViewById(R.id.recyclerViewVertical);

        setRecyclerview();
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        Log.d("TokenCheck", "Retrieved token: " + token);
    }
    private void setRecyclerview() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int storedCategoryId = sharedPreferences.getInt("categoryId", defaultValue); // defaultValue is optional

        // Use the retrieved categoryId as needed in this method
        Log.d("YourActivity", "Retrieved Category ID: " + storedCategoryId);
        String authorizationHeader = "Bearer " + token;

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



                        // Set adapter for recyclerView11 (itemData4)


                        // Set adapter for recyclerView12 (itemData2)


//                        deinks_adpo adapter1 = new deinks_adpo(alldata1);

//                        // Assuming recyclerView is your RecyclerView instance
                        deinks_adpo adapter1 = new deinks_adpo(alldata1);
                        // After setting the adapter for your RecyclerView
                        int spanCount = 2; // Number of columns
//                        int spacingInPixels = 50; // Spacing in pixels
                        boolean includeEdge = true; // Include spacing at the edges

//                        recyclerView1.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));


                        recyclerView1.setLayoutManager(new GridLayoutManager(products.this, 2)); // 2 columns

// Set the adapter to your RecyclerView

                        recyclerView1.setAdapter(adapter1);

                        adapter1.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {
                Log.e("error", t.getMessage());
                Toast.makeText(products.this, "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}


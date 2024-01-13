package com.nectar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.search_adapter;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.search_model_Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search extends AppCompatActivity {
    ImageView imageView;
    EditText search;
    TextView textView3, searchbutton2;
    RecyclerView recyclerView3;
//    ArrayAdapter<search_model_Response.searchdata>res;
    String token;
    private ArrayList<search_model_Response.searchdata> res;
    private ArrayList<search_model_Response.imagelist> imagelist;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        // Initialize views
        imageView = findViewById(R.id.imageView);
        textView3 = findViewById(R.id.textView3);
        search = findViewById(R.id.search);
        searchbutton2 = findViewById(R.id.searchbutton2);
        recyclerView3 = findViewById(R.id.recyclerView3); // Replace with your ListView ID

        // Create an empty ArrayList for search results
//        res = new ArrayList<>();

        // Create ArrayAdapter and set it to the ListView


        // Setting the click listener for the search button


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not used, but required method
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // String searchText = charSequence.toString().toLowerCase();
                performSearch(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not used, but required method
            }
        });

        if(this.search.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }

        // Retrieve token from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        Log.d("TokenCheck", "Retrieved token: " + token);
    }

    private void performSearch(String searchQuery) {


        // Your Retrofit API call for search...
        Call<search_model_Response> call = RetrofitClient.getInstance().getMyApi().search(searchQuery);
        call.enqueue(new Callback<search_model_Response>() {
            @Override
            public void onResponse(Call<search_model_Response> call, Response<search_model_Response> response) {
                if (response.isSuccessful()) {
                    search_model_Response result = response.body();
                    res= result.getSearch();
                    imagelist = result.getImages();
//                    for(search_model_Response.searchdata res1:res){
//                        String name= res1.getName();
//                        Log.e("name",name);
//                    }
                    search_adapter adapter1 = new search_adapter(search.this, res, imagelist);
                    recyclerView3.setAdapter(adapter1);
                    //adapter1.updateData(res, imagelist); // Call the updateData method here
                    adapter1.notifyDataSetChanged();

                    // Handle successful response here
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(search.this, "Search failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<search_model_Response> call, Throwable t) {
                Toast.makeText(search.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Other methods and functionalities can go here...
}

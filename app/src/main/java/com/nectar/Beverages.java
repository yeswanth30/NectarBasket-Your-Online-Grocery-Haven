package com.nectar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.CategoryNamesAdapter;
import com.nectar.Adapters.FilterAdapter;
import com.nectar.Adapters.FilterNamesAdapter;
import com.nectar.BrandAdopter;
import com.nectar.R;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.FilterSearchRequest;
import com.nectar.Retrofitclient.FilterSearchapply;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.category;
import com.nectar.Retrofitclient.search_model_Request;
import com.nectar.Retrofitclient.search_model_Response;
import com.nectar.deinks_adpo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Beverages extends AppCompatActivity implements FilterNamesAdapter.checkboxClickListener, CategoryNamesAdapter.checkboxClickListener {    RecyclerView recyclerView11;
    RecyclerView recyclerView12;
    RecyclerView recyclerView13,recyclerView20,recyclerView21,recyclerView30;
    ImageView rightIcon,left;
    TextView headingTextView;
    int categoryId;
    String categoryName;
    TextView searchbutton2;
    private FilterAdapter filtadap;
    private FilterNamesAdapter namesofbrands;
    private CategoryNamesAdapter namesofcategory;
    private ArrayList<FilterSearchRequest.image> alldata8;
    CheckBox categorycheckBox;
    private ArrayList<FilterSearchRequest.filterproducts> alldata6;


    private ArrayList<FilterSearchRequest.products> alldata7;

    TextView applyfilter;
    RelativeLayout sideMenuLayout;
    private ArrayList<category.itemData> alldata;
    private ArrayList<category.itemData4> alldata1;
    private ArrayList<category.itemData2> alldata2;
    private ArrayList<category.itemData3> alldata3;
    private ArrayList<search_model_Response.searchdata> res;
    private ArrayList<FilterSearchRequest.products> alldata5;
    private deinks_adpo deinksAdapter;
    private BrandAdopter brandAdapter;
    private subCat__Adpoter subCatAdapter;


    String token;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beverages);
        categorycheckBox=findViewById(R.id.categorycheckBox);

        SpannableString content = new SpannableString("Brands");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

        TextView textView = findViewById(R.id.recyclerView1Heading);
        textView.setText(content);

        SpannableString content1 = new SpannableString("Sub Category");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        TextView textView1 = findViewById(R.id.recyclerView2Heading);
        textView1.setText(content1);



        SpannableString content2 = new SpannableString("Products");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);

        TextView textView2 = findViewById(R.id.recyclerView3Heading);
        textView2.setText(content2);

        headingTextView = findViewById(R.id.headingTextView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String categoryName = bundle.getString("categoryName", "");

            // Set the top category name to the headingTextView
            if (categoryName != null) {
                headingTextView.setText(categoryName);
            } else {
                // Handle the case where topCategoryName is null
            }
        } else {
            // Handle the case where the bundle might be null or key not found
        }
        recyclerView11 = findViewById(R.id.recyclerView3);
        recyclerView12 = findViewById(R.id.recyclerView1);
        recyclerView13 = findViewById(R.id.recyclerView2);
        recyclerView20 = findViewById(R.id.recyclerView20);
        left = findViewById(R.id.left);
        recyclerView21 = findViewById(R.id.recyclerView21);
        recyclerView30 = findViewById(R.id.recyclerView30);
        rightIcon = findViewById(R.id.rightIcon12);
        applyfilter = findViewById(R.id.applyfilter);
        sideMenuLayout = findViewById(R.id.sideMenuLayout);
        setRecyclerview();


//        rightIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Toggle side menu visibility on right icon click
//                if (sideMenuLayout.getVisibility() == View.VISIBLE) {
//                    sideMenuLayout.setVisibility(View.GONE);
//                } else {
//                    sideMenuLayout.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        searchbutton2 = findViewById(R.id.searchbutton2); // Assuming you have a Button with ID searchButton

        searchbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next activity
                Intent intent = new Intent(Beverages.this, search.class);
                startActivity(intent);
                // Finish current activity if needed
                // finish();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next activity
                Intent intent = new Intent(Beverages.this, FragmentTwo .class);
                startActivity(intent);
                // Finish current activity if needed
                // finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        Log.d("TokenCheck", "Retrieved token: " + token);
    }


    // Method to perform search API call



    private void setRecyclerview() {
        Bundle bundle = getIntent().getExtras();
        int categoryId = bundle.getInt("categoryId");

        String categoryName = bundle.getString("categoryName", "");
        String authorizationHeader = "Bearer " + token;
        // Store categoryId in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("categoryId", categoryId);
        editor.apply();

        Log.d("Beverages", "Category ID: " + categoryId);
        Log.d("Beverages", "Category Name: " + categoryName);
        categorycheckBox.setText(categoryName);
        Call<category> call = RetrofitClient.getInstance().getMyApi().product(authorizationHeader,categoryId );

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
                        recyclerView11.setLayoutManager(new GridLayoutManager(Beverages.this, 2)); // 2 columns

// Set the adapter to your RecyclerView

                        recyclerView11.setAdapter(adapter1);

                        adapter1.notifyDataSetChanged();

                        // Set adapter for recyclerView12 (itemData2)
                        BrandAdopter adapter2 = new BrandAdopter(alldata2);
                        recyclerView12.setLayoutManager(new LinearLayoutManager(Beverages.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView12.setAdapter(adapter2);
                        adapter2.notifyDataSetChanged();

                        subCat__Adpoter adapter3 = new subCat__Adpoter(alldata3);
                        recyclerView13.setLayoutManager(new LinearLayoutManager(Beverages.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView13.setAdapter(adapter3);
                        adapter2.notifyDataSetChanged();

                        namesofbrands = new FilterNamesAdapter(alldata2, getApplicationContext(), Beverages.this);
                        recyclerView20.setLayoutManager(new LinearLayoutManager(Beverages.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView20.setAdapter(namesofbrands);
                        namesofbrands.notifyDataSetChanged();

                        namesofcategory = new CategoryNamesAdapter(alldata3, getApplicationContext(), Beverages.this);
                        recyclerView21.setLayoutManager(new LinearLayoutManager(Beverages.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView21.setAdapter(namesofcategory);
                        namesofcategory.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<category> call, Throwable t) {
                Log.e("error", t.getMessage());
                Toast.makeText(Beverages.this, "An error has occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sideMenuLayout.getVisibility() == View.VISIBLE) {
                    sideMenuLayout.setVisibility(View.GONE);
                } else {
                    sideMenuLayout.setVisibility(View.VISIBLE);

                    SeekBar priceSeekBar = findViewById(R.id.priceSeekBar);
                    TextView priceRangeTextView = findViewById(R.id.priceRangeTextView);

                    priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            String priceRange = "Price Range: 0 - " + progress;
                            priceRangeTextView.setText(priceRange);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                }
            }

        });
        applyfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar priceSeekBar = findViewById(R.id.priceSeekBar);
                int selectedPrice = priceSeekBar.getProgress();

                int selectedpricemin = 0;

                // ArrayList<Integer> selectedBrands = namesofbrands.getSelectedBrands();

                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                String Brand = sharedpreferences.getString("BrandId", "");
                String SubCategory = sharedpreferences.getString("SubCategoryId", "");
                Log.e("Brand", Brand + "," + selectedPrice + "," + SubCategory + "," + categoryId + "," + selectedpricemin);
                Log.e("tokenn", token);

                HashMap<String, String> queryParams = new HashMap<>();
                queryParams.put("subcat", SubCategory);
                queryParams.put("brand", Brand);
                queryParams.put("maximum_price", String.valueOf(selectedPrice));
                queryParams.put("cat", String.valueOf(categoryId));
                queryParams.put("minimum_price", String.valueOf(selectedpricemin));

              //  FilterSearchapply applyfilter = new FilterSearchapply(Brand, selectedPrice, categoryId, SubCategory, selectedpricemin);
                FilterSearchapply fil = new FilterSearchapply();
                fil.setMinimum_price(selectedpricemin);
                fil.setMaximum_price(selectedPrice);
                fil.setCat(categoryId);
                fil.setSubcat(SubCategory);
                fil.setBrand(Brand);

                Call<FilterSearchRequest.filterproducts> filterCall = RetrofitClient.getInstance().getMyApi().filterSearch(authorizationHeader, fil);
                filterCall.enqueue(new Callback<FilterSearchRequest.filterproducts>() {
                    @Override
                    public void onResponse(Call<FilterSearchRequest.filterproducts> call, Response<FilterSearchRequest.filterproducts> response) {
                        if (response.isSuccessful()) {
                            FilterSearchRequest.filterproducts filterResponse = response.body();
                            if (filterResponse != null && filterResponse.getProducts() != null) {

                                alldata7 = filterResponse.getProducts();
                                alldata8 = filterResponse.getImage();

                                for (int i = 0; i < alldata7.size(); i++) {
                                    String data = alldata7.get(i).getCatname();
                                    Log.e("items ka data", data);
                                }

                                for (int i = 0; i < alldata8.size(); i++) {
                                    String datax = alldata8.get(i).getName();
                                    Log.e("items ka data", datax);
                                }


                                filtadap = new FilterAdapter(alldata7, alldata8);
                                recyclerView30.setLayoutManager(new LinearLayoutManager(Beverages.this, LinearLayoutManager.HORIZONTAL, false));
                                recyclerView30.setAdapter(filtadap);
                                filtadap.notifyDataSetChanged();

                                sideMenuLayout.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(Beverages.this, "Filter search failed", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<FilterSearchRequest.filterproducts> call, Throwable t) {
                        Toast.makeText(Beverages.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    @Override
    public void onCheckboxClickedb(int itemId) {
        String brandName = namesofbrands.getBrandName(itemId);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("BrandId", String.valueOf(itemId));
        editor.putString("BrandName", brandName);
        editor.apply();

        Log.e("Checkbox Clicked", "Item ID: " + itemId + ", Brand Name: " + brandName);
    }

    @Override
    public void onCheckboxClickedc(int subcatid) {

        String subcategoryName = namesofcategory.getCategoryName(subcatid);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("SubCategoryId", String.valueOf(subcatid));
        editor.putString("SubCategoryName", subcategoryName);
        editor.apply();

        Log.e("Checkbox Clicked", "Item ID:" + subcatid + ", SubCategoryName: " + subcategoryName);
    }
}

package com.nectar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.nectar.Adapters.ImageAdapter;
import com.nectar.Adapters.RelatedproductsAdapter;
import com.nectar.Dbhelper.dphelper;
import com.nectar.Retrofitclient.ProductDetailModel;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.mycartmodel;
import com.nectar.Retrofitclient.mylikemodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreDetails extends AppCompatActivity {

    TextView productkaname, addtobasket, amount, describe, amount1, keyfeatures, overview;
    ImageView back1, upload, like;
    dphelper dphelpers;
    RecyclerView recyclerView8, recyclerView11;
    ImageAdapter imageAdapter;

    RelatedproductsAdapter related;
    private ArrayList<ProductDetailModel.Productsubmodels> alldata;
    private ProductDetailModel.productlists alldata1;
    private ArrayList<ProductDetailModel.images> alldata2;
    private ArrayList<ProductDetailModel.relatedproducts> alldata7;
    private ArrayList<ProductDetailModel.relatedproductsimages> alldata8;

    int clickedItemId, like_pid, like_cid, categoryId, productId, categorykiid, i;
    String image, finalprice;
    private ViewPager viewPager;

    boolean isLiked = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addto_cart);
        viewPager = findViewById(R.id.viewPager);
//        recyclerView8 = findViewById(R.id.recyclerView8);
        recyclerView11 = findViewById(R.id.recyclerView11);
        amount1 = findViewById(R.id.amount1);
        productkaname = findViewById(R.id.productkaname);
        productkaname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the full name in a Toast message
                Toast.makeText(MoreDetails.this, productkaname.getText(), Toast.LENGTH_LONG).show();
            }
            });
                addtobasket = findViewById(R.id.addtobasket);
                amount = findViewById(R.id.amount);
                describe = findViewById(R.id.expandableTextView);
                keyfeatures = findViewById(R.id.keyfeatures);
        final TextView headingTextView = findViewById(R.id.headingTextView);
        final TextView headingTextView4 = findViewById(R.id.headingTextView4);
        final CardView cardView = findViewById(R.id.cardView);
        final TextView expandableTextView = findViewById(R.id.expandableTextView);

        final TextView headingTextView1 = findViewById(R.id.headingTextView1);
        final CardView cardView1 = findViewById(R.id.cardView1);
        final TextView expandableTextView1 = findViewById(R.id.keyfeatures);

        final TextView headingTextView2 = findViewById(R.id.headingTextView2);
        final CardView cardView2 = findViewById(R.id.cardView2);
        final TextView expandableTextView2 = findViewById(R.id.overview);

                overview = findViewById(R.id.overview);
                back1 = findViewById(R.id.back1);

                like = findViewById(R.id.like);
                dphelpers = new dphelper(MoreDetails.this);

                Bundle extras = getIntent().getExtras();
                clickedItemId = extras.getInt("id");
                categorykiid = extras.getInt("category_id");
                image = extras.getString("image");
                finalprice = extras.getString("finalprice");
                like_pid = extras.getInt("getProduct_id_like");
                like_cid = extras.getInt("Cat_id_like");
                productId = clickedItemId;
                categoryId = categorykiid;

                updateLikeSymbol();

                addtobasket.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = productkaname.getText().toString();
                        String price = amount.getText().toString();

                        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                        int userId = sharedpreferences.getInt("userID", -1);
                        Log.e("user ki id", String.valueOf(userId));

                        long times = System.currentTimeMillis();
                        SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
                        String formats = dates.format(new Date(times));

                        Log.e("MoreDetails", "Name: " + name + ", Price: " + price);
                        Log.e("MoreDetail", categorykiid + "," + clickedItemId);
                        mycartmodel cartdata = new mycartmodel(name, price,clickedItemId, categorykiid, "1", image, finalprice, userId);

                        dphelpers.addcart(cartdata, formats, String.valueOf(userId));

                        Toast.makeText(MoreDetails.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                    }
                });

                like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = productkaname.getText().toString();
                        String price = amount.getText().toString();

                        Log.e("MoreDetails", "Name: " + name + ", Price: " + price);
                        Log.e("MoreDetail", categorykiid + "," + clickedItemId);

                        if (!isLiked) {
                            mylikemodel cartdata = new mylikemodel(name, price, categorykiid, clickedItemId, "1", image);
                            dphelpers.addlike(cartdata);
                            Toast.makeText(MoreDetails.this, "Item Liked", Toast.LENGTH_SHORT).show();
                        } else {
                            dphelpers.deleteLikeItem(clickedItemId);
                            Toast.makeText(MoreDetails.this, "Item Unliked", Toast.LENGTH_SHORT).show();
                        }

                        isLiked = !isLiked;
                        updateLikeSymbol();
                    }
                });

                Call<ProductDetailModel> call = RetrofitClient.getInstance().getMyApi()
                        .productitem("your_authorization_header", categoryId, productId);

                call.enqueue(new Callback<ProductDetailModel>() {
                    @Override
                    public void onResponse(Call<ProductDetailModel> call, Response<ProductDetailModel> response) {
                        if (response.isSuccessful()) {
                            Log.e("you", "");
                            ProductDetailModel productDetailModel = response.body();
                            if (productDetailModel != null && productDetailModel.getData() != null) {
                                alldata = productDetailModel.getData();

                                for (int i = 0; i < alldata.size(); i++) {
                                    alldata1 = productDetailModel.getData().get(i).getProductlist();
                                    alldata2 = productDetailModel.getData().get(i).getImage();
                                }

                                for (int i = 0; i < alldata.size(); i++) {
                                    alldata7 = productDetailModel.getData().get(i).getRelated_products();
                                }

                                for (int i = 0; i < alldata.size(); i++) {
                                    alldata8 = productDetailModel.getData().get(i).getRelated_products_images();
                                }

                                productkaname.setText(alldata1.getName());
                                amount.setText(alldata1.getSelling_price());
                                amount1.setText(alldata1.getMrp_price());
                                amount1.setPaintFlags(amount1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                                describe.setText(Html.fromHtml(alldata1.getDescription().toString()));
                                final String originalHeadingText = headingTextView.getText().toString();
                                final String originalHeadingText4 = headingTextView4.getText().toString();
                                headingTextView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (cardView.getVisibility() == View.VISIBLE) {
                                            cardView.setVisibility(View.GONE);
                                            headingTextView.setText(originalHeadingText);
                                        } else {
                                            cardView.setVisibility(View.VISIBLE);
                                            headingTextView4.setText(originalHeadingText4);
                                            headingTextView.setText(describe.getText().toString());
                                        }
                                    }
                                });

                                keyfeatures.setText(Html.fromHtml(alldata1.getFeture().toString()));
                                final String originalHeadingText1 = headingTextView1.getText().toString();
                                headingTextView1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (cardView1.getVisibility() == View.VISIBLE) {
                                            cardView1.setVisibility(View.GONE);
                                            headingTextView1.setText(originalHeadingText1);
                                        } else {
                                            cardView1.setVisibility(View.VISIBLE);
                                            headingTextView1.setText(keyfeatures.getText().toString());
                                        }
                                    }
                                });

                                overview.setText(Html.fromHtml(alldata1.getOverview().toString()));
                                final String originalHeadingText2 = headingTextView2.getText().toString();
                                headingTextView2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (cardView2.getVisibility() == View.VISIBLE) {
                                            cardView2.setVisibility(View.GONE);
                                            headingTextView2.setText(originalHeadingText2);
                                        } else {
                                            cardView2.setVisibility(View.VISIBLE);
                                            headingTextView2.setText(overview.getText().toString());
                                        }
                                    }
                                });

                                Log.e("data ko bhejo", String.valueOf(productkaname));

                                imageAdapter = new ImageAdapter(alldata2);
                                viewPager.setAdapter(imageAdapter);

                                // Handle manual swiping
                                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                    }

                                    @Override
                                    public void onPageSelected(int position) {
                                        // Handle page selected event if needed
                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int state) {
                                    }
                                });

//                                imageAdapter = new ImageAdapter(alldata2);
//                                recyclerView8.setLayoutManager(new LinearLayoutManager(MoreDetails.this, LinearLayoutManager.HORIZONTAL, false));
//                                recyclerView8.setAdapter(imageAdapter);

                                related = new RelatedproductsAdapter(alldata7);
                                recyclerView11.setLayoutManager(new LinearLayoutManager(MoreDetails.this, LinearLayoutManager.HORIZONTAL, false));
                                recyclerView11.setAdapter(related);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductDetailModel> call, Throwable t) {
                        Log.e("error", t.toString());
                        Toast.makeText(MoreDetails.this, "An error has occurred" + t, Toast.LENGTH_SHORT).show();
                    }
                });

                back1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MoreDetails.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }

            private void updateLikeSymbol() {
                if (isLiked) {
                    like.setImageResource(R.drawable.heart_liked);
                } else {
                    like.setImageResource(R.drawable.heart_unliked);
                }
            }
        }


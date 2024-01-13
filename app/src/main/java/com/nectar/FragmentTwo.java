package com.nectar;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.HomeAdapter;
import com.nectar.Adapters.HomeAdapter1;
import com.nectar.Adapters.HomeAdapter2;
import com.nectar.Adapters.HomeAdapter3;
import com.nectar.Adapters.HomeAdapter4;
import com.nectar.Retrofitclient.GridSpacingItemDecoration;
import com.nectar.Retrofitclient.Homemodel;
import com.nectar.Retrofitclient.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTwo extends Fragment {
    String token;
    EditText searchbutton2;
    RecyclerView recyclerView;
    HomeAdapter2 adapter2;
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6;
    private Homemodel.Homesubmodels alldata;
    private ArrayList<Homemodel.firstproductslider> alldata1 = new ArrayList<>();
    private ArrayList<Homemodel.secondproductlist> alldata2 = new ArrayList<>();
    private ArrayList<Homemodel.topcategory> alldata3 = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttwo, container, false);

        // Use getActivity() to access shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        // Find the ImageView by ID
        ImageView imageView = view.findViewById(R.id.imageView);
        searchbutton2 = view.findViewById(R.id.searchbutton2);
        recyclerView = view.findViewById(R.id.recyclerView);
//        linear1 = view.findViewById(R.id.linear1);
//        linear2 = view.findViewById(R.id.linear2);
//        linear3 = view.findViewById(R.id.linear3);
//        linear4 = view.findViewById(R.id.linear4);
//        linear5 = view.findViewById(R.id.linear5);
//        linear6 = view.findViewById(R.id.linear6);


        // Set onClickListener for the ImageView
//        searchbutton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Beverages!", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });
//        linear6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle ImageView click action here
//                // Show a toast message
//                Toast.makeText(getActivity(), "Brands", Toast.LENGTH_SHORT).show();
//
//                // Start the NextActivity
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                startActivity(intent);
//            }
//        });


        Call<Homemodel> call = RetrofitClient.getInstance().getMyApi().allproduct();
        call.enqueue(new Callback<Homemodel>() {
            @Override
            public void onResponse(Call<Homemodel> call, Response<Homemodel> response) {
                if (response.isSuccessful()) {
                    Log.e("you", "");
                    Homemodel myheroList = response.body();
                    if (myheroList != null && myheroList.getData() != null) {
                        alldata = myheroList.getData();
                        alldata1 = alldata.getFirst_product_list();
                        alldata2 = alldata.getSecond_product_list();
                        alldata3 = alldata.getTop_category();




                        adapter2 = new HomeAdapter2(alldata3);
                        int spanCount = 2; // Number of columns
                      int spacingInPixels = 50; // Spacing in pixels
                        boolean includeEdge = true; // Include spacing at the edges

                       recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacingInPixels, true));
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2)); // 2 columns
                        recyclerView.setAdapter(adapter2);


                    }
                }
            }

            @Override
            public void onFailure(Call<Homemodel> call, Throwable t) {
                Log.e("error t", t.toString());
                Log.e("error stack trace", Log.getStackTraceString(t));
                Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}

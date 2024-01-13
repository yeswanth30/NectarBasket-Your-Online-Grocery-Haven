package com.nectar;

import static android.content.Context.MODE_PRIVATE;

import static androidx.appcompat.app.AppCompatDelegate.create;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.HomeAdapter;
import com.nectar.Adapters.HomeAdapter1;
import com.nectar.Adapters.HomeAdapter2;
import com.nectar.Adapters.HomeAdapter3;
import com.nectar.Adapters.HomeAdapter4;
import com.nectar.R;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.Homemodel;
import com.nectar.Retrofitclient.RetrofitClient;

import com.nectar.Retrofitclient.search_model_Request;
import com.nectar.Retrofitclient.search_model_Response;
import com.nectar.databinding.FragmentoneBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentOne extends Fragment {

    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView5, recyclerView6;
    EditText searchBar;
    TextView seeall1, seeall2, seeall3;
    ImageView carticon;

    String token;

    HomeAdapter adapter;
    HomeAdapter1 adapter1;
    HomeAdapter2 adapter2;
    HomeAdapter3 adapter3;
    HomeAdapter4 adapter4;

    private Homemodel.Homesubmodels alldata;
    private ArrayList<Homemodel.firstproductslider> alldata1 = new ArrayList<>();
    private ArrayList<Homemodel.secondproductlist> alldata2 = new ArrayList<>();
    private ArrayList<Homemodel.topcategory> alldata3 = new ArrayList<>();
    private ArrayList<Homemodel.homeslider> alldata4 = new ArrayList<>();
    private ArrayList<Homemodel.secondcategoryslider> alldata5 = new ArrayList<>();


    private FragmentoneBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentoneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView1 = root.findViewById(R.id.recyclerView1);
        recyclerView2 = root.findViewById(R.id.recyclerView2);
        recyclerView3 = root.findViewById(R.id.recyclerView3);
        recyclerView5 = root.findViewById(R.id.recyclerView5);
        recyclerView6 = root.findViewById(R.id.recyclerView6);
        carticon = root.findViewById(R.id.carticon);

//        seeall2 = root.findViewById(R.id.seeall2);
//        seeall1 = root.findViewById(R.id.seeall1);
//        seeall3 = root.findViewById(R.id.seeall3);
//        seeall3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Beverages.class);
//                // Add any extras or data to pass to the next activity if needed
//                // intent.putExtra("key", value);
//                startActivity(intent);
//            }
//        });
        carticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                // Add any extras or data to pass to the next activity if needed
                // intent.putExtra("key", value);
                startActivity(intent);
            }
        });
//        seeall1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), FragmentTwo.class);
//                // Add any extras or data to pass to the next activity if needed
//                // intent.putExtra("key", value);
//                startActivity(intent);
//            }
//        });
//        searchBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchQuery = searchBar.getText().toString().trim();
//
//                if (!searchQuery.isEmpty()) {
//                    search_model_Request requestModel = new search_model_Request(searchQuery); // Create your request model
//                    performSearch(requestModel);
//                } else {
//                    Toast.makeText(getContext(), "Enter a search query", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        Log.e("token", token);

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
                        alldata4 = alldata.getHome_slider();
                        alldata5 = alldata.getSecond_category_slider();


                        adapter = new HomeAdapter(alldata1);
                        recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView1.setAdapter(adapter);

                        adapter1 = new HomeAdapter1(alldata2);
                        recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView2.setAdapter(adapter1);

                        adapter2 = new HomeAdapter2(alldata3);
                        recyclerView3.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView3.setAdapter(adapter2);

                        adapter3 = new HomeAdapter3(alldata4);
                        recyclerView5.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView5.setAdapter(adapter3);

                        adapter4 = new HomeAdapter4(alldata5);
                        recyclerView6.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                        recyclerView6.setAdapter(adapter4);
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
        return root;
    }

//    private void performSearch(search_model_Request requestModel) {
//        // Make the API call
//        Call<search_model_Response> call = RetrofitClient.getInstance().getMyApi().search(requestModel);
//        call.enqueue(new Callback<search_model_Response>() {
//            @Override
//            public void onResponse(Call<search_model_Response> call, Response<search_model_Response> response) {
//                if (response.isSuccessful()) {
//                    search_model_Response result = response.body();
//                    // Handle successful response here
//                    Log.d("Search", "Search successful. Results: " + result.toString());
//                } else {
//                    // Handle unsuccessful response
//                    Toast.makeText(getContext(), "Search failed", Toast.LENGTH_SHORT).show();
//                    Log.e("Search", "Search failed. Response code: " + response.code());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<search_model_Response> call, Throwable t) {
//                // Handle failure
//                Toast.makeText(getContext(), "An error occurred: " + t.getMessage(), Toast.LENGTH_LONG).show();
//                Log.e("Search", "Search request failed", t);
//            }
//        });


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

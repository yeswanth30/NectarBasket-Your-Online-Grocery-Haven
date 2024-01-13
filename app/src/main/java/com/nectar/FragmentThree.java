package com.nectar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.caryadapter;
import com.nectar.Dbhelper.dphelper;
import com.nectar.Retrofitclient.mycartmodel;
import com.nectar.Retrofitclient.mycartmodel12;

import java.util.ArrayList;
import java.util.List;

public class FragmentThree extends Fragment {

    private RecyclerView recyclerView;
    private caryadapter adapter;
    private List<mycartmodel> cartList = new ArrayList<>();
    private dphelper databaseHelperClass;
    private TextView checkout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentthree, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        checkout = view.findViewById(R.id.checkout);
//
//        // Assuming you have stored the user ID in SharedPreferences
//        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//        int userId = sharedpreferences.getInt("userid", -1);

        databaseHelperClass = new dphelper(getActivity());

        // Use the retrieved userId to get the cart items
        cartList = databaseHelperClass.getcarting();

        adapter = new caryadapter(cartList, getActivity());
        recyclerView.setAdapter(adapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if cartList is empty
                if (cartList.isEmpty()) {
                    // Display a Toast message indicating no items in the cart
                    Toast.makeText(getContext(), "There is no item in the cart. Please add an item.", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to the CheckoutPayment activity
                    Intent intent = new Intent(v.getContext(), CheckoutPayment.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}

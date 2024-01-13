package com.nectar;

// OrderFragment.java
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.OrderAdapter;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem1;

import java.util.List;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderItem1> orderItemList;
    private SharedPreferences sharedpreferences;


    public OrderFragment(List<OrderItem1> orderItemList) {
        this.orderItemList = orderItemList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.orderRecyclerView);
        orderAdapter = new OrderAdapter(getActivity(), orderItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(orderAdapter);

        return view;
    }
}

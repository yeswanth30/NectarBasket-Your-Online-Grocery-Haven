package com.nectar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.ClosedOrderAdapter;
import com.nectar.Adapters.OrderAdapter;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem3;

import java.util.List;

public class ClosedOrderFragment extends Fragment {

    private RecyclerView recyclerView;

    private List<OrderItem3> closedOrderItemList;

    public ClosedOrderFragment(List<OrderItem3> closedOrderItemList) {
        this.closedOrderItemList = closedOrderItemList;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_closed_order, container, false);

        recyclerView = view.findViewById(R.id.closedOrderRecyclerView);

        ClosedOrderAdapter closedOrderAdapter = new ClosedOrderAdapter(getActivity(),closedOrderItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(closedOrderAdapter);

        return view;
    }
}

package com.nectar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.OrderAdapter;
import com.nectar.Adapters.ProcessingOrderAdapter;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem2;

import java.util.List;

public class ProcessingOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<OrderItem2> processingOrderItemList;

    public ProcessingOrderFragment(List<OrderItem2> processingOrderItemList) {
        this.processingOrderItemList = processingOrderItemList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processing_order, container, false);

        recyclerView = view.findViewById(R.id.processingOrderRecyclerView);

        ProcessingOrderAdapter processingOrderAdapter = new ProcessingOrderAdapter(getActivity(),processingOrderItemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(processingOrderAdapter);

        return view;
    }
}

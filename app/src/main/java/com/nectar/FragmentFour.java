package com.nectar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.like_adapter;
import com.nectar.Dbhelper.dphelper;
import com.nectar.Retrofitclient.mylikemodel;

import java.util.ArrayList;
import java.util.List;

public class FragmentFour extends Fragment {

    private RecyclerView recyclerView;
    private like_adapter adapter;
    private List<mylikemodel> likeList = new ArrayList<>();
    private dphelper databaseHelperClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentfour, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseHelperClass = new dphelper(getActivity());

        // Retrieve data from the database
//        likeList = databaseHelperClass.getlike();

        List<mylikemodel> likedItems = databaseHelperClass.getlike();

        adapter = new like_adapter(likedItems, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }
}

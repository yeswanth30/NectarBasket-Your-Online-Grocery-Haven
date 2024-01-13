package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.R;
import com.nectar.Retrofitclient.Homemodel;
import com.squareup.picasso.Picasso;
//import com.uipart.Models.data;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter4 extends RecyclerView.Adapter<HomeAdapter4.ViewHolder> {

    private ArrayList<Homemodel.secondcategoryslider> secondcategory;
    private Context context;

    public HomeAdapter4(ArrayList<Homemodel.secondcategoryslider> secondcategory) {
        this.secondcategory = secondcategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_layout4, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Homemodel.secondcategoryslider datas = secondcategory.get(position);
//        Log.d("HomeAdapter", "Product Name: " + datas.getCatname());
//        holder.productnames.setText(String.valueOf(datas.getCatname()));

        Picasso.get()
                // Get image URL from your item
                .load("http://13.232.168.157/images/uploads/"+ datas.getImage()) // Get image URL from your item
//                .placeholder(R.drawable.cola) // Placeholder image while loading (optional)
//                .error(R.drawable.error_image) // Error image if loading fails (optional)
                .into(holder.productphoto);

    }

    @Override
    public int getItemCount() {
        return secondcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productphoto;
        TextView productnames;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productphoto = itemView.findViewById(R.id.productphoto);
            productnames = itemView.findViewById(R.id.productname);
        }
    }
}

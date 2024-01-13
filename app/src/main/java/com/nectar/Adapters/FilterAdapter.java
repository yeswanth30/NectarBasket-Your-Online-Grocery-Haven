package com.nectar.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nectar.R;
import com.nectar.Retrofitclient.FilterSearchRequest;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.category;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.CardViewHolder> {
    private ArrayList<FilterSearchRequest.products> filterlist;
    private ArrayList<FilterSearchRequest.image> imagelist;

    public FilterAdapter(ArrayList<FilterSearchRequest.products> filterlist, ArrayList<FilterSearchRequest.image> imagelist) {
        this.filterlist = filterlist;
        this.imagelist = imagelist;
    }

    @NonNull
    @Override
    public FilterAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapeer_for_brand, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        FilterSearchRequest.products item = filterlist.get(position);
        FilterSearchRequest.image photo = imagelist.get(position);
        //  Log.e("AdapterLog up", "Item at position ");
//        holder.textView1.setText(String.valueOf(item.getId()));
        holder.textView1.setText(String.valueOf(item.getCatname()));
//        holder.textView4.setText(String.valueOf(item.getSelling_price()));
//        holder.textView1.setText("First");
//        holder.textView2.setText("1");
//        holder.textView4.setText("24");
//        String img = "nav_1587366883AWUBn.jpg";

        Picasso.get()
//                // Get image URL from your item
                .load("http://13.232.168.157/images/uploads/"+ photo.getName()) // Get image URL from your item
////                .placeholder(R.drawable.cola) // Placeholder image while loading (optional)
////                .error(R.drawable.error_image) // Error image if loading fails (optional)
                .into(holder.imageView);


        // Add click listeners or other functionality here
    }
    @Override
    public int getItemCount() {
        return filterlist.size() + imagelist.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView imageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.brandname);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

    public void updateData(ArrayList<FilterSearchRequest.products> updatedList) {
        filterlist.clear();
        filterlist.addAll(updatedList);
        notifyDataSetChanged();
    }
}



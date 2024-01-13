package com.nectar;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.category;

import java.util.ArrayList;

public class BrandAdopter extends RecyclerView.Adapter<BrandAdopter.CardViewHolder> {
    private ArrayList<category.itemData2> itemList;

    public BrandAdopter(ArrayList<category.itemData2> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public BrandAdopter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapeer_for_brand, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        category.itemData2 item = itemList.get(position);
       // holder.textView1.setText(String.valueOf(item.getBrandname()));
        int brandId = item.getId();
        String brandname = item.getBrandname();


        Picasso.get()
                .load("http://13.232.168.157/images/uploads/"+item.getBrand_image())
                .into(holder.imageView);

        // Add click listener inside onBindViewHolder
        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), sub_brands.class);
                // Pass the IDs to the next activity using Intent extras
                intent.putExtra("brand_id", brandId);
                intent.putExtra("brand_name", brandname);
                Log.e("yo","brandId");

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView imageView;
        LinearLayout layout1;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
           // textView1 = itemView.findViewById(R.id.brandname);
            imageView = itemView.findViewById(R.id.imageView);
            layout1 = itemView.findViewById(R.id.layout1);
        }
    }
}

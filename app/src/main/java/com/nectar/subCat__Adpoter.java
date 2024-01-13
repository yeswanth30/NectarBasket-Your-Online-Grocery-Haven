package com.nectar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class subCat__Adpoter extends RecyclerView.Adapter<subCat__Adpoter.CardViewHolder> {
    private ArrayList<category.itemData3> itemList;
    public subCat__Adpoter(ArrayList<category.itemData3> itemList){this.itemList = itemList; }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapeer_for_subcat, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subCat__Adpoter.CardViewHolder holder, int position) {




        category.itemData3 item = itemList.get(position);
        //  Log.e("AdapterLog up", "Item at position ");
//        holder.textView1.setText(String.valueOf(item.getId()));
//        holder.textView1.setText(String.valueOf(item.getSubcatname()));
//        holder.textView4.setText(String.valueOf(item.getSelling_price()));
//        holder.textView1.setText("First");
//        holder.textView2.setText("1");
//        holder.textView4.setText("24");

            int cat_id = item.getCatid();
            String subcatname = item.getSubcatname();
        Picasso.get()
                // Get image URL from your item
                .load("http://13.232.168.157/images/uploads/"+item.getSub_image()) // Get image URL from your item
//                .placeholder(R.drawable.cola) // Placeholder image while loading (optional)
//                .error(R.drawable.error_image) // Error image if loading fails (optional)
                .into(holder.imageView);


        // Add click listeners or other functionality here

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent164 = new Intent(holder.itemView.getContext(), products
                        .class);
                intent164.putExtra("cat_id", cat_id);
                intent164.putExtra("subcatname", subcatname);
                holder.itemView.getContext().startActivity(intent164);
            }
        });
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2,textView4;
        ImageView imageView;
        LinearLayout layout1;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
           // textView1 = itemView.findViewById(R.id.subcat);
//            textView2 = itemView.findViewById(R.id.textView2);
//            textView4 = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView);
            layout1 = itemView.findViewById(R.id.layout1);

        }
    }
}




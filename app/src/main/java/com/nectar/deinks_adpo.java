package com.nectar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.FilterSearchRequest;
import com.nectar.Retrofitclient.category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class deinks_adpo extends RecyclerView.Adapter<deinks_adpo.CardViewHolder> {
    private ArrayList<category.itemData4> itemList;

    public deinks_adpo(ArrayList<category.itemData4> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapperfordrinks, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        int firstProductIndex = position * 2;
        int secondProductIndex = firstProductIndex + 1;

        if (firstProductIndex < itemList.size()) {
            category.itemData4 firstItem = itemList.get(firstProductIndex);
            holder.bind(firstItem);
        }

        if (secondProductIndex < itemList.size()) {
            category.itemData4 secondItem = itemList.get(secondProductIndex);
            holder.bind(secondItem);
        } else {
            holder.hideSecondProduct(); // If there's no second product, hide the second column
        }
    }

    @Override
    public int getItemCount() {
        return (int) Math.ceil(itemList.size() / 2.0); // Return the number of rows (each row contains two columns)
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        RelativeLayout linerrrr1;
        TextView textView4;
        ImageView imageView;
        ImageView addbutton;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView1.setMaxLines(1);
            textView1.setEllipsize(TextUtils.TruncateAt.END); // Ensure to use TextUtils.TruncateAt
            textView1.setText("Your long text goes here...");
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Show the full name in a Toast message
                    Toast.makeText(itemView.getContext(), textView1.getText(), Toast.LENGTH_LONG).show();
                }
            });// Replace R.id.textView1 with your actual TextView IDs
            textView2 = itemView.findViewById(R.id.textView2);
            textView2.setPaintFlags(textView2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
// Replace R.id.textView2 with your actual TextView IDs
            textView4 = itemView.findViewById(R.id.textView4); // Replace R.id.textView4 with your actual TextView IDs
            imageView = itemView.findViewById(R.id.imageView);
            addbutton = itemView.findViewById(R.id.addbutton);
            linerrrr1 = itemView.findViewById(R.id.linerrrr1);
        }

        public void bind(category.itemData4 item) {
            // Bind data to views for the product
            // For example:
            textView1.setText(String.valueOf(item.getName()));
            textView2.setText(String.valueOf(item.getMarp_price()));
            textView4.setText(String.valueOf(item.getSelling_price()));
            Picasso.get()
                    .load("http://13.232.168.157/images/uploads/" + item.getImage())
                    .into(imageView);
            // Set click listeners or other functionality here
            linerrrr1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the Id of the clicked item
                    int clickedItemId = item.getId();
                    int categorykiid = item.getCategory_id();
                    String image = item.getImage();
                    Log.e("item ki id", String.valueOf(clickedItemId));

//                    Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Catgory Id is: " + categorykiid, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(view.getContext(), MoreDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",(item.getId()));
                    bundle.putInt("category_id",(item.getCategory_id()));
                    bundle.putString("image",(item.getImage()));
                    intent.putExtras(bundle);
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void hideSecondProduct() {
            // Hide views related to the second product column
            // For example:
            // Hide the views for the second product in the row
        }
    }
}

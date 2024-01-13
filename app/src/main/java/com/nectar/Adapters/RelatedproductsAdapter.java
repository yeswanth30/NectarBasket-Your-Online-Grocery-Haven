package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
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


import com.nectar.MoreDetails;
import com.nectar.Retrofitclient.ProductDetailModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.nectar.R;

import java.util.ArrayList;

public class RelatedproductsAdapter extends RecyclerView.Adapter<RelatedproductsAdapter.ViewHolder> {

    private ArrayList<ProductDetailModel.relatedproducts> allrelatedList;
//    private ArrayList<ProductDetailModel.relatedproductsimages> allrelatedimages;

    private Context context;

    public RelatedproductsAdapter(ArrayList<ProductDetailModel.relatedproducts> allrelatedList) {
        this.allrelatedList = allrelatedList;
    }

//    public RelatedproductsAdapter(ArrayList<ProductDetailModel.relatedproductsimages> allrelatedimages, Context context) {
//        this.allrelatedimages = allrelatedimages;
//        this.context = context;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductDetailModel.relatedproducts datass = allrelatedList.get(position);
//        ProductDetailModel.relatedproductsimages datasss = allrelatedimages.get(position);
//
//        Picasso.get()
//                // Get image URL from your item
//                .load("http://13.232.168.157/images/uploads/" + datasss.getName()).into(holder.productphoto);


        Log.d("HomeAdapter", "Product Name: " + datass.getName());
        holder.productnames.setText(String.valueOf(datass.getName()));
        holder.mrpprice.setText(String.valueOf(datass.getMrp_price()));
        holder.sellingprice.setText(String.valueOf(datass.getSelling_price()));
        holder.homebutoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the Id of the clicked item
                int clickedItemId = datass.getId();
                int categorykiid = datass.getCategory_id();
                Log.e("item ki id", String.valueOf(clickedItemId));

                //Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Catgory Id is: " + categorykiid, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", (datass.getId()));
                bundle.putInt("category_id", (datass.getCategory_id()));
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allrelatedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productphoto;
        TextView productnames;
        TextView mrpprice, sellingprice;
        TextView productquantity;
        ImageView addbutton,likeIcon;

        RelativeLayout homebutoon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productphoto = itemView.findViewById(R.id.productphoto);
            homebutoon = itemView.findViewById(R.id.homebutoon);
            productnames = itemView.findViewById(R.id.productname);
            productnames.setMaxLines(1);
            productnames.setEllipsize(TextUtils.TruncateAt.END); // Ensure to use TextUtils.TruncateAt
            productnames.setText("Your long text goes here...");
            productnames.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), productnames.getText(), Toast.LENGTH_LONG).show();
                }
            });
            addbutton = itemView.findViewById(R.id.addbutton);
            mrpprice = itemView.findViewById(R.id.mrppricetextview);
            sellingprice = itemView.findViewById(R.id.sellingpricetextview);
        }
    }
}

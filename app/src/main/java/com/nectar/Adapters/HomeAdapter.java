package com.nectar.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.nectar.MoreDetails;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//
//import com.nectar.Moredetails;
import com.nectar.R;
import com.nectar.Retrofitclient.Homemodel;
//import com.uipart.Models.data;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Homemodel.firstproductslider> allproductmodelList;
    private Context context;
    int category_id;

    public HomeAdapter(ArrayList<Homemodel.firstproductslider> allproductmodelList) {
        this.allproductmodelList = allproductmodelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.home_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Homemodel.firstproductslider datas = allproductmodelList.get(position);
        Log.d("HomeAdapter", "Product Name: " + datas.getCatname());
        holder.productnames.setText(String.valueOf(datas.getName()));
        holder.mrpprice.setText(String.valueOf(datas.getMrp_price()));
        holder.sellingprice.setText(String.valueOf(datas.getSelling_price()));
        holder.mrpprice.setPaintFlags(holder.mrpprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.homebutoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedItemId = datas.getId();
                int categorykiid = datas.getCategory_id();
                String image = datas.getImage_path();
                Log.e("item ki id", String.valueOf(clickedItemId));
                Log.e("item ki ", String.valueOf(categorykiid));


                //Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Catgory Id is: " + categorykiid, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",(datas.getId()));
                bundle.putInt("category_id",(datas.getCategory_id()));
                bundle.putString("image",(datas.getImage_path()));
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

//                SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", MODE_PRIVATE);
//                SharedPreferences.Editor myedit = sharedPreferences.edit();
//                myedit.putInt("category_id", categorykiid);
//                myedit.apply();

            }
        });
//        holder.likeIcon.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // Get the Id of the clicked item
//            int clickedItemId = datas.getId();
//            int categorykiid = datas.getCategory_id();
//            Log.e("item ki id", String.valueOf(clickedItemId));
//
//            Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Catgory Id is: " + categorykiid, Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(view.getContext(), MoreDetails.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("id",(datas.getId()));
//            bundle.putInt("category_id",(datas.getCategory_id()));
//            intent.putExtras(bundle);
//            view.getContext().startActivity(intent);
//        }
//    });
        Picasso.get()
                // Get image URL from your item
                .load("http://13.232.168.157/images/uploads/"+ datas.getImage_path()) // Get image URL from your item
//                .placeholder(R.drawable.cola) // Placeholder image while loading (optional)
//                .error(R.drawable.error_image) // Error image if loading fails (optional)
                .into(holder.productphoto);

    }

    @Override
    public int getItemCount() {
        return allproductmodelList.size();
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
                    // Show the full name in a Toast message
                    Toast.makeText(itemView.getContext(), productnames.getText(), Toast.LENGTH_LONG).show();

                    // Or, show the full name in a dialog
            /*AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setMessage(textView1.getText());
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();*/
                }
            });
            addbutton = itemView.findViewById(R.id.addbutton);
            mrpprice = itemView.findViewById(R.id.mrppricetextview);
            sellingprice = itemView.findViewById(R.id.sellingpricetextview);
//            likeIcon  = itemView.findViewById(R.id.likeIcon);
        }
    }
}

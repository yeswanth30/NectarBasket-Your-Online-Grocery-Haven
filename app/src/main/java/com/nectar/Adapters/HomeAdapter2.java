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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Beverages;
import com.nectar.R;
import com.nectar.Retrofitclient.Homemodel;
import com.squareup.picasso.Picasso;
//import com.uipart.Models.data;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.ViewHolder> {

    private ArrayList<Homemodel.topcategory> alltopcategory;
    private Context context;

    public HomeAdapter2(ArrayList<Homemodel.topcategory> alltopcategory) {
        this.alltopcategory = alltopcategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_layout1, parent, false);
        int width = parent.getMeasuredWidth() / 2;
        view.setLayoutParams(new RecyclerView.LayoutParams(width, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Homemodel.topcategory datas = alltopcategory.get(position);
        holder.productnames.setText(String.valueOf(datas.getCatname()));

        Picasso.get()
                .load("http://13.232.168.157/images/uploads/" + datas.getImage())
                .into(holder.productphoto);

        holder.layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Beverages.class);

                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", datas.getId());
                bundle.putString("categoryName", datas.getCatname());
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alltopcategory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productphoto;
        TextView productnames;
        LinearLayout layout1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout1 = itemView.findViewById(R.id.layout1);
            productphoto = itemView.findViewById(R.id.productphoto);
            productnames = itemView.findViewById(R.id.productname);
        }
    }
}

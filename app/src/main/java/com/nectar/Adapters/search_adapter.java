package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.BrandAdopter;
import com.nectar.MoreDetails;
import com.nectar.R;
import com.nectar.Retrofitclient.search_model_Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


//    public search_adapter(Context context, List<search_model_Response.searchdata> list,List<search_model_Response.imagelist> imagelist) {
public class search_adapter extends RecyclerView.Adapter<search_adapter.CardViewHolder> {
    private List<search_model_Response.searchdata> itemList;
    private List<search_model_Response.imagelist> imagelist;
    private Context context;

    public search_adapter(Context context, List<search_model_Response.searchdata> list, List<search_model_Response.imagelist> imagelist) {
        this.itemList = list;
        this.imagelist = imagelist;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_view, parent, false);
        return new CardViewHolder(view);
    }


    public void clear(){
        int size = this.imagelist.size();
        if(size > 0){
            for(int i=0;i< size;i++){
                this.imagelist.remove(0);
            }
            this.notifyItemRangeRemoved(0,size);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        search_model_Response.searchdata currentItem = itemList.get(position);
        search_model_Response.imagelist currentimage = imagelist.get(position);
       // clear();
        String itemName = currentItem.getName();
        holder.textView3.setText(itemName);

        String imageUrl = "http://13.232.168.157/images/uploads/" + currentimage.getName();
        Log.d("Image URL", imageUrl); // Logging the image URL to check if it's correct
        Log.d("Item Name", itemName);
        Picasso.get()
                .load(imageUrl)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedItemId = currentItem.getId();
                int categorykiid = currentItem.getCategory_id();
                // Handle item click here
                Log.e("item ki id", String.valueOf(clickedItemId));
                Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Category Id is: " + categorykiid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", currentItem.getId());
                bundle.putInt("category_id", currentItem.getCategory_id());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textView3;
        ImageView imageView;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void updateData(List<search_model_Response.searchdata> newList, List<search_model_Response.imagelist> newImageList) {
        itemList.clear();
        imagelist.clear();
        itemList.addAll(newList);
        imagelist.addAll(newImageList);
        notifyDataSetChanged();
    }

}

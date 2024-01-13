package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Dbhelper.dphelper;
import com.nectar.FragmentThree;
import com.nectar.MoreDetails;
import com.nectar.R;
import com.nectar.Retrofitclient.mylikemodel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class like_adapter extends RecyclerView.Adapter<like_adapter.ViewHolder> {
    List<mylikemodel> employee;
    Context context;
    private dphelper databaseHelperClass;

    public like_adapter(List<mylikemodel> employee, Context context) {
        this.employee = employee;
        this.context = context;
        this.databaseHelperClass = new dphelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.wrapperfor_fav, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final mylikemodel like = employee.get(position);

        holder.Name.setText(like.getProduct_name());

        Picasso.get()
                .load("http://13.232.168.157/images/uploads/" + like.getImage())
                .into(holder.bankIcon);

        holder.amount.setText((like.getPrice()));

//        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    // Remove the item from the list
//                    mylikemodel like = employee.get(position);
//                    int productIdToDelete = like.getProduct_id();
//
//                    // Check if databaseHelperClass is not null before using it
//                    if (databaseHelperClass != null) {
//                        // Delete the item from the database
//                        databaseHelperClass.deleteCartItem1(productIdToDelete);
//
//                        // Remove the item from the list
//                        employee.remove(position);
//                        notifyItemRemoved(position);
//                    } else {
//                        Log.e("like_adapter", "databaseHelperClass is null");
//                    }
//                }
//            }
//        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedItemId = like.getProduct_id();
                int categorykiid = like.getCat_id();
                // Handle item click here
//                Log.e("item ki id", String.valueOf(clickedItemId));
//                Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Category Id is: " + categorykiid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("category_id", like.getProduct_id());
                bundle.putInt("id", like.getCat_id());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });



//        holder.layout11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Handle click event for layout11 here
////                Intent intent = new Intent(context, MoreDetails.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("getProduct_id_like", like.getProduct_id());
//                bundle.putInt("Cat_id_like", like.getCat_id());
//                bundle.putString("productName_like", like.getProduct_name());
//                bundle.putString("price_like", like.getPrice());
//                bundle.putString("image_like", like.getImage());
//                // Add other data to the bundle as needed
//
////                intent.putExtras(bundle);
////                context.startActivity(intent);
//            }
//        });
    }




    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, ml, amount,deleteButton;
        ImageView bankIcon;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Names);
            Name.setMaxLines(1);
            Name.setEllipsize(TextUtils.TruncateAt.END); // Ensure to use TextUtils.TruncateAt
            Name.setText("Your long text goes here...");
            Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), Name.getText(), Toast.LENGTH_LONG).show();
                }
            });
            bankIcon = itemView.findViewById(R.id.bankIcon);
            amount = itemView.findViewById(R.id.price);
            layout = itemView.findViewById(R.id.layout);
            //  deleteButton=itemView.findViewById(R.id.deletebutton123);
        }
    }
}

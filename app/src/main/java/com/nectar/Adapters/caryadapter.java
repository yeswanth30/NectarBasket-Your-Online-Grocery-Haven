package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.nectar.MoreDetails;
import com.nectar.R;
import com.nectar.Retrofitclient.mycartmodel;
import com.nectar.Retrofitclient.mycartmodel12;
import com.squareup.picasso.Picasso;

import java.util.List;

public class caryadapter extends RecyclerView.Adapter<caryadapter.ViewHolder> {
    private List<mycartmodel> cartList;
    private Context context;
    private dphelper databaseHelperClass;
    LinearLayout layout;
    int catid,proid;
    public caryadapter(List<mycartmodel> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
        databaseHelperClass = new dphelper(context);
        calculateTotalAmount();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final mycartmodel cartItem = cartList.get(position);
        Log.d("caryadapter", "Product Name: " + cartItem.getProduct_name());
        Log.d("caryadapter", "Quantity: " + cartItem.getQunatity());
        Log.d("caryadapter", "Price: " + cartItem.getMrp_price());

        holder.productname.setText(cartItem.getProduct_name());
        holder.price.setText(cartItem.getMrp_price());
        Picasso.get()
                .load("http://13.232.168.157/images/uploads/" + cartItem.getImage())
                .into(holder.productphoto);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 proid = cartItem.getProduct_id();
                 catid = cartItem.getCat_id();
                // Handle item click here
//                Log.e("item ki id", String.valueOf(clickedItemId));
                Toast.makeText(view.getContext(), "pro Id: " + proid + "Category Id is: " + catid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("category_id", catid);
                bundle.putInt("id", proid);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAbsoluteAdapterPosition(); // Use getAbsoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Remove the item from the list
                    cartList.remove(position);
                    notifyItemRemoved(position);

                    // Delete the item from the database
                    int productIdToDelete = cartItem.getProduct_id();
                    databaseHelperClass.deleteCartItem(productIdToDelete);
                    calculateTotalAmount();
                }
            }
        });


        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name", cartItem.getProduct_name());
        editor.putString("quantity", cartItem.getQunatity());
        editor.putString("sellingprice", cartItem.getPrice());
        editor.putInt("productid", cartItem.getProduct_id());
        editor.putString("image", cartItem.getImage());
        editor.apply();

        Log.e("product ka name", cartItem.getProduct_name());
        Log.e("product ki quantity", cartItem.getQunatity());
        Log.e("product ki selling price", cartItem.getMrp_price());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void calculateTotalAmount() {
        double totalAmount = 0.0;
        for (mycartmodel item : cartList) {
            double itemPrice = Double.parseDouble(item.getMrp_price());
            totalAmount += itemPrice;
            Log.d("caryadapter", "Item Price: " + itemPrice);
        }

        // Convert the totalAmount to String if needed
        String totalAmountString = String.valueOf(totalAmount);

        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("totalAmount", totalAmountString);
        editor.apply();

        notifyDataSetChanged();

        Log.d("caryadapter", "Total Amount: " + totalAmount);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productname, price;
        ImageView productphoto, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.productname);

            productname.setMaxLines(1);
            productname.setEllipsize(TextUtils.TruncateAt.END); // Ensure to use TextUtils.TruncateAt
            productname.setText("Your long text goes here...");
            productname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Show the full name in a Toast message
                    Toast.makeText(itemView.getContext(), productname.getText(), Toast.LENGTH_LONG).show();

                    // Or, show the full name in a dialog
            /*AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setMessage(textView1.getText());
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();*/
                }
            });
            price = itemView.findViewById(R.id.price);
            productphoto = itemView.findViewById(R.id.productphoto);
            deleteButton = itemView.findViewById(R.id.deletebutton);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

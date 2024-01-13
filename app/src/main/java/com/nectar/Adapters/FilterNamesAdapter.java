package com.nectar.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.R;
import com.nectar.Retrofitclient.category;

import java.util.ArrayList;

public class FilterNamesAdapter extends RecyclerView.Adapter<FilterNamesAdapter.CardViewHolder> {
    private ArrayList<category.itemData2> itemList;
    private ArrayList<Integer> selectedBrands = new ArrayList<>();
    public ArrayList<Integer> getSelectedBrands() {
        return selectedBrands;
    }
    public String getBrandName(int position) {
        if (position >= 0 && position < itemList.size()) {
            category.itemData2 item = itemList.get(position);
            return item.getBrandname();
        }
        return "";
    }
    private Context context;
    private checkboxClickListener checkboxClickListener;

    public FilterNamesAdapter(ArrayList<category.itemData2> itemList, Context context, checkboxClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.checkboxClickListener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wrapperfor_brandnamecheckbox, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        category.itemData2 item = itemList.get(position);
        holder.textView1.setText(String.valueOf(item.getBrandname()));

        // Use the position as the ID
        int itemId = position;

        // Set the ID as a tag on the checkbox
        holder.checkbox.setTag(itemId);

        SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        // Attach a click listener
        holder.checkbox.setOnClickListener(v -> {
            CheckBox clickedCheckbox = (CheckBox) v;
            int clickedItemId = (int) clickedCheckbox.getTag();

            // Now, 'clickedItemId' contains the position of the clicked checkbox
            // You can use this position as needed (e.g., pass it to an activity, trigger an API call, etc.)
            checkboxClickListener.onCheckboxClickedb(clickedItemId);

            // Handle selected brands
            if (clickedCheckbox.isChecked()) {
                selectedBrands.add(item.getId());

                // Store selected brand id and brand name
                editor.putString("BrandId", String.valueOf(item.getId()));
                editor.putString("BrandName", item.getBrandname());
            } else {
                selectedBrands.remove(Integer.valueOf(item.getId()));

                // Clear stored brand id and brand name
                editor.remove("BrandId");
                editor.remove("BrandName");
            }

            editor.apply();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView1;
        CheckBox checkbox;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text);
            checkbox = itemView.findViewById(R.id.brandnamecheckbox);

            // Attach a click listener
            checkbox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // Notify the listener (activity) about the checkbox click
                if (checkboxClickListener != null) {
                    checkboxClickListener.onCheckboxClickedb(position);
                }

                // Handle other click actions if needed
            }
        }
    }

    public interface checkboxClickListener {
        void onCheckboxClickedb(int position);
    }
}

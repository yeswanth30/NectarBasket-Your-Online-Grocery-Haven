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

public class CategoryNamesAdapter extends RecyclerView.Adapter<CategoryNamesAdapter.CardViewHolder> {
    private ArrayList<category.itemData3> categoryList;
    private ArrayList<Integer> selectedCategory = new ArrayList<>();
    public ArrayList<Integer> getSelectedCategory() {
        return selectedCategory;
    }
    public String getCategoryName(int position) {
        if (position >= 0 && position < categoryList.size()) {
            category.itemData3 item = categoryList.get(position);
            return item.getSubcatname();
        }
        return "";
    }
    private Context context;
    private checkboxClickListener checkboxClickListener;

    public CategoryNamesAdapter(ArrayList<category.itemData3> categoryList, Context context, checkboxClickListener listener) {
        this.categoryList = categoryList;
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
        category.itemData3 item = categoryList.get(position);
        holder.textView1.setText(String.valueOf(item.getSubcatname()));

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
            checkboxClickListener.onCheckboxClickedc(clickedItemId);

            // Handle selected brands
            if (clickedCheckbox.isChecked()) {
                selectedCategory.add(item.getId());

                // Store selected brand id and brand name
                editor.putString("SubCategoryId", String.valueOf(item.getId()));
                editor.putString("SubCategoryName", item.getSubcatname());
            } else {
                selectedCategory.remove(Integer.valueOf(item.getId()));

                // Clear stored brand id and brand name
                editor.remove("SubCategoryId");
                editor.remove("SubCategoryName");
            }

            editor.apply();
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
                    checkboxClickListener.onCheckboxClickedc(position);
                }

                // Handle other click actions if needed
            }
        }
    }

    public interface checkboxClickListener {
        void onCheckboxClickedc(int position);
    }
}

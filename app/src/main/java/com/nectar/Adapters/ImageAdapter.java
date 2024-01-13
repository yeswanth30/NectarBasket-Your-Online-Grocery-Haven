package com.nectar.Adapters;// Update your ImageAdapter class to extend PagerAdapter

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.nectar.R;
import com.nectar.Retrofitclient.ProductDetailModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {

    private ArrayList<ProductDetailModel.images> images;

    public ImageAdapter(ArrayList<ProductDetailModel.images> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View itemView = inflater.inflate(R.layout.images_layout, container, false);
        ProductDetailModel.images datas = images.get(position);

        ImageView imageView = itemView.findViewById(R.id.imageofphoto);

        if (position >= 0 && position < images.size()) {
            Picasso.get().load("http://13.232.168.157/images/uploads/" + datas.getName()).into(imageView);
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

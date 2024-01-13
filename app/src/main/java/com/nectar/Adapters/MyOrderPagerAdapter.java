package com.nectar.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nectar.ClosedOrderFragment;
import com.nectar.OrderFragment;
import com.nectar.ProcessingOrderFragment;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem1;
import com.nectar.Retrofitclient.OrderItem2;
import com.nectar.Retrofitclient.OrderItem3;
import com.nectar.Retrofitclient.category;

import java.util.List;

public class MyOrderPagerAdapter extends FragmentPagerAdapter {
    private List<OrderItem1> orderItemList;
    private List<OrderItem2> processingOrderItemList;
    private List<OrderItem3> closedOrderItemList;

    public MyOrderPagerAdapter(FragmentManager fm, List<OrderItem1> orderItemList, List<OrderItem2> processingOrderItemList, List<OrderItem3> closedOrderItemList) {
        super(fm);
        this.orderItemList = orderItemList;
        this.processingOrderItemList = processingOrderItemList;
        this.closedOrderItemList = closedOrderItemList;
    }


    public void onBindViewHolder(@NonNull MyOrderPagerAdapter holder, int position) {
        holder.getPageTitle(position);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrderFragment(orderItemList);
            case 1:
                return new ProcessingOrderFragment(processingOrderItemList);
            case 2:
                return new ClosedOrderFragment(closedOrderItemList);
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "All Orders";
            case 1:
                return "Processing orders";
            case 2:
                return "Closed Orders";
            default:
                return null;
        }

    }
}

package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class TrackOrderRequest {
    @SerializedName("orderid")
    private String orderid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }


//    public TrackOrderRequest(String orderid) {
//        this.orderid = orderid;
//    }

}
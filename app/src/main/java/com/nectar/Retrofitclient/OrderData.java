package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderData {
    @SerializedName("order")

    private List<OrderItem1> order;

    @SerializedName("processingorder")

    private List<OrderItem2> processingorder;

    @SerializedName("closedorder")

    private List<OrderItem3> closedorder;

    public List<OrderItem1> getOrder() {
        return order;
    }

    public void setOrder(List<OrderItem1> order) {
        this.order = order;
    }

    public List<OrderItem2> getProcessingorder() {
        return processingorder;
    }

    public void setProcessingorder(List<OrderItem2> processingorder) {
        this.processingorder = processingorder;
    }

    public List<OrderItem3> getClosedorder() {
        return closedorder;
    }

    public void setClosedorder(List<OrderItem3> closedorder) {
        this.closedorder = closedorder;
    }
}

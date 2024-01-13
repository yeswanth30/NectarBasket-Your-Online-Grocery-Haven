package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MyOrderResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private List<Order> orders;


    public MyOrderResponse(String status, int code, List<Order> orders) {
        this.status = status;
        this.code = code;
        this.orders = orders;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order {

        @SerializedName("id")
        private int id;

        @SerializedName("Userid")
        private String userId;

        @SerializedName("ItemName")
        private String itemName;

        @SerializedName("ProductAmount")
        private String productAmount;

        @SerializedName("FinalPrice")
        private String finalPrice;

        @SerializedName("DeliveryDate")
        private String deliveryDate;

        @SerializedName("address")
        private String address;

        @SerializedName("zip")
        private String zip;

        @SerializedName("mobile")
        private String mobile;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getProductAmount() {
            return productAmount;
        }

        public void setProductAmount(String productAmount) {
            this.productAmount = productAmount;
        }

        public String getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(String finalPrice) {
            this.finalPrice = finalPrice;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}

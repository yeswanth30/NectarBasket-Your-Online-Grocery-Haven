package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class MyOrderRequest {

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

    public MyOrderRequest(String itemName, String productAmount, String finalPrice, String deliveryDate, String address, String zip, String mobile) {
        this.itemName = itemName;
        this.productAmount = productAmount;
        this.finalPrice = finalPrice;
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.zip = zip;
        this.mobile = mobile;
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

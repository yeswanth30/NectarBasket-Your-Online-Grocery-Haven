package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class TrackOrderModel {
    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private OrderData data;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public OrderData getData() {
        return data;
    }

    public static class OrderData {
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

        @SerializedName("Purchaseid")
        private String purchaseId;

        @SerializedName("Status")
        private String status;

        @SerializedName("image")
        private String image;

        @SerializedName("productid")
        private String productId;

        @SerializedName("created_at")
        private String createdAt;

        @SerializedName("updated_at")
        private String updatedAt;


        public int getId() {
            return id;
        }

        public String getUserId() {
            return userId;
        }

        public String getItemName() {
            return itemName;
        }

        public String getProductAmount() {
            return productAmount;
        }

        public String getFinalPrice() {
            return finalPrice;
        }

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public String getAddress() {
            return address;
        }

        public String getZip() {
            return zip;
        }

        public String getMobile() {
            return mobile;
        }

        public String getPurchaseId() {
            return purchaseId;
        }

        public String getStatus() {
            return status;
        }

        public String getImage() {
            return image;
        }

        public String getProductId() {
            return productId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public void setProductAmount(String productAmount) {
            this.productAmount = productAmount;
        }

        public void setFinalPrice(String finalPrice) {
            this.finalPrice = finalPrice;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setPurchaseId(String purchaseId) {
            this.purchaseId = purchaseId;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}

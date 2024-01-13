package com.nectar.Retrofitclient;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MyOrderModel {
    private String status;
    private int code;
    private List<OrderData> data;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderData> getData() {

        return data;
    }

    public void setData(List<OrderData> data) {
        this.data = data;
    }
}

//   public class OrderData {
//    private List<OrderItem> order;
//    private List<OrderItem> processingorder;
//    private List<OrderItem> closedorder;
//
//    public List<OrderItem> getOrder() {
//        return order;
//    }
//
//    public List<OrderItem> getProcessingorder() {
//        return processingorder;
//    }
//
//    public List<OrderItem> getClosedorder() {
//        return closedorder;
//    }
//
//       public void setOrder(List<OrderItem> order) {
//           this.order = order;
//       }
//
//       public void setProcessingorder(List<OrderItem> processingorder) {
//           this.processingorder = processingorder;
//       }
//
//       public void setClosedorder(List<OrderItem> closedorder) {
//           this.closedorder = closedorder;
//       }
//   }

//   public class OrderItem {
//    private int id;
//    private String userId;
//    private String itemName;
//    private String productAmount;
//    private String finalPrice;
//    private String deliveryDate;
//    private String address;
//    private String zip;
//    private String mobile;
//    private String purchaseId;
//    private String status;
//    private String image;
//    private String productId;
//    private String createdAt;
//    private String updatedAt;
//    private String cashbackDate;
//    private String cashbackPrice;
//    private String cashbackPercentage;
//    private String orderId;
//    private String quantity;
//    private String color;
//    private String billingAdd;
//    private String state;
//    private String invoiceId;
//    private String referUsed;
//    private String couponUsed;
//    private String firstName;
//    private String lastName;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public String getProductAmount() {
//        return productAmount;
//    }
//
//    public void setProductAmount(String productAmount) {
//        this.productAmount = productAmount;
//    }
//
//    public String getFinalPrice() {
//        return finalPrice;
//    }
//
//    public void setFinalPrice(String finalPrice) {
//        this.finalPrice = finalPrice;
//    }
//
//    public String getDeliveryDate() {
//        return deliveryDate;
//    }
//
//    public void setDeliveryDate(String deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getZip() {
//        return zip;
//    }
//
//    public void setZip(String zip) {
//        this.zip = zip;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getPurchaseId() {
//        return purchaseId;
//    }
//
//    public void setPurchaseId(String purchaseId) {
//        this.purchaseId = purchaseId;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public String getCashbackDate() {
//        return cashbackDate;
//    }
//
//    public void setCashbackDate(String cashbackDate) {
//        this.cashbackDate = cashbackDate;
//    }
//
//    public String getCashbackPrice() {
//        return cashbackPrice;
//    }
//
//    public void setCashbackPrice(String cashbackPrice) {
//        this.cashbackPrice = cashbackPrice;
//    }
//
//    public String getCashbackPercentage() {
//        return cashbackPercentage;
//    }
//
//    public void setCashbackPercentage(String cashbackPercentage) {
//        this.cashbackPercentage = cashbackPercentage;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public String getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public String getBillingAdd() {
//        return billingAdd;
//    }
//
//    public void setBillingAdd(String billingAdd) {
//        this.billingAdd = billingAdd;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getInvoiceId() {
//        return invoiceId;
//    }
//
//    public void setInvoiceId(String invoiceId) {
//        this.invoiceId = invoiceId;
//    }
//
//    public String getReferUsed() {
//        return referUsed;
//    }
//
//    public void setReferUsed(String referUsed) {
//        this.referUsed = referUsed;
//    }
//
//    public String getCouponUsed() {
//        return couponUsed;
//    }
//
//    public void setCouponUsed(String couponUsed) {
//        this.couponUsed = couponUsed;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


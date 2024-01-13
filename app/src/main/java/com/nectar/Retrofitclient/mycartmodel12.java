package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class mycartmodel12 {
    @SerializedName("name")
    private String name;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("price")
    private String price;
    @SerializedName("date")
    private CharSequence date;
    @SerializedName("id")
    private String id;
    @SerializedName("image")
    private String image;
    @SerializedName("color")
    private String color;
    @SerializedName("final")
    private String final_price;
    @SerializedName("cashbackprice")
    private String cashbackprice;
    @SerializedName("cashbackdate")
    private String cashbackdate;
    @SerializedName("cashbackpercentage")
    private String cashbackpercentage;

    private int userid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public CharSequence getDate() {
        return date;
    }

    public void setDate(CharSequence date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getCashbackprice() {
        return cashbackprice;
    }

    public void setCashbackprice(String cashbackprice) {
        this.cashbackprice = cashbackprice;
    }

    public String getCashbackdate() {
        return cashbackdate;
    }

    public void setCashbackdate(String cashbackdate) {
        this.cashbackdate = cashbackdate;
    }

    public String getCashbackpercentage() {
        return cashbackpercentage;
    }

    public void setCashbackpercentage(String cashbackpercentage) {
        this.cashbackpercentage = cashbackpercentage;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public mycartmodel12(String name, String quantity, String price, String date, int id, String image, String color, String final_price, String cashbackprice, String cashbackdate, String cashbackpercentage){
       this.name = name;
       this.quantity = quantity;
       this.price = price;
       this.date = date;
       this.id = String.valueOf(id);
       this.image = image;
       this.color = color;
       this.final_price = final_price;
       this.cashbackprice = cashbackprice;
       this.cashbackdate = cashbackdate;
       this.cashbackpercentage = cashbackpercentage;
    }
}

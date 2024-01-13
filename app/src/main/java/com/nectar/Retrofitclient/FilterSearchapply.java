package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class FilterSearchapply {

    @SerializedName("minimum_price")
    private int minimum_price;
    @SerializedName("maximum_price")
    private int maximum_price;
    @SerializedName("cat")
    private int cat;
    @SerializedName("subcat")
    private String subcat;
    @SerializedName("brand")
    private String brand;

//    public FilterSearchapply(String selectedBrands, int selectedPrice, int categoryId, String Subcategory, int selectedpricemin) {
//        this.brand = selectedBrands;
//        this.maximum_price = selectedPrice;
//        this.cat = categoryId;
//        this.subcat = Subcategory;
//        this.minimum_price = selectedpricemin;
//    }

//    public FilterSearchapply(String selectedBrands, int selectedPrice, int categoryId, String Subcategory, int selectedpricemin) {
//        this.brand = selectedBrands;
//        this.maximum_price = selectedPrice;
//        this.cat = categoryId;
//        this.subcat = Subcategory;
//        this.minimum_price = selectedpricemin;
//    }
//
//    public FilterSearchapply() {
//
//    }

    public int getMinimum_price() {
        return minimum_price;
    }

    public void setMinimum_price(int minimum_price) {
        this.minimum_price = minimum_price;
    }

    public int getMaximum_price() {
        return maximum_price;
    }

    public void setMaximum_price(int maximum_price) {
        this.maximum_price = maximum_price;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcat) {
        this.subcat = subcat;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

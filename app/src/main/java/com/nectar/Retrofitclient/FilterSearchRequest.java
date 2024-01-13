package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FilterSearchRequest {
    @SerializedName("data")
    private filterproducts data;
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;

    public FilterSearchRequest(int selectedPrice, ArrayList<String> selectedBrands) {

    }
    public filterproducts getData() {
        return data;
    }

    public void setData(filterproducts data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class filterproducts {

        @SerializedName("products")
        private ArrayList<products> products = null;

        @SerializedName("image")
        private ArrayList<image> image = null;


        public ArrayList<products> getProducts() {
            return products;
        }

        public void setProducts(ArrayList<products> products) {
            this.products = products;
        }

        public ArrayList<image> getImage() {
            return image;
        }

        public void setImage(ArrayList<image> image) {
            this.image = image;
        }
    }

    public static class products {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("selling_price")
        private String selling_price;

        @SerializedName("mrp_price")
        private String mrp_price;

        @SerializedName("quantity")
        private String quantity;

        @SerializedName("brand_id")
        private int brand_id;

        @SerializedName("category_id")
        private int category_id;

        @SerializedName("subcategory_id")
        private int subcategory_id;

        @SerializedName("offer")
        private String offer;

        @SerializedName("fivetoseven")
        private String fivetoseven;

        @SerializedName("seventoeleven")
        private String seventoeleven;

        @SerializedName("eleventofifteen")
        private String eleventofifteen;

        @SerializedName("fifteentotwentyone")
        private String fifteentotwentyone;

        @SerializedName("description")
        private String description;

        @SerializedName("feture")
        private String feture;

        @SerializedName("overview")
        private String overview;

        @SerializedName("calculate_on")
        private String calculate_on;

        @SerializedName("sellingprice")
        private String sellingprice;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("gstpercentage")
        private String gstpercentage;

        @SerializedName("subcatname")
        private String subcatname;

        @SerializedName("catname")
        private String catname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(String selling_price) {
            this.selling_price = selling_price;
        }

        public String getMrp_price() {
            return mrp_price;
        }

        public void setMrp_price(String mrp_price) {
            this.mrp_price = mrp_price;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(int subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }

        public String getFivetoseven() {
            return fivetoseven;
        }

        public void setFivetoseven(String fivetoseven) {
            this.fivetoseven = fivetoseven;
        }

        public String getSeventoeleven() {
            return seventoeleven;
        }

        public void setSeventoeleven(String seventoeleven) {
            this.seventoeleven = seventoeleven;
        }

        public String getEleventofifteen() {
            return eleventofifteen;
        }

        public void setEleventofifteen(String eleventofifteen) {
            this.eleventofifteen = eleventofifteen;
        }

        public String getFifteentotwentyone() {
            return fifteentotwentyone;
        }

        public void setFifteentotwentyone(String fifteentotwentyone) {
            this.fifteentotwentyone = fifteentotwentyone;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFeture() {
            return feture;
        }

        public void setFeture(String feture) {
            this.feture = feture;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getCalculate_on() {
            return calculate_on;
        }

        public void setCalculate_on(String calculate_on) {
            this.calculate_on = calculate_on;
        }

        public String getSellingprice() {
            return sellingprice;
        }

        public void setSellingprice(String sellingprice) {
            this.sellingprice = sellingprice;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getGstpercentage() {
            return gstpercentage;
        }

        public void setGstpercentage(String gstpercentage) {
            this.gstpercentage = gstpercentage;
        }

        public String getSubcatname() {
            return subcatname;
        }

        public void setSubcatname(String subcatname) {
            this.subcatname = subcatname;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }
    }

    public static class image {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("product_id")
        private String product_id;

        @SerializedName("color_id")
        private String color_id;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private int updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }
    }
}

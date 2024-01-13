package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class search_model_Response {

    @SerializedName("status")
    private String status;
    @SerializedName("success")
    private String message;
    @SerializedName("code")
    private String code;
    @SerializedName("search")
    private ArrayList<search_model_Response.searchdata> search = null;

    @SerializedName("image")
    private ArrayList<search_model_Response.imagelist> images = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<searchdata> getSearch() {
        return search;
    }

    public void setSearch(ArrayList<searchdata> search) {
        this.search = search;
    }

    public ArrayList<imagelist> getImages() {
        return images;
    }

    public void setImages(ArrayList<imagelist> images) {
        this.images = images;
    }

    public class searchdata {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
//        @SerializedName("image")
//        private String image;

//        public String getImage() {
//            return image;
//        }

//        public void setImage(String image) {
//            this.image = image;
//        }

        @SerializedName("selling_price")
        private int selling_price;


        @SerializedName("mrp_price")
        private String marp_price;
        @SerializedName("quantity")
        private int quantity;
        @SerializedName("brand_id")
        private int brand_id;

        @SerializedName("category_id")
        private int category_id;


        @SerializedName("subcategory")
        private String subcategory;
        @SerializedName("offer")
        private String offer;
        @SerializedName("description")
        private String description;
        @SerializedName("fivetoseven")
        private String fivetoseven;
        @SerializedName("seventoeleven")
        private String seventoeleven;
        @SerializedName("eleventofifteen")
        private String eleventofitten;
        @SerializedName("fifteentotowentyone")
        private String fifteentotowentyone;

        @SerializedName("feture")
        private String feture;
        @SerializedName("overview")
        private String overview;
        @SerializedName("calculate_on")
        private String calculate_on;
        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;
        @SerializedName("gstpercentage")
        private String gstpercentage;

        @SerializedName("brandname")
        private String brandname;

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

        public int getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(int selling_price) {
            this.selling_price = selling_price;
        }

        public String getMarp_price() {
            return marp_price;
        }

        public void setMarp_price(String marp_price) {
            this.marp_price = marp_price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
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

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getEleventofitten() {
            return eleventofitten;
        }

        public void setEleventofitten(String eleventofitten) {
            this.eleventofitten = eleventofitten;
        }

        public String getFifteentotowentyone() {
            return fifteentotowentyone;
        }

        public void setFifteentotowentyone(String fifteentotowentyone) {
            this.fifteentotowentyone = fifteentotowentyone;
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

        public String getBrandname() {
            return brandname;
        }

        public void setBrandname(String brandname) {
            this.brandname = brandname;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }
    }

    public class imagelist {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;


        @SerializedName("product_id")
        private int product_id;


        @SerializedName("color_id")
        private String color_id;

        @SerializedName("created_at")
        private String created_at;
        @SerializedName("updated_at")
        private String updated_at;


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

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getColor_id() {
            return color_id;
        }

        public void setColor_id(String color_id) {
            this.color_id = color_id;
        }
    }


}

package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class category {
    @SerializedName("data")
    private ArrayList<itemData> data = null;
    @SerializedName("status")
    private String status;
    @SerializedName("message")

    private String message;
    @SerializedName("code")
    private String code;

    public ArrayList<itemData> getData() {
        return data;
    }

    public void setData(ArrayList<itemData> data) {
        this.data = data;
    }

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
    public class itemData {


        @SerializedName("brand")
        private ArrayList<itemData2> brand;
        @SerializedName("subcat")
        private ArrayList<itemData3> subcat;
        @SerializedName("productlist")
        private ArrayList<itemData4> productlist;

        public ArrayList<itemData4> getProductlist() {
            return productlist;
        }

        public void setProductlist(ArrayList<itemData4> productlist) {
            this.productlist = productlist;
        }

        public ArrayList<itemData2> getBrand() {
            return brand;
        }

        public void setBrand(ArrayList<itemData2> brand) {
            this.brand = brand;
        }

        public ArrayList<itemData3> getSubcat() {
            return subcat;
        }

        public void setSubcat(ArrayList<itemData3> subcat) {
            this.subcat = subcat;
        }
    }
        public class itemData2 {
            @SerializedName("id")
            private int id;

            @SerializedName("brand_image")
            private String brand_image;

            @SerializedName("brandname")
            private String brandname;

            private boolean isChecked;
            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBrand_image() {
                return brand_image;
            }

            public void setBrand_image(String brand_image) {
                this.brand_image = brand_image;
            }

            public String getBrandname() {
                return brandname;
            }

            public void setBrandname(String brandname) {
                this.brandname = brandname;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
        }
        public class itemData3 {
            @SerializedName("id")
            private int id;
            @SerializedName("catid")
            private int catid;

            @SerializedName("sub_image")
            private String sub_image;

            @SerializedName("subcatname")
            private String subcatname;

            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCatid() {
                return catid;
            }

            public void setCatid(int catid) {
                this.catid = catid;
            }

            public String getSub_image() {
                return sub_image;
            }

            public void setSub_image(String sub_image) {
                this.sub_image = sub_image;
            }

            public String getSubcatname() {
                return subcatname;
            }

            public void setSubcatname(String subcatname) {
                this.subcatname = subcatname;
            }
        }
        public class itemData4{
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;

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

            @SerializedName("image")
            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
            }
        }
    }


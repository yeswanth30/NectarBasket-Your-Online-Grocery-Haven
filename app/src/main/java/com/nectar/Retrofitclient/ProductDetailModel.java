package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDetailModel {
    @SerializedName("data")
    private ArrayList<Productsubmodels> data; //isko arraylist banao

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    public ArrayList<Productsubmodels> getData() {
        return data;
    }

    public void setData(ArrayList<Productsubmodels> data) {
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

    public static class Productsubmodels {

        @SerializedName("productlist")
        private productlists productlist = null;

        @SerializedName("image")
        private ArrayList<images> image = null;

        @SerializedName("related_products")
        private ArrayList<relatedproducts> related_products = null;

        @SerializedName("related_products_images")
        private ArrayList<relatedproductsimages> related_products_images = null;

        public productlists getProductlist() {
            return productlist;
        }

        public void setProductlist(productlists productlist) {
            this.productlist = productlist;
        }

        public ArrayList<images> getImage() {
            return image;
        }

        public void setImage(ArrayList<images> image) {
            this.image = image;
        }

        public ArrayList<relatedproducts> getRelated_products() {
            return related_products;
        }

        public void setRelated_products(ArrayList<relatedproducts> related_products) {
            this.related_products = related_products;
        }

        public ArrayList<relatedproductsimages> getRelated_products_images() {
            return related_products_images;
        }

        public void setRelated_products_images(ArrayList<relatedproductsimages> related_products_images) {
            this.related_products_images = related_products_images;
        }
    }

    public static class productlists {

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

        @SerializedName("description")
        private String description;

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

        @SerializedName("barcode")
        private String barcode;

        @SerializedName("purchase_price")
        private String purchase_price;

        @SerializedName("hsn")
        private String hsn;

        @SerializedName("discount")
        private String discount;

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

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getPurchase_price() {
            return purchase_price;
        }

        public void setPurchase_price(String purchase_price) {
            this.purchase_price = purchase_price;
        }

        public String getHsn() {
            return hsn;
        }

        public void setHsn(String hsn) {
            this.hsn = hsn;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }

    public static class images {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("product_id")
        private int product_id;

        @SerializedName("color_id")
        private int color_id;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("colorname")
        private String colorname;

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

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getColor_id() {
            return color_id;
        }

        public void setColor_id(int color_id) {
            this.color_id = color_id;
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

        public String getColorname() {
            return colorname;
        }

        public void setColorname(String colorname) {
            this.colorname = colorname;
        }
    }

    public static class relatedproducts {

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

        @SerializedName("description")
        private String description;

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

        @SerializedName("barcode")
        private String barcode;

        @SerializedName("purchase_price")
        private String purchase_price;

        @SerializedName("hsn")
        private String hsn;

        @SerializedName("discount")
        private String discount;

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

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getPurchase_price() {
            return purchase_price;
        }

        public void setPurchase_price(String purchase_price) {
            this.purchase_price = purchase_price;
        }

        public String getHsn() {
            return hsn;
        }

        public void setHsn(String hsn) {
            this.hsn = hsn;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }

    public static class relatedproductsimages {

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("product_id")
        private int product_id;

        @SerializedName("color_id")
        private int color_id;

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

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getColor_id() {
            return color_id;
        }

        public void setColor_id(int color_id) {
            this.color_id = color_id;
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
    }
}

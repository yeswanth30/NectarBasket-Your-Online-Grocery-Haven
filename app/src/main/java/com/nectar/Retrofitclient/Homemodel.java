package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Homemodel {
    @SerializedName("data")
    private Homesubmodels data;

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    public Homesubmodels getData() {
        return data;
    }

    public void setData(Homesubmodels data) {
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
    public static class Homesubmodels {

        @SerializedName("home_slider")
        private ArrayList<homeslider> home_slider = null;

        @SerializedName("category_slider_images")
        private ArrayList<categoryslider> category_slider_images = null;

        @SerializedName("first_product_list")
        private ArrayList<firstproductslider> first_product_list = null;

        @SerializedName("first_category_slider")
        private ArrayList<firstcategoryslider> first_category_slider = null;

        @SerializedName("second_category_slider")
        private ArrayList<secondcategoryslider> second_category_slider = null;

        @SerializedName("top_category")
        private ArrayList<topcategory> top_category = null;

        @SerializedName("third_category_slider")
        private ArrayList<thirdcategoryslider> third_category_slider = null;

        @SerializedName("fourth_category_slider")
        private ArrayList<fourthcategoryslider> fourth_category_slider = null;

        @SerializedName("second_product_list")
        private ArrayList<secondproductlist> second_product_list = null;

        public ArrayList<homeslider> getHome_slider() {
            return home_slider;
        }

        public void setHome_slider(ArrayList<homeslider> home_slider) {
            this.home_slider = home_slider;
        }

        public ArrayList<categoryslider> getCategory_slider_images() {
            return category_slider_images;
        }

        public void setCategory_slider_images(ArrayList<categoryslider> category_slider_images) {
            this.category_slider_images = category_slider_images;
        }

        public ArrayList<firstproductslider> getFirst_product_list() {
            return first_product_list;
        }

        public void setFirst_product_list(ArrayList<firstproductslider> first_product_list) {
            this.first_product_list = first_product_list;
        }

        public ArrayList<firstcategoryslider> getFirst_category_slider() {
            return first_category_slider;
        }

        public void setFirst_category_slider(ArrayList<firstcategoryslider> first_category_slider) {
            this.first_category_slider = first_category_slider;
        }

        public ArrayList<secondcategoryslider> getSecond_category_slider() {
            return second_category_slider;
        }

        public void setSecond_category_slider(ArrayList<secondcategoryslider> second_category_slider) {
            this.second_category_slider = second_category_slider;
        }

        public ArrayList<topcategory> getTop_category() {
            return top_category;
        }

        public void setTop_category(ArrayList<topcategory> top_category) {
            this.top_category = top_category;
        }

        public ArrayList<thirdcategoryslider> getThird_category_slider() {
            return third_category_slider;
        }

        public void setThird_category_slider(ArrayList<thirdcategoryslider> third_category_slider) {
            this.third_category_slider = third_category_slider;
        }

        public ArrayList<fourthcategoryslider> getFourth_category_slider() {
            return fourth_category_slider;
        }

        public void setFourth_category_slider(ArrayList<fourthcategoryslider> fourth_category_slider) {
            this.fourth_category_slider = fourth_category_slider;
        }

        public ArrayList<secondproductlist> getSecond_product_list() {
            return second_product_list;
        }

        public void setSecond_product_list(ArrayList<secondproductlist> second_product_list) {
            this.second_product_list = second_product_list;
        }
    }


    public static class homeslider {

        @SerializedName("id")
        private int id;

        @SerializedName("Image")
        private String Image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("link")
        private String link;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
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

    public static class categoryslider {

        @SerializedName("id")
        private int id;

        @SerializedName("catid")
        private int catid;

        @SerializedName("image")
        private String image;

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

        public int getCatid() {
            return catid;
        }

        public void setCatid(int catid) {
            this.catid = catid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

    public static class firstproductslider {

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
        private String brand_id;

        @SerializedName("category_id")
        private int category_id;

        @SerializedName("subcategory_id")
        private String subcategory_id;

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

        @SerializedName("products_name")
        private String products_name;

        @SerializedName("discount")
        private String discount;

        @SerializedName("image_path")
        private String image_path;

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

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(String subcategory_id) {
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

        public String getProducts_name() {
            return products_name;
        }

        public void setProducts_name(String products_name) {
            this.products_name = products_name;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
    public static class firstcategoryslider {

        @SerializedName("id")
        private int id;

        @SerializedName("Image")
        private String Image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("link")
        private String link;

        @SerializedName("category_id")
        private String category_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
    }

    public static class secondcategoryslider {

        @SerializedName("id")
        private int id;

        @SerializedName("Image")
        private String Image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("link")
        private String link;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public static class topcategory {

        @SerializedName("id")
        private int id;

        @SerializedName("catname")
        private String catname;

        @SerializedName("image")
        private String image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("offer")
        private String offer;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }
    }

    public static class thirdcategoryslider {

        @SerializedName("id")
        private int id;

        @SerializedName("Image")
        private String Image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("link")
        private String link;

        @SerializedName("category_id")
        private String category_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
    }

    public static class fourthcategoryslider {

        @SerializedName("id")
        private int id;

        @SerializedName("catid")
        private String catid;

        @SerializedName("image")
        private String image;

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("category_id")
        private String category_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
    }

    public static class secondproductlist {

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
        private String brand_id;

        @SerializedName("category_id")
        private int category_id;

        @SerializedName("subcategory_id")
        private String subcategory_id;

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

        @SerializedName("created_at")
        private String created_at;

        @SerializedName("updated_at")
        private String updated_at;

        @SerializedName("gstpercentage")
        private String gstpercentage;

        @SerializedName("subcatname")
        private String  subcatname;

        @SerializedName("catname")
        private String catname;

        @SerializedName("products_name")
        private String products_name;

        @SerializedName("discount")
        private String discount;

        @SerializedName("image_path")
        private String image_path;

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

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(String subcategory_id) {
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

        public String getProducts_name() {
            return products_name;
        }

        public void setProducts_name(String products_name) {
            this.products_name = products_name;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }
}

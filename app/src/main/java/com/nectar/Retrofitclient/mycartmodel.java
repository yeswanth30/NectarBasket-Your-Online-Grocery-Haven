package com.nectar.Retrofitclient;

public class mycartmodel {
    private int Product_id;
    private int Cat_id;
    private String Image;
    private String Price;
    private String Qunatity;
    private String Product_name;
    private String final_price;
    private String dates;
    private String userid;

    public mycartmodel(String name, String quantity, String price, int categoryId, int productid, String image) {
        this.Product_name = name;
        this.Qunatity = quantity;
        this.mrp_price = price;
        this.Cat_id = categoryId;
        this.Product_id = productid;
        this.Image = image;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private  String mrp_price;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(int product_id) {
        Product_id = product_id;
    }

    public int getCat_id() {
        return Cat_id;
    }

    public void setCat_id(int cat_id) {
        Cat_id = cat_id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQunatity() {
        return Qunatity;
    }

    public void setQunatity(String qunatity) {
        Qunatity = qunatity;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getMrp_price() {
        return mrp_price;
    }

    public void setMrp_price(String mrp_price) {
        this.mrp_price = mrp_price;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public mycartmodel(String name, String price, int id, int categoryId, String Quantity, String image, String finalprice,int userid){
        Product_name = name;
        Price = price;
        Product_id = id;
        Cat_id = categoryId;
        Image = image;
        final_price = finalprice;
        this.Qunatity = Quantity;
        this.userid = String.valueOf(userid);
    }



}

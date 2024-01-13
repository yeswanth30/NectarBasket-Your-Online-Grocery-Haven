package com.nectar.Retrofitclient;

public class mylikemodel {
    private int Product_id;
    private int Cat_id;
    private String Price;
    private String Qunatity;
    private String Product_name;
    private String Image;

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
    public mylikemodel(String name, String price,int id,int categoryId,String Quantity,String image){
        Product_name = name;
        Price = price;
        Product_id = id;
        Cat_id = categoryId;
        Image = image;
        this.Qunatity = Quantity;
    }
}

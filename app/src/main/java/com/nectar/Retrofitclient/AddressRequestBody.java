package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class AddressRequestBody {

    @SerializedName("companyname")
    private String companyName;

    @SerializedName("address")
    private String address;

    @SerializedName("state")
    private String state;

    @SerializedName("pincode")
    private String pincode;

    @SerializedName("city")
    private String city;

    @SerializedName("phone")
    private String phone;

    @SerializedName("landmark")
    private String landmark;

    public AddressRequestBody(String companyName, String address, String state, String pincode, String city, String phone, String landmark) {
        this.companyName = companyName;
        this.address = address;
        this.state = state;
        this.pincode = pincode;
        this.city = city;
        this.phone = phone;
        this.landmark = landmark;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}

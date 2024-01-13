package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {

    @SerializedName("token")
    private String token;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("email")
    private String email;

    // Other fields if any

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

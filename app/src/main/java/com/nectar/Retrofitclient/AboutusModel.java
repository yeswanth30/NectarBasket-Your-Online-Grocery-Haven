package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class AboutusModel {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

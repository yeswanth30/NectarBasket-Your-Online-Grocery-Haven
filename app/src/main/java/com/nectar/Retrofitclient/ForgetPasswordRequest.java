package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordRequest {

    @SerializedName("email")
    private String email;

    // Constructor
    public ForgetPasswordRequest(String email) {
        this.email = email;
    }

    // Getter and setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

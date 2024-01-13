package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    // Constructor, getters, and setters

    public ChangePasswordResponse(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // Getter and setter methods

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

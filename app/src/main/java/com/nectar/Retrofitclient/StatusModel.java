package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class StatusModel {

    @SerializedName("status")
    private String status;
    @SerializedName("id")
    private int id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusModel(String status, int id) {
        this.status = status;
        this.id = id;
    }
}

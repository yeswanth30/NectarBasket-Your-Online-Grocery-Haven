package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class slot_request_model {
    @SerializedName("datetime")
    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}

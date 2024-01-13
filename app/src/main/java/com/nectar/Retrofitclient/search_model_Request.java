package com.nectar.Retrofitclient;

import com.google.gson.annotations.SerializedName;

public class search_model_Request {

    @SerializedName("search")
    private String search;
    public search_model_Request( String search) {

        this.search = search;
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


}

package com.nectar.Retrofitclient;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static RetrofitClient instance = null;
    private ApiService myApi;
    private String bearerToken;

    private RetrofitClient() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .build())
                .build();

        myApi = retrofit.create(ApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getMyApi() {
        return myApi;
    }
    public void setBearerToken(String token) {
        this.bearerToken = token;
    }

    public String getBearerToken() {
        return bearerToken;
    }


}

package com.nectar.Retrofitclient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PdfApiHandler {

    private ApiService apiService;

    public PdfApiHandler(ApiService apiService) {
        this.apiService = apiService;
    }

    public void generatePdf(String orderId, String token, Callback<ResponseBody> callback) {
        apiService.generatePdf("Bearer " + token, orderId).enqueue(callback);
    }
}

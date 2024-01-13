package com.nectar.Retrofitclient;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiManager {
    private final ApiService apiService;

    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }
    public void getAddress(String authorization, Callback<AddressModel> callback) {
        Call<AddressModel> call = apiService.getAddress(authorization);
        call.enqueue(callback);
    }
    public void fetchAddressDetails(String token, Callback<AddressModel> callback) {
        Call<AddressModel> call = apiService.getAddress("Bearer " + token);
        call.enqueue(callback);
    }

    public void deleteAddress(String token, int addressId, Callback<Void> callback) {
        Call<Void> call = apiService.deleteAddress("Bearer " + token, addressId);
        call.enqueue(callback);
    }

    public void updateAddress(String token, AddressModel.NewAddressData updatedAddressData, Callback<UpdateAddressResponse> callback) {
        Call<UpdateAddressResponse> call = apiService.updateAddress("Bearer " + token, updatedAddressData);
        call.enqueue(callback);
    }
}

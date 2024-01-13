package com.nectar.Retrofitclient;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    String BASE_URL = "http://13.232.168.157/api/";

    //yeswanth
    @POST("register")
    Call<loginModel> register(@Body SigninModel data);

    @POST("login")
    Call<loginModel> login(@Body SigninModel data);

    @GET("get_profile")
    Call<loginModel> getUserInfo(@Header("Authorization") String authorization);

    @GET("get_profile")
    Call<loginModel> getprofile(@Header("Authorization") String authorization);
    @GET("aboutus")
    Call<ResponseBody> getaboutus(@Header("Authorization") String authorization);

    @POST("logout_user")
    Call<ResponseBody> logoutUser(@Header("Authorization") String authorization);

    @GET("terms")
    Call<ResponseBody> getterms(@Header("Authorization") String authorization);

    @GET("faq")
    Call<ResponseBody> getfaq(@Header("Authorization") String authorization);

    @GET("policy")
    Call<ResponseBody> getpolicy(@Header("Authorization") String authorization);

    @GET("contactus")
    Call<ResponseBody> getcontactus(@Header("Authorization") String authorization);

    @POST("change_password")
    Call<ChangePasswordResponse> changePassword(@Header("Authorization") String authorization, @Body ChangePasswordModel passwordData);

    @POST("forget_password")
    Call<ForgetPasswordResponse> forgetPassword(@Header("Authorization") String authorization, @Body ForgetPasswordRequest email);

    @POST("updateprofile")
    Call<UpdateProfileResponse> updateProfile(@Header("Authorization") String authorization, @Body Map<String, String> updateMap);

    @POST("address")
    Call<Void> addAddress(@Header("Content-Type") String contentType, @Header("Accept") String accept, @Header("Authorization") String authorization, @Body AddressRequestBody addressRequestBody);

    @GET("getaddress")
    Call<AddressModel> getAddress(@Header("Authorization") String authorization);

    @POST("updateaddress")
    Call<UpdateAddressResponse> updateAddress(@Header("Authorization") String authorization, @Body AddressModel.NewAddressData updatedAddressData);
    @POST("updateaddress")
    Call<UpdateAddressResponse> updatestatus(@Header("Authorization") String authorization, @Body StatusModel updatedAddressData);
    @GET("deleteaddress/{id}")
    Call<Void> deleteAddress(@Header("Authorization") String authorization, @Path("id") int addressId);

    @GET("myorder")
    Call<MyOrderModel> getMyOrder(@Header("Authorization") String token);

    @GET("generate-pdf/{id}")
    Call<ResponseBody> generatePdf(@Header("Authorization") String token, @Path("id") String orderId);

    @POST("order")
    Call<TrackOrderModel> trackOrder(@Header("Authorization") String authorization, @Body TrackOrderRequest trackOrderRequest);





    // saksaham

    @GET("allproduct")
    Call<Homemodel> allproduct();

    @GET("product/{category_id}/{product_id}")
    Call<ProductDetailModel> productitem(@Header("Authorization") String authorizationHeader, @Path("category_id") int categoryId, @Path("product_id") int productId);
    @POST("successresponse")
    Call<PaymentModel> paymentsuccess(@Header("Authorization") String token, @Body PaymentRequestModel cart);

    @POST("filter_search")
    Call<FilterSearchRequest.filterproducts> filterSearch(@Header("Authorization") String token, @Body FilterSearchapply fil);




    // kiran
    @GET("product-category/{id}")
    Call<category> product(@Header("Authorization") String authorizationHeader, @Path("id") int itemId);

    @POST("product_search")
    Call<search_model_Response> search(@Body String  request);

    @POST("slots")
    Call<solt_response_model> solt(@Header("Authorization") String authorizationHeader, @Body HashMap<String, String> body);



}






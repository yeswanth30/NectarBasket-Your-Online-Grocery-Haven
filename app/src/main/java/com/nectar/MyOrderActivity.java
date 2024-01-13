package com.nectar;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.nectar.Adapters.MyOrderPagerAdapter;
import com.nectar.Adapters.OrderAdapter;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.MyOrderModel;
import com.nectar.Retrofitclient.OrderData;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem1;
import com.nectar.Retrofitclient.OrderItem2;
import com.nectar.Retrofitclient.OrderItem3;
import com.nectar.Retrofitclient.RetrofitClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    MyOrderPagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    OrderAdapter  orderAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder);

         viewPager = findViewById(R.id.viewPager);
         tabLayout = findViewById(R.id.tabLayout);




        sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("token", "");

       // orderAdapter = new OrderAdapter(this, new ArrayList<>(), sharedpreferences);

        getMyOrderData();
    }

    private void getMyOrderData() {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("token", "");

        Call<MyOrderModel> call = apiService.getMyOrder("Bearer " + token);
        call.enqueue(new Callback<MyOrderModel>() {
            @Override
            public void onResponse(Call<MyOrderModel> call, Response<MyOrderModel> response) {
                if (response.isSuccessful()) {

                    if (response.body() != null && response.body().getData() != null) {
                        MyOrderModel myOrderModel = response.body();
                        List<OrderData> orderDataList = myOrderModel.getData();

                        List<OrderItem1> orderItemList = new ArrayList<>();
                        for (OrderData orderData : orderDataList) {
                            if (orderData.getOrder() != null) {
                                orderItemList.addAll(orderData.getOrder());
                            }
                        }

                        List<OrderItem2> processingOrderItemList = new ArrayList<>();
                        for (OrderData orderData : orderDataList) {
                            if (orderData.getProcessingorder() != null) {
                                processingOrderItemList.addAll(orderData.getProcessingorder());
                            }
                        }

                        List<OrderItem3> closedOrderItemList = new ArrayList<>();
                        for (OrderData orderData : orderDataList) {
                            if (orderData.getClosedorder() != null) {
                                closedOrderItemList.addAll(orderData.getClosedorder());
                            }
                        }

                        pagerAdapter = new MyOrderPagerAdapter(getSupportFragmentManager(), orderItemList, processingOrderItemList, closedOrderItemList);
                        viewPager.setAdapter(pagerAdapter);
                        tabLayout.setupWithViewPager(viewPager);
                    } else {
                        Log.e("MyOrderActivity", "Response body or data is null");
                        showErrorMessage("No data received from the server");
                    }
                } else {
                    Log.e("MyOrderActivity", "Failed to get data. Response code: " + response.code());
                    showErrorMessage("Failed to get data. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MyOrderModel> call, Throwable t) {
                Log.e("MyOrderActivity", "Network error or server unavailable", t);
                t.printStackTrace();

                showErrorMessage("Network error or server unavailable. Please check your connection.");
            }
        });
    }


    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

//
//    @Override
//    public void onGeneratePdf(String orderId) {
//        String token = sharedpreferences.getString("token", "");
//
//        if (token != null && !token.isEmpty()) {
//            ApiService apiService = RetrofitClient.getInstance().getMyApi();
//            Call<ResponseBody> pdfCall = apiService.generatePdf("Bearer " + token, orderId);
//
//            pdfCall.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> pdfResponse) {
//                    savePdfLocally(pdfResponse);
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    showErrorMessage("Network error or server unavailable");
//                }
//            });
//        } else {
//            showErrorMessage("Token is null or empty");
//        }
//    }
//
////    private void savePdfLocally(Response<ResponseBody> response) {
////        try {
////            if (response.isSuccessful() && response.body() != null) {
////                String contentDisposition = response.headers().get("Content-Disposition");
////                String fileName = contentDisposition != null ? contentDisposition.replace("attachment; filename=", "") : "downloaded_file.pdf";
////
////                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
////                File pdfFile = new File(directory, fileName);
////
////                InputStream inputStream = null;
////                OutputStream outputStream = null;
////
////                try {
////                    byte[] fileReader = new byte[4096];
////                    long fileSize = response.body().contentLength();
////                    long fileSizeDownloaded = 0;
////
////                    inputStream = response.body().byteStream();
////                    outputStream = new FileOutputStream(pdfFile);
////
////                    while (true) {
////                        int read = inputStream.read(fileReader);
////                        if (read == -1) {
////                            break;
////                        }
////
////                        outputStream.write(fileReader, 0, read);
////                        fileSizeDownloaded += read;
////                    }
////
////                    outputStream.flush();
////
////                    Log.d("PDF Download", "File saved to: " + pdfFile.getAbsolutePath());
////                } catch (IOException e) {
////                    e.printStackTrace();
////                } finally {
////                    if (inputStream != null) {
////                        inputStream.close();
////                    }
////
////                    if (outputStream != null) {
////                        outputStream.close();
////                    }
////                }
////            } else {
////                showErrorMessage("Failed to download PDF. Response code: " + response.code());
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
////
//    private void trackOrderApiCall(String orderId) {
//        String bearerToken = "Bearer " + sharedpreferences.getString("token", "");
//
//        TrackOrderRequest trackOrderRequest = new TrackOrderRequest(orderId);
//
//        ApiService apiService = RetrofitClient.getInstance().getMyApi();
//        Call<TrackOrderModel> call = apiService.trackOrder(bearerToken, trackOrderRequest);
//
//        call.enqueue(new Callback<TrackOrderModel>() {
//            @Override
//            public void onResponse(Call<TrackOrderModel> call, Response<TrackOrderModel> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    TrackOrderModel trackOrderModel = response.body();
//                    showTrackOrderDialog(
//                            trackOrderModel.getData().getItemName(),
//                            trackOrderModel.getData().getDeliveryDate(),
//                            trackOrderModel.getData().getStatus(),
//                            trackOrderModel.getData().getFinalPrice()
//                    );
//                } else {
//                    Toast.makeText(MyOrderActivity.this, "Failed to track order", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TrackOrderModel> call, Throwable t) {
//                Toast.makeText(MyOrderActivity.this, "Failed to track order", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void showTrackOrderDialog(String productName, String deliveryDate, String status, String price) {
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_track_order);
//
//        TextView productNameTextView = dialog.findViewById(R.id.productNameTextView);
//        TextView deliveryDateTextView = dialog.findViewById(R.id.deliveryDateTextView);
//        TextView statusTextView = dialog.findViewById(R.id.statusTextView);
//        TextView priceTextView = dialog.findViewById(R.id.priceTextView);
//        TextView okButton = dialog.findViewById(R.id.okButton);
//
//        productNameTextView.setText(productName);
//        deliveryDateTextView.setText(deliveryDate);
//        statusTextView.setText(status);
//        priceTextView.setText(price);
//
//        okButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
////                Intent intent = new Intent(MyOrderActivity.this, MyOrderActivity.class);
////                startActivity(intent);
//            }
//        });
//
//        dialog.show();
//    }

//        private void showErrorMessage (String message){
//            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//        }
//    }}

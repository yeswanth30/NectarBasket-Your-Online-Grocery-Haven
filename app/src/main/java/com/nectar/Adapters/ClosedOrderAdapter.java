package com.nectar.Adapters;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nectar.MoreDetails;
import com.nectar.R;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.OrderItem;
import com.nectar.Retrofitclient.OrderItem3;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.TrackOrderModel;
import com.nectar.Retrofitclient.TrackOrderRequest;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClosedOrderAdapter extends RecyclerView.Adapter<ClosedOrderAdapter.ClosedOrderViewHolder> {
    private List<OrderItem3> closedOrderItems;
    private Context context;
    private String token;
    private SharedPreferences sharedpreferences;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 123;


    public ClosedOrderAdapter(Context context, List<OrderItem3> closedOrderItems) {
        this.context = context;
        this.closedOrderItems = closedOrderItems;
      this.sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public ClosedOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderwraper, parent, false);
        return new ClosedOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClosedOrderViewHolder holder, int position) {
        OrderItem3 closedOrderItem = closedOrderItems.get(position);

        holder.productNameTextView.setText(closedOrderItem.getItemName());
        holder.priceTextView.setText(closedOrderItem.getProductAmount());
        holder.deliveryDateTextView.setText(closedOrderItem.getDeliveryDate());
        holder.orderlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int clickedItemId = (closedOrderItem.getProductid());
                int categorykiid = closedOrderItem.getCategory_id();

                Log.e("item ki id", String.valueOf(clickedItemId));
                Log.e("item ki", String.valueOf(categorykiid));

              //  Toast.makeText(view.getContext(), "Clicked item Id: " + clickedItemId + "Category Id is: " + categorykiid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(view.getContext(), MoreDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", closedOrderItem.getProductid());
                bundle.putInt("category_id", closedOrderItem.getCategory_id());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

        Picasso.get()
                .load("http://13.232.168.157/images/uploads/"+closedOrderItem.getImage())
                .into(holder.productphoto12);

        holder.trackOrderButton.setOnClickListener(view -> {
            trackOrderApiCall(closedOrderItem.getOrderid());
        });

       holder.generatePdfButton.setOnClickListener(view -> {
            onGeneratePdf(closedOrderItem.getId());
        });
        token = sharedpreferences.getString("token", "");


    }

    @Override
    public int getItemCount() {

        return closedOrderItems.size();
    }

    static class ClosedOrderViewHolder extends RecyclerView.ViewHolder {
        ImageView productphoto12, minusImageView, addImageView;
        TextView productNameTextView, priceTextView, deliveryDateTextView, quantityTextView;
        Button trackOrderButton, generatePdfButton;
        RelativeLayout orderlayout;

        ClosedOrderViewHolder(View itemView) {
            super(itemView);
            productphoto12 = itemView.findViewById(R.id.productphoto12);
            productNameTextView = itemView.findViewById(R.id.productname11);
            productNameTextView.setMaxLines(1);
            productNameTextView.setEllipsize(TextUtils.TruncateAt.END);
            productNameTextView.setText("Your long text goes here...");
            productNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), productNameTextView.getText(), Toast.LENGTH_LONG).show();
                }
            });
            priceTextView = itemView.findViewById(R.id.price11);
            deliveryDateTextView = itemView.findViewById(R.id.deliverydate);
            trackOrderButton = itemView.findViewById(R.id.trackOrderButton);
           generatePdfButton = itemView.findViewById(R.id.generate);
            orderlayout=itemView.findViewById(R.id.orderlayout);
        }
    }
 private void trackOrderApiCall(String orderId) {
    TrackOrderRequest trackOrderRequest = new TrackOrderRequest();
    trackOrderRequest.setOrderid(orderId);
    ApiService apiService = RetrofitClient.getInstance().getMyApi();



    Log.d("OrderAdapter", "Token: " + token);
    Call<TrackOrderModel> call = apiService.trackOrder("Bearer " + token, trackOrderRequest);

    call.enqueue(new Callback<TrackOrderModel>() {
        @Override
        public void onResponse(Call<TrackOrderModel> call, Response<TrackOrderModel> response) {
            if (response.isSuccessful() && response.body() != null) {
                TrackOrderModel trackOrderModel = response.body();

                Log.d("OrderAdapter", "Track Order API Response: " + trackOrderModel.toString());

                if (trackOrderModel.getStatus().equals("success") && trackOrderModel.getData() != null) {
                    Log.d("OrderAdapter", "Track Order Data: " + trackOrderModel.getData().toString());

                    showTrackOrderDialog(
                            trackOrderModel.getData().getItemName(),
                            trackOrderModel.getData().getDeliveryDate(),
                            trackOrderModel.getData().getStatus(),
                            trackOrderModel.getData().getProductAmount()
                    );
                } else {
                    Log.e("OrderAdapter", "Failed to track order. Status: " + trackOrderModel.getStatus());
                    Toast.makeText(context, "Failed to track order", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e("OrderAdapter", "Failed to track order. Response code: " + response);
              //  Toast.makeText(context, "Failed to track order", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<TrackOrderModel> call, Throwable t) {
            Log.e("OrderAdapter", "Failed to track order", t);
            Toast.makeText(context, "Failed to track ", Toast.LENGTH_SHORT).show();
        }
    });
}
    private void onGeneratePdf(String orderId) {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();
        Call<ResponseBody> pdfCall = apiService.generatePdf("Bearer " + token, orderId);

        pdfCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> pdfResponse) {
                if (pdfResponse.isSuccessful() && pdfResponse.body() != null) {
                    downloadPdf(pdfResponse.body(), orderId);
                } else {
                    Log.e("OrderAdapter", "PDF Response: " + pdfResponse);
                    if (pdfResponse.errorBody() != null) {
                        try {
                            Log.e("OrderAdapter", "Error Body: " + pdfResponse.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    showErrorMessage("PDF Response is null or not successful. HTTP Status Code: " + pdfResponse, new Throwable());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showErrorMessage("Network error or server unavailable", t);
            }
        });
    }

    private void downloadPdf(ResponseBody body, String orderId) {
        try {
            if (body == null) {
                showErrorMessage("PDF Response body is null", new Throwable());
                return;
            }

            String fileExtension = MimeTypeMap.getFileExtensionFromUrl(orderId);
            String fileName = "order_" + orderId + "." + fileExtension;

            File directory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File pdfFile = new File(directory, fileName);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(pdfFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                outputStream.flush();
                showSuccessMessage("PDF Downloaded Successfully");
                openPdf(pdfFile);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("OrderAdapter", "Failed to save PDF locally", e);
                showErrorMessage("Failed to save PDF locally", e);
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    showErrorMessage("Error closing streams", e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Error downloading PDF", e);
        }
    }

    private void openPdf(File pdfFile) {
        Uri uri = FileProvider.getUriForFile(context, "nectar.fileprovider", pdfFile);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            showErrorMessage("No application available to view PDF", e);
        }
    }

    private void showTrackOrderDialog(String productName, String deliveryDate, String status, String price) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_track_order);

        TextView productNameTextView = dialog.findViewById(R.id.productNameTextView);
        productNameTextView.setMaxLines(1);
        productNameTextView.setEllipsize(TextUtils.TruncateAt.END);
        productNameTextView.setText("Your long text goes here...");
        productNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(dialog.getContext(), productNameTextView.getText(), Toast.LENGTH_LONG).show();
            }
        });
        TextView deliveryDateTextView = dialog.findViewById(R.id.deliveryDateTextView);
        TextView statusTextView = dialog.findViewById(R.id.statusTextView);
        TextView priceTextView = dialog.findViewById(R.id.priceTextView);

        TextView okButton = dialog.findViewById(R.id.okButton);

        productNameTextView.setText("Product Name: " + productName);
        deliveryDateTextView.setText("Delivery Date: " + deliveryDate);
        statusTextView.setText("Status:" + status);
        priceTextView.setText("₹" + price);

        String statusMessage = getStatusMessage(status);
        statusTextView.setText(statusMessage);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private String getStatusMessage(String status) {
        // Implement your logic to map status codes to messages
        switch (status) {
            case "0":
                return "Order Created";
            case "1":
                return "Order Processed";
            case "2":
                return "Order Shipped";
            case "3":
                return "Order Delivered";
            case "4":
                return "Order Canceled";
            default:
                return "Unknown Status";
        }
    }

    private void showSuccessMessage(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void showErrorMessage(String message, Throwable t) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        if (t != null) {
            Log.e("OrderAdapter", message, t);
        }
    }
}


package com.nectar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Adapters.AddressAdapter;
import com.nectar.Retrofitclient.AddressModel;
import com.nectar.Retrofitclient.ApiManager;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class address extends AppCompatActivity implements AddressAdapter.OnDeleteClickListener, AddressAdapter.OnEditClickListener {

    private static final int EDIT_ADDRESS_REQUEST_CODE = 1;
    private RecyclerView recyclerViewAddresses;
    private AddressAdapter addressAdapter;
    private String token;
    private ApiManager apiManager;
    TextView addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        addbutton=findViewById(R.id.addbutton);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent164 = new Intent(address.this, addaddress.class);
                startActivity(intent164);
            }
        });
        recyclerViewAddresses = findViewById(R.id.recyclerViewAddresses);
        recyclerViewAddresses.setLayoutManager(new LinearLayoutManager(this));
        addressAdapter = new AddressAdapter(this, new ArrayList<>());
        addressAdapter.setDeleteClickListener(this);
        addressAdapter.setEditClickListener(this);
        recyclerViewAddresses.setAdapter(addressAdapter);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");

        apiManager = new ApiManager(RetrofitClient.getInstance().getMyApi());

        fetchAddressDetails();
    }

    private void fetchAddressDetails() {
        apiManager.fetchAddressDetails(token, new Callback<AddressModel>() {
            @Override
            public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
                if (response.isSuccessful()) {
                    AddressModel addressInfo = response.body();
                    if (addressInfo != null) {
                        List<AddressModel.NewAddressData> newAddressDataList = addressInfo.getNewdata();
                        if (newAddressDataList != null && !newAddressDataList.isEmpty()) {
                            addressAdapter.updateAddressList(newAddressDataList);
                        } else {
                            showToast("Address data list is null or empty");
                        }
                    } else {
                        showToast("Failed to get address details from the response");
                    }
                } else {
                    showToast("Failed to fetch address info. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AddressModel> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }

    @Override
    public void onDeleteClick(int position) {
        int addressId = addressAdapter.getAddressId(position);
        deleteAddressApiCall(addressId);
    }

    @Override
    public void onEditClick(AddressModel.NewAddressData addressData, int position) {
        Intent intent = new Intent(this, EditAddressActivity.class);
        intent.putExtra("addressData", addressData);
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_ADDRESS_REQUEST_CODE);
    }

    private void deleteAddressApiCall(int addressId) {
        apiManager.deleteAddress(token, addressId, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Address deleted successfully");
                    fetchAddressDetails();
                } else {
                    showToast("Failed to delete address. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ADDRESS_REQUEST_CODE && resultCode == RESULT_OK) {
            AddressModel.NewAddressData updatedAddressData = data.getParcelableExtra("updatedAddressData");
            int position = data.getIntExtra("position", -1);

            if (position != -1 && updatedAddressData != null) {
                addressAdapter.getDataList().set(position, updatedAddressData);
                addressAdapter.notifyItemChanged(position);
            }
        }
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

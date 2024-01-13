package com.nectar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.AddressRequestBody;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addaddress extends AppCompatActivity {

    EditText editCompanyName, editAddress, editPincode, editPhone, editLandmark;
    Spinner spinnerState, spinnerCity;
    ApiService apiService;
    String token;
    TextView saveChanges;

    private AddressAddedCallback addressAddedCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

        editCompanyName = findViewById(R.id.editcompanyname);
        editAddress = findViewById(R.id.editaddress);
        spinnerState = findViewById(R.id.spinnerState);
        editPincode = findViewById(R.id.editpincode);
        spinnerCity = findViewById(R.id.spinnerCity);
        editPhone = findViewById(R.id.editphone);
        editLandmark = findViewById(R.id.editlandmark);
        saveChanges = findViewById(R.id.btnAddAddress);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");

        // Set up the Spinner for states
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.indian_states_array, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(stateAdapter);

        // Set up the Spinner for cities
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.indian_cities_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAddress();
            }
        });

        // Set the callback if it's available (from the calling activity)
        if (getParent() != null && getParent() instanceof AddressAddedCallback) {
            addressAddedCallback = (AddressAddedCallback) getParent();
        }
    }

    private void addNewAddress() {
        String companyName = editCompanyName.getText().toString();
        String address = editAddress.getText().toString();
        String state = spinnerState.getSelectedItem().toString();
        String pincode = editPincode.getText().toString();
        String city = spinnerCity.getSelectedItem().toString();
        String phone = editPhone.getText().toString();
        String landmark = editLandmark.getText().toString();

        if (TextUtils.isEmpty(companyName) || TextUtils.isEmpty(address) || TextUtils.isEmpty(pincode)
                || TextUtils.isEmpty(phone) || TextUtils.isEmpty(landmark)) {
            showToast("Please enter Required details");
            return;
        }

        if (phone.length() > 10) {
            showToast("Phone number should not exceed 10 digits");
            return;
        }


        AddressRequestBody newAddress = new AddressRequestBody(companyName, address, state, pincode, city, phone, landmark);

        ApiService apiService = RetrofitClient.getInstance().getMyApi();
        Call<Void> call = apiService.addAddress("application/json", "application/json", "Bearer " + token, newAddress);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    showToast("Address added successfully");

                    if (addressAddedCallback != null) {
                        addressAddedCallback.onAddressAdded();
                    }
                    Intent intent = new Intent(addaddress.this, address.class);
                    startActivity(intent);

                    finish();
                } else {
                    showToast("Failed to add address. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to add address. Please try again.");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

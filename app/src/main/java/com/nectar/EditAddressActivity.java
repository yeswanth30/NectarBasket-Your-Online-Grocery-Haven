package com.nectar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.AddressModel;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.UpdateAddressResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddressActivity extends AppCompatActivity {

    private EditText editCompanyName, editAddress, editPincode, editPhone, editLandmark;
    private Spinner spinnerState, spinnerCity;
    private Button btnSaveChanges;
    private ApiService apiService;
    private AddressModel.NewAddressData currentAddressData;
    private int position;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editaddressactivity);

        editCompanyName = findViewById(R.id.editcompanyname);
        editAddress = findViewById(R.id.editaddress);
        spinnerState = findViewById(R.id.spinnerState);
        editPincode = findViewById(R.id.editpincode);
        spinnerCity = findViewById(R.id.spinnerCity);
        editPhone = findViewById(R.id.editphone);
        editLandmark = findViewById(R.id.editlandmark);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        currentAddressData = getIntent().getParcelableExtra("addressData");
        position = getIntent().getIntExtra("position", -1);

        apiService = RetrofitClient.getInstance().getMyApi();

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");

        populateUI();

        btnSaveChanges.setOnClickListener(v -> saveChanges());
    }

    private void populateUI() {
        editCompanyName.setText(currentAddressData.getCompanyname());
        editAddress.setText(currentAddressData.getAddress());
        editPincode.setText(String.valueOf(currentAddressData.getPincode()));
        editPhone.setText(currentAddressData.getPhone());
        editLandmark.setText(currentAddressData.getLandmark());

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.indian_states_array, android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerState.setAdapter(stateAdapter);

        int statePosition = stateAdapter.getPosition(currentAddressData.getState());
        spinnerState.setSelection(statePosition);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.indian_cities_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        int cityPosition = cityAdapter.getPosition(currentAddressData.getCity());
        spinnerCity.setSelection(cityPosition);
    }

    private void saveChanges() {
        String updatedCompanyName = editCompanyName.getText().toString().trim();
        String updatedAddress = editAddress.getText().toString().trim();
        String updatedState = spinnerState.getSelectedItem().toString();
        String updatedPincode = editPincode.getText().toString().trim();
        String updatedCity = spinnerCity.getSelectedItem().toString();
        String updatedPhone = editPhone.getText().toString().trim();
        String updatedLandmark = editLandmark.getText().toString().trim();

        if (TextUtils.isEmpty(updatedCompanyName) || TextUtils.isEmpty(updatedAddress) || TextUtils.isEmpty(updatedPincode)
                || TextUtils.isEmpty(updatedPhone) || TextUtils.isEmpty(updatedLandmark)) {
            showToast("Please enter Required details");
            return;
        }

        if (updatedPhone.length() > 10) {
            showToast("Phone number should not exceed 10 digits");
            return;
        }

        AddressModel.NewAddressData updatedAddressData = new AddressModel.NewAddressData(
                currentAddressData.getId(),
                updatedCompanyName,
                updatedAddress,
                updatedState,
                updatedPincode,
                updatedCity,
                updatedPhone,
                updatedLandmark
        );



        Call<UpdateAddressResponse> call = apiService.updateAddress("Bearer " + token, updatedAddressData);
        call.enqueue(new Callback<UpdateAddressResponse>() {
            @Override
            public void onResponse(Call<UpdateAddressResponse> call, Response<UpdateAddressResponse> response) {
                if (response.isSuccessful()) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("updatedAddressData", updatedAddressData);
                    resultIntent.putExtra("position", position);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    showToast("Failed to update address. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<UpdateAddressResponse> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

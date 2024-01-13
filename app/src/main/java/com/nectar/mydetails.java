package com.nectar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.LoginResponse;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.SigninModel;
import com.nectar.Retrofitclient.UpdateProfileRequest;
import com.nectar.Retrofitclient.UpdateProfileResponse;
import com.nectar.Retrofitclient.loginModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mydetails extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText, mobileNumberEditText, emailEditText;
    ApiService apiService;
    String token;
    TextView savecahnges;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydetails);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        mobileNumberEditText = findViewById(R.id.mobileNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        savecahnges=findViewById(R.id.savecahnges);

        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");
        fetchUserInfo();
        mobileNumberEditText.setEnabled(false);

        savecahnges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFirstName = firstNameEditText.getText().toString();
                String newLastName = lastNameEditText.getText().toString();
                String newEmail = emailEditText.getText().toString();
                String newMobileNumber = mobileNumberEditText.getText().toString();

                updateProfile(token, newFirstName, newLastName, newEmail, newMobileNumber);
            }
        });

    }

    private void fetchUserInfo() {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();

        Call<loginModel> call = apiService.getprofile("Bearer " + token);

        call.enqueue(new Callback<loginModel>() {
            @Override
            public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                if (response.isSuccessful()) {
                    loginModel userInfo = response.body();

                    String firstName = userInfo.getData().getUser().getFirstname();
                    String lastName = userInfo.getData().getUser().getLastname();
                    String email = userInfo.getData().getUser().getEmail();
                    String mobileNumber =userInfo.getData().getUser().getNumber();



                    firstNameEditText.setText(firstName);
                    lastNameEditText.setText(lastName);
                    emailEditText.setText(email);
                    mobileNumberEditText.setText(mobileNumber);

                } else {
                    showToast("Failed to fetch user info");
                }
            }

            @Override
            public void onFailure(Call<loginModel> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }

    public void updateProfile(String token, String newFirstName, String newLastName, String newEmail, String newMobileNumber) {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();

        Map<String, String> updateMap = new HashMap<>();
        updateMap.put("FirstName", newFirstName);
        updateMap.put("LastName", newLastName);
        updateMap.put("Email", newEmail);
        updateMap.put("Mobile", newMobileNumber);

        Call<UpdateProfileResponse> call = apiService.updateProfile("Bearer " + token, updateMap);
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful()) {
                    UpdateProfileResponse updateResponse = response.body();
                    if (updateResponse != null) {
                        UpdateProfileResponse.NewData newData = updateResponse.getNewData();
                        if (newData != null) {
                            String updatedFirstName = newData.getFirstname();
                            String updatedLastName = newData.getLastname();
                            String updatedEmail = newData.getEmail();
                            String updatedMobileNumber = newData.getNumber();
                            showToast("Update successful");
                        } else {
                            Log.e("UpdateProfile", "New data is null");
                            showToast("Failed to update profile");
                        }
                    } else {
                        Log.e("UpdateProfile", "Update response is null");
                        showToast("Failed to update profile");
                    }
                } else {
                    Log.e("UpdateProfile", "Failed to update profile. Error: " + response.message());
                    showToast("Failed to update profile");
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}











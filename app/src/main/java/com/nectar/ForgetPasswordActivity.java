package com.nectar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.ForgetPasswordRequest;
import com.nectar.Retrofitclient.ForgetPasswordResponse;
import com.nectar.Retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText emailEditText;
    Button forgetPasswordButton;
    String token;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");

        Log.d("datasss", "Token Retrieved: " + token);


        emailEditText = findViewById(R.id.editTextEmail);
        forgetPasswordButton = findViewById(R.id.buttonResetPassword);

        forgetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forgetPassword();
            }
        });
    }


    private void forgetPassword() {

        String userEmail = emailEditText.getText().toString();
        ForgetPasswordRequest emailData = new ForgetPasswordRequest(userEmail);

        ApiService apiService = RetrofitClient.getInstance().getMyApi();
        Call<ForgetPasswordResponse> forgetPasswordCall = apiService.forgetPassword("Bearer " + token, emailData);

        forgetPasswordCall.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                if (response.isSuccessful()) {
                    ForgetPasswordResponse forgetPasswordResponse = response.body();
                    if (forgetPasswordResponse != null) {
                        if ("success".equalsIgnoreCase(forgetPasswordResponse.getStatus())) {
                            Toast.makeText(ForgetPasswordActivity.this, "Forget Password request successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgetPasswordActivity.this, "Forget Password request failed: " + forgetPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Forget Password request failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                Toast.makeText(ForgetPasswordActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.nectar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.ChangePasswordModel;
import com.nectar.Retrofitclient.ChangePasswordResponse;
import com.nectar.Retrofitclient.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changepassword extends AppCompatActivity {

    EditText oldPasswordEditText, newPasswordEditText;
    Button changePasswordButton;
    String token;
     ImageView passwordVisibilityToggle11,passwordVisibilityToggle12;
    private ApiService apiService;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        oldPasswordEditText = findViewById(R.id.oldPasswordEditText);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        passwordVisibilityToggle11 = findViewById(R.id.passwordVisibilityToggle11);
        passwordVisibilityToggle12 = findViewById(R.id.passwordVisibilityToggle12);



        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        oldPasswordEditText.addTextChangedListener(new PasswordToggleTextWatcher(oldPasswordEditText,passwordVisibilityToggle11));

        passwordVisibilityToggle11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        newPasswordEditText.addTextChangedListener(new PasswordToggleTextWatcher(newPasswordEditText,passwordVisibilityToggle12));

        passwordVisibilityToggle12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility1();
            }
        });


    }

    private void changePassword() {
        // Check if the authentication token is available
        if (token == null || token.isEmpty()) {
            // Handle the case where the token is not available
            Toast.makeText(changepassword.this, "Authentication token is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        String oldPassword = oldPasswordEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        ChangePasswordModel passwordData = new ChangePasswordModel(oldPassword, newPassword);

        ApiService apiService = RetrofitClient.getInstance().getMyApi();
        Call<ChangePasswordResponse> changePasswordCall = apiService.changePassword("Bearer " + token, passwordData);

        changePasswordCall.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful()) {
                    ChangePasswordResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        if ("success".equalsIgnoreCase(apiResponse.getStatus())) {
                            Toast.makeText(changepassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(changepassword.this, "Password change failed: " + apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        int errorCode = response.code();
                        Log.e("API Error", "Error Code: " + errorCode + ", Body: " + errorBody);
                        Toast.makeText(changepassword.this, "Password change failed: " + errorBody, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Toast.makeText(changepassword.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;

        int inputType = passwordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

        oldPasswordEditText.setInputType(inputType);

        // Update the icon based on the password visibility state
        int drawableRes = passwordVisible ?
                R.drawable.baseline_remove_red_eye_24 :
                R.drawable.baseline_visibility_off_24;

        passwordVisibilityToggle11.setImageResource(drawableRes);
    }
    private void togglePasswordVisibility1() {
        passwordVisible = !passwordVisible;

        int inputType = passwordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

        newPasswordEditText.setInputType(inputType);

        int drawableRes = passwordVisible ?
                R.drawable.baseline_remove_red_eye_24 :
                R.drawable.baseline_visibility_off_24;

        passwordVisibilityToggle12.setImageResource(drawableRes);
    }
}

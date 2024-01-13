package com.nectar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.SigninModel;
import com.nectar.Retrofitclient.loginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {

    private TextView forgotpassword, bottomTextView2, loginButton23;
    private EditText emailEditText, passwordEditText;
    private ImageView passwordVisibilityToggle;
    private ApiService apiService;
    String userid;
    private boolean passwordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        bottomTextView2 = findViewById(R.id.bottomTextView2);
        loginButton23 = findViewById(R.id.loginButton23);
        passwordEditText = findViewById(R.id.passwordEditText);
        emailEditText = findViewById(R.id.emailEditText);
        forgotpassword = findViewById(R.id.forgotpassword);
        passwordVisibilityToggle = findViewById(R.id.passwordVisibilityToggle);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        passwordEditText.addTextChangedListener(new PasswordToggleTextWatcher(passwordEditText,passwordVisibilityToggle));

        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent164 = new Intent(login.this, ForgetPasswordActivity.class);
                startActivity(intent164);
            }
        });

        bottomTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent164 = new Intent(login.this, signup.class);
                startActivity(intent164);
            }
        });

        loginButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Please Enter Values", Toast.LENGTH_LONG).show();
                } else {
                    SigninModel signinData = new SigninModel();
                    signinData.setEmail(name);
                    signinData.setPassword(password);

                    Call<loginModel> call = apiService.login(signinData);
                    call.enqueue(new Callback<loginModel>() {
                        @Override
                        public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                loginModel signinResponse = response.body();

                                String token = signinResponse.getData().getToken();
                                int userId = signinResponse.getData().getUser().getId();

                                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                                SharedPreferences.Editor myedit = sharedPreferences.edit();
                                myedit.putString("token", token);
                                myedit.putInt("userID", userId);
                              //  myedit.putString("id",userid);
                                myedit.apply();

                                Log.e("userewrwrwrwr", String.valueOf(userId));

                                Toast.makeText(login.this, "Successfully login", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(login.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                showLoginFailedDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<loginModel> call, Throwable t) {
                            // Handle network errors or request failure
                            Toast.makeText(login.this, "Login failed. Please try again later.", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;

        int inputType = passwordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

        passwordEditText.setInputType(inputType);

        // Update the icon based on the password visibility state
        int drawableRes = passwordVisible ?
                R.drawable.baseline_remove_red_eye_24 :
                R.drawable.baseline_visibility_off_24;

        passwordVisibilityToggle.setImageResource(drawableRes);
    }

    private void showLoginFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
        builder.setTitle("Login Failed")
                .setMessage("Invalid credentials. Please check your email and password.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // You can optionally add functionality here if the user clicks "OK"
                    }
                })
                .show();
    }
}

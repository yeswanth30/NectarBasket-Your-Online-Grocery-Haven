package com.nectar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.SigninModel;
import com.nectar.Retrofitclient.loginModel;
import com.nectar.PasswordToggleTextWatcher;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup extends AppCompatActivity {

    private CheckBox checkBox;
    private TextView loginButton, bottomTextView23;

    EditText emailEditText, emailEditText343, emailsss, numEditText342, passwordEditText1;
    private ImageView passwordVisibilityToggle;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        bottomTextView23 = findViewById(R.id.bottomTextView23);
        loginButton = findViewById(R.id.loginButton);
        emailEditText = findViewById(R.id.emailEditText);
        emailEditText343 = findViewById(R.id.emailEditText343);
        emailsss = findViewById(R.id.emailsss);
        numEditText342 = findViewById(R.id.numEditText342);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        passwordVisibilityToggle = findViewById(R.id.passwordVisibilityToggle);

        passwordEditText1.addTextChangedListener(new PasswordToggleTextWatcher(passwordEditText1, passwordVisibilityToggle));

        passwordVisibilityToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        bottomTextView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent164 = new Intent(signup.this, login.class);
                startActivity(intent164);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmptyField(emailEditText) || isEmptyField(emailEditText343) || isEmptyField(emailsss) ||
                        isEmptyField(numEditText342) || isEmptyField(passwordEditText1)) {
                    showToast("Please enter Required details");
                    return;
                }

                if (!isGmailAccount(emailsss.getText().toString())) {
                    showToast("Please enter a valid Gmail account");
                    return;
                }

                if (numEditText342.getText().toString().length() > 10) {
                    showToast("Phone number should not exceed 10 digits");
                    return;
                }

                SigninModel sign = new SigninModel();
                sign.setEmail(emailsss.getText().toString());
                sign.setPassword(passwordEditText1.getText().toString());
                sign.setFirstname(emailEditText.getText().toString());
                sign.setLastName(emailEditText343.getText().toString());
                sign.setNumber(numEditText342.getText().toString());

                Call<loginModel> call = RetrofitClient.getInstance().getMyApi().register(sign);
                call.enqueue(new Callback<loginModel>() {
                    @Override
                    public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                        if (response.isSuccessful()) {
                            loginModel data = response.body();
                            String firstname = data.getData().getUser().getFirstname();
                            String email = data.getData().getUser().getEmail();
                            Log.e("data user", firstname);
                            Log.e("data user", email);
                            Intent intent = new Intent(signup.this, login.class);
                            startActivity(intent);
                        } else {
                            Log.e("signup response else", "Unsuccessful");
                        }
                    }

                    @Override
                    public void onFailure(Call<loginModel> call, Throwable t) {
                        Log.e("API Error", "An error has occurred", t);
                        Toast.makeText(signup.this, "An error has occurred. Please try again later.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void togglePasswordVisibility() {
        passwordVisible = !passwordVisible;

        int inputType = passwordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

        passwordEditText1.setInputType(inputType);

        int drawableRes = passwordVisible ?
                R.drawable.baseline_remove_red_eye_24 :
                R.drawable.baseline_visibility_off_24;

        passwordVisibilityToggle.setImageResource(drawableRes);
    }

    private boolean isGmailAccount(String email) {
        return email.toLowerCase().endsWith("@gmail.com");
    }

    private void showToast(String message) {
        Toast.makeText(signup.this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isEmptyField(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }
}

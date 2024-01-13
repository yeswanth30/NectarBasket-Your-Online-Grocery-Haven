package com.nectar;
import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.SigninModel;
import com.nectar.Retrofitclient.loginModel;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class privacypolicy extends AppCompatActivity {

    TextView policytext;
    String token;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacypolicy);
        policytext=findViewById(R.id.policytext);
        ApiService apiService = RetrofitClient.getInstance().getMyApi();


        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        token = sharedpreferences.getString("token", "");


        Call<ResponseBody> call = apiService.getpolicy("Bearer " + token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    Log.d("FragmentFive", "terms: " + response.body());
                    try {
                        policytext.setText(Html.fromHtml(response.body().string()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Log.d("FragmentFive", "fetchAboutUsData successful");


                } else {
                    showToast("Failed to fetch terms data");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Network request failed: " + t.getMessage());
                t.printStackTrace();

                Log.e("FragmentFive", "Network request failed", t);

            }

        });



    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
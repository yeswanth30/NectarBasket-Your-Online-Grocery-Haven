package com.nectar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.loginModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentFive extends Fragment {
    private RecyclerView recyclerView;
    private TextView firstTextView, secondTextView,aboutus;
    ImageView next8;
    String token;
    RelativeLayout layout13,layout14,layout12,layout11,layout10,layout9,layout8,layout134,layout7;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentfive, container, false);

        firstTextView = view.findViewById(R.id.firstTextView);
        secondTextView = view.findViewById(R.id.secondTextView);
        layout14=view.findViewById(R.id.layout14);
        layout13=view.findViewById(R.id.layout13);
        layout12=view.findViewById(R.id.layout12);
        layout11=view.findViewById(R.id.layout11);
        layout10=view.findViewById(R.id.layout10);
        layout9=view.findViewById(R.id.layout9);
        layout8=view.findViewById(R.id.layout8);
        layout134=view.findViewById(R.id.layout134);
        layout7=view.findViewById(R.id.layout7);

        layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), MyOrderActivity.class));
            }
        });


        layout134.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), address.class));
            }
        });

        layout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), mydetails.class));
            }
        });


        layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), changepassword.class));
            }
        });
        layout10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), contactus.class));
            }
        });

        layout11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), privacypolicy.class));
            }
        });
        layout12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), faq.class));
            }
        });

        layout14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), aboutus.class));
            }
        });
        layout13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(requireContext(), terms.class));
            }
        });

        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
         token = sharedpreferences.getString("token", "");


        TextView logoutButton = view.findViewById(R.id.logout1);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

 //   String email = sharedpreferences.getString("email", "");
//
//        firstTextView.setText(fullName);
//        secondTextView.setText(email);

        fetchUserInfo();



        return view;
    }




    private void fetchUserInfo() {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();
    //    String bearerToken = RetrofitClient.getInstance().getBearerToken();

        Call<loginModel> call = apiService.getUserInfo("Bearer " + token);

        call.enqueue(new Callback<loginModel>() {
            @Override
            public void onResponse(Call<loginModel> call, Response<loginModel> response) {
                if (response.isSuccessful()) {
                    loginModel userInfo = response.body();



                    firstTextView.setText(userInfo.getData().getUser().getFirstname());
                    secondTextView.setText(userInfo.getData().getUser().getEmail());
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



    private void logoutUser() {
        // Create an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes, perform logout
                performLogout();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No, do nothing or dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void performLogout() {
        ApiService apiService = RetrofitClient.getInstance().getMyApi();

        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String token = sharedpreferences.getString("token", "");

        Call<ResponseBody> call = apiService.logoutUser("Bearer " + token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    clearUserData();
                    showToast("Logout successful");
                    navigateToLoginActivity();
                } else {
                    showToast("Logout failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Network request failed");
            }
        });
    }

    private void clearUserData() {
        SharedPreferences sharedpreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove("token");
        editor.apply();
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(requireContext(), login.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
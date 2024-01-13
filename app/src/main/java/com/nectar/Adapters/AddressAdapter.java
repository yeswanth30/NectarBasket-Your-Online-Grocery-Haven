package com.nectar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nectar.R;
import com.nectar.Retrofitclient.AddressModel;
import com.nectar.Retrofitclient.ApiService;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.StatusModel;
import com.nectar.Retrofitclient.UpdateAddressResponse;
import com.nectar.address;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private static final String TAG = "AddressAdapter";

    private List<AddressModel.NewAddressData> addressList;
    private Context context;
    private int defaultAddressPosition = -1;
    private SharedPreferences sharedPreferences;
    private ApiService apiService;
    private address add;
    public List<AddressModel.NewAddressData> getDataList() {
        return addressList;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public interface OnEditClickListener {
        void onEditClick(AddressModel.NewAddressData addressData, int position);
    }

    private OnDeleteClickListener deleteClickListener;
    private OnEditClickListener editClickListener;

    public void setDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    public void setEditClickListener(OnEditClickListener listener) {
        this.editClickListener = listener;
    }

    public AddressAdapter(Context context, List<AddressModel.NewAddressData> addressList) {
        this.context = context;
        this.addressList = addressList;
        this.sharedPreferences = context.getSharedPreferences("AddressPreferences", Context.MODE_PRIVATE);

        this.defaultAddressPosition = sharedPreferences.getInt("defaultAddressPosition", -1);

        Log.d(TAG, "AddressAdapter: Constructor called");
    }

    public AddressAdapter(Context context, List<AddressModel.NewAddressData> addressList, SharedPreferences sharedPreferences) {
        this.context = context;
        this.addressList = addressList;
        this.sharedPreferences = sharedPreferences;
        this.defaultAddressPosition = sharedPreferences.getInt("defaultAddressPosition", -1);

        Log.d(TAG, "AddressAdapter: Constructor with SharedPreferences called");
    }

    public void updateAddressList(List<AddressModel.NewAddressData> newList) {
        addressList.clear();
        addressList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressModel.NewAddressData addressData = addressList.get(position);
        holder.bind(addressData, position);

        if (addressData.getStatus() == 1) {
            //holder.setDefaultAddress(position);
            holder.radioButtonAddressDetails.setChecked(position == defaultAddressPosition);
        }
    }

    public int getAddressId(int position) {
        if (position >= 0 && position < addressList.size()) {
            return addressList.get(position).getId();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAddressDetails, btnedit, btndelete;
        private RadioButton radioButtonAddressDetails;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAddressDetails = itemView.findViewById(R.id.textViewAddressDetails);
            radioButtonAddressDetails = itemView.findViewById(R.id.radioButtonAddressDetails);
            btnedit = itemView.findViewById(R.id.btnedit);
            btndelete = itemView.findViewById(R.id.btndelete);

            Log.d(TAG, "AddressViewHolder: Constructor called");
        }

        public void bind(AddressModel.NewAddressData addressData, int position) {
            textViewAddressDetails.setText(
                    addressData.getCompanyname() +
                            "\n" + addressData.getAddress() + " " +
                            addressData.getState() +
                            addressData.getCity() + " " +
                            addressData.getPincode() +
                            "\n" + addressData.getLandmark() +
                            "\nPhone: " + addressData.getPhone()
            );

            btndelete.setOnClickListener(v -> {
                if (deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(position);
                }
            });

            btnedit.setOnClickListener(v -> {
                if (editClickListener != null) {
                    editClickListener.onEditClick(addressData, position);
                }
            });
            int stat =1;
            Log.e("current sttaus", String.valueOf(addressData.getStatus()));
            if(stat == (addressData.getStatus())){
                radioButtonAddressDetails.setChecked(position == defaultAddressPosition);
//               radioButtonAddressDetails.setChecked(true);

            }else{
                radioButtonAddressDetails.setChecked(position == defaultAddressPosition);
            }


            radioButtonAddressDetails.setOnClickListener(v -> {
                // setDefaultAddress(getAdapterPosition()); // Use getAdapterPosition()
                setDefaultAddress(position);
            });
        }

        private void setDefaultAddress(int newPosition) {
            if (newPosition == defaultAddressPosition) {
                return;
            }

//            notifyItemChanged(defaultAddressPosition);



            if (defaultAddressPosition != -1) {
                addressList.get(defaultAddressPosition).setDefault(false);
                notifyItemChanged(defaultAddressPosition);
                updatestatus(addressList.get(newPosition).getId());

//                showToast("Default address Changed");
            }

            AddressModel.NewAddressData newDefaultAddress = addressList.get(newPosition);
            newDefaultAddress.setDefault(true);
            notifyItemChanged(newPosition);

            defaultAddressPosition = newPosition;
            Log.e("default address new",addressList.get(newPosition).getId()+addressList.get(newPosition).getCompanyname());
            saveDefaultAddressInSharedPreferences(newDefaultAddress);

            showToast("Default address set successfully");

            Log.d(TAG, "setDefaultAddress: Default address set at position " + newPosition);
        }

        private void saveDefaultAddressInSharedPreferences(AddressModel.NewAddressData addressData) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("defaultCompanyname", addressData.getCompanyname());
            editor.putString("defaultAddress", addressData.getAddress());
            editor.putString("defaultPincode", addressData.getPincode());
            editor.putString("defaultState", addressData.getState());
            editor.putString("defaultCity", addressData.getCity());
            editor.putString("defaultPhone", addressData.getPhone());
            editor.putString("defaultLandmark", addressData.getLandmark());
            editor.putInt("defaultAddressId", addressData.getId());


            editor.apply();
            Log.e("default address inside",addressData.getId()+addressData.getCompanyname());
        }
        private void updatestatus(int id ){
            String status = "1";
            StatusModel updatestatus = new StatusModel(status,id);
            Log.e("statusupdate", String.valueOf(id));
            apiService = RetrofitClient.getInstance().getMyApi();

            SharedPreferences sharedpreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            String token = sharedpreferences.getString("token", "");

            Call<UpdateAddressResponse> call = apiService.updatestatus("Bearer " + token, updatestatus);
            call.enqueue(new Callback<UpdateAddressResponse>() {
                @Override
                public void onResponse(Call<UpdateAddressResponse> call, Response<UpdateAddressResponse> response) {
                    if (response.isSuccessful()) {

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
    }



    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}





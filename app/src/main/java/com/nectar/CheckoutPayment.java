package com.nectar;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.nectar.Adapters.caryadapter;
import com.nectar.Dbhelper.dphelper;
import com.nectar.OrderAcceptedPage;
import com.nectar.R;
import com.nectar.Retrofitclient.AddressModel;
import com.nectar.Retrofitclient.PaymentModel;
import com.nectar.Retrofitclient.PaymentRequestModel;
import com.nectar.Retrofitclient.RetrofitClient;
import com.nectar.Retrofitclient.mycartmodel12;
import com.nectar.Retrofitclient.solt_response_model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckoutPayment extends AppCompatActivity {

    private static final String TAG = "CheckoutPayment";

    TextView placeorder, totalAmountTextView, addressTextView, eventDateEditText;
    Spinner eventTimeEditText;
    String authorization;

    String cartarray;ArrayList spinnerDataList;
    CheckBox categorycheckBox;
    String token, price;
    private dphelper databaseHelperClass;
    ArrayList<mycartmodel12> paym;

    ArrayList<solt_response_model.slotdata> alldata1;
    ArrayList<String> alldata3;
    ArrayList<Integer> alldata33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_layout);

        placeorder = findViewById(R.id.placeorder);
        categorycheckBox = findViewById(R.id.categorycheckBox);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        addressTextView = findViewById(R.id.addresstextview);
        addressTextView.setMaxLines(1);
        addressTextView.setEllipsize(TextUtils.TruncateAt.END); // Ensure to use TextUtils.TruncateAt
        addressTextView.setText("Your long text goes here...");
        addressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the full name in a Toast message
                Toast.makeText(CheckoutPayment.this, addressTextView.getText(), Toast.LENGTH_LONG).show();

                // Or, show the full name in a dialog
            /*AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setMessage(textView1.getText());
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();*/
            }
        });
        eventTimeEditText = findViewById(R.id.eventTimeEditText);
        eventDateEditText = findViewById(R.id.eventDateEditText);
//
//        categorycheckBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!categorycheckBox.isChecked()) {
//                    // If the checkbox is not checked, show a toast and prevent navigation
//                    Toast.makeText(getApplicationContext(), "Please agree to the policy", Toast.LENGTH_SHORT).show();
//                } else {
//                    // If the checkbox is checked, proceed to the next page
//                    // Place your navigation logic here, for example:
////                     startActivity(new Intent(CheckoutPayment.this, OrderAcceptedPage.class));
//                }
//            }
//        });

//       // String eventDate = eventDateEditText.getText().toString();
//       // String eventTime = eventTimeEditText.getSelectedItem().toString();
//        String totalAmount = totalAmountTextView.getText().toString();
//        String address = addressTextView.getText().toString();
//
//        if ( totalAmount.isEmpty() || address.isEmpty()) {
//            showToast("Please enter the required fields.");
//            return;
//        }

        spinnerDataList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerDataList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventTimeEditText.setAdapter(adapter);

        placeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventDateEditText == null || eventTimeEditText == null || totalAmountTextView == null || addressTextView == null ) {
                    showToast("Error: Some views are not initialized.");
                    return;
                }

                // Retrieve data from views
                String eventDate = eventDateEditText.getText().toString();
                String eventTime = (eventTimeEditText.getSelectedItem() != null) ? eventTimeEditText.getSelectedItem().toString() : "";
                String totalAmount = totalAmountTextView.getText().toString();
                String address1 = addressTextView.getText().toString();
               //
                // String checkkbox = categorycheckBox.getText().toString();


                // Check if the required fields are filled
                if (eventDate.isEmpty() || eventTime.isEmpty() || totalAmount.isEmpty() || address1.isEmpty() ) {
                    showToast("Please enter the required fields.");
                    return; // Do not proceed further if fields are not filled
                }
                SharedPreferences sharedPreferencess = getSharedPreferences("AddressPreferences", Context.MODE_PRIVATE);
                int addressId = sharedPreferencess.getInt("defaultAddressId", -1);
                String address = sharedPreferencess.getString("defaultAddress", "");
                String pincode = sharedPreferencess.getString("defaultPincode", "");
                String state = sharedPreferencess.getString("defaultState", "");
                String phone = sharedPreferencess.getString("defaultPhone", "");
                int txnid = 0;
                int gst = 0;
                int refercredit = 0;

                SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

                token = sharedpreferences.getString("token", "");
                price = sharedpreferences.getString("totalAmount", "");
                int Id = sharedpreferences.getInt("slot_id", -1);

                String name = sharedpreferences.getString("name", "");
                Log.e("checkout ka name", name);
                String quantity = sharedpreferences.getString("quantity", "");

                String sellingprice = sharedpreferences.getString("sellingprice", "");
                Log.e("checkout ki selling price", sellingprice);

                int productid = sharedpreferences.getInt("productid", -1);
                String image = sharedpreferences.getString("image", "");

                int cashbackprice = 0;
                int cashbackdate = 0;
                int cashbackpercentage = 0;
                String mrpprice = "240";
                String color = "Black";

                Date d = new Date();
                CharSequence seee = DateFormat.format("dd MM yyyy", d.getTime());

                databaseHelperClass = new dphelper(CheckoutPayment.this);
                paym = databaseHelperClass.getcart();

                JSONArray jsonArray = new JSONArray();

                //ArrayList<mycartmodel12> carts = new ArrayList<>();

                for (mycartmodel12 item : paym) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name", item.getName());
                        jsonObject.put("quantity", item.getQuantity());
                        jsonObject.put("price", item.getPrice());
                        jsonObject.put("date", seee);
                        jsonObject.put("id", item.getId());
                        jsonObject.put("image", item.getImage());
                        jsonObject.put("color", "Black");
                        jsonObject.put("final", item.getFinal_price());
                        jsonObject.put("cashbackprice", 0);
                        jsonObject.put("cashbackdate", 0);
                        jsonObject.put("cashbackpercentage", 0);
                       // carts.add( item.getProduct_name());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    jsonArray.put(jsonObject);
                }
                cartarray = jsonArray.toString();

                HashMap<String, String> requestData = new HashMap<>();
                requestData.put("shippingaddress", address);
                requestData.put("shippingzip", pincode);
                requestData.put("shippingstate", state);
                requestData.put("mobile", phone);
                requestData.put("slot_id", String.valueOf(Id));
                requestData.put("finalprice", price);
                requestData.put("txnid", String.valueOf(txnid));
                requestData.put("gst", String.valueOf(gst));
                requestData.put("billingaddress_id", String.valueOf(addressId));
                requestData.put("refercredit", String.valueOf(refercredit));
                requestData.put("cart", cartarray);

                PaymentRequestModel pay = new PaymentRequestModel();
                pay.setShippingaddress(address);
                pay.setShippingzip(pincode);
                pay.setShippingstate(state);
                pay.setMobile(phone);
                pay.setSlot_id(Id);
                pay.setFinalprice(price);
                pay.setTxnid(txnid);
                pay.setGst(gst);
                pay.setBillingaddress_id(addressId);
                pay.setRefercredit(refercredit);
                pay.setCart(paym);

                //totalAmountTextView.setText(calculateTotalAmount(paym));

                Call<PaymentModel> call = RetrofitClient.getInstance().getMyApi().paymentsuccess("Bearer " + token, pay);
                call.enqueue(new Callback<PaymentModel>() {
                    @Override
                    public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                        if (response.isSuccessful()) {
                            PaymentModel paymentSuccessResponse = response.body();
                            if (paymentSuccessResponse != null) {
                                String status = paymentSuccessResponse.getStatus();
                                String code = paymentSuccessResponse.getCode();
                                String successMessage = paymentSuccessResponse.getSuccess();
                                showToast("Payment successful");
                                finish();
                            } else {
                                showToast("Failed to process payment");
                            }
                        } else {
                            showToast("Failed to process payment out");
                            Log.e("PaymentData", "Failed to update profile. Error: " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<PaymentModel> call, Throwable t) {
                        showToast("Failed to process payment. Please try again.");
                    }
                });
                Intent intent = new Intent(CheckoutPayment.this, OrderAcceptedPage.class);
                startActivity(intent);
            }
        });

        eventDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });


        SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        authorization = sharedpreferences.getString("token", "");
        Log.e(TAG, "Token value: " + authorization);

        AddressModel.NewAddressData defaultAddress = getDefaultAddressFromSharedPreferences();

        setAddressTextView(defaultAddress);

        totalAmountTextView.setText(sharedpreferences.getString("totalAmount", ""));
    }


    private String calculateTotalAmount(List<mycartmodel12> cartItems) {
        double totalAmount = 0.0;

        for (mycartmodel12 item : cartItems) {
            double itemPrice = Double.parseDouble(item.getFinal_price());
            totalAmount += itemPrice;
        }

        return String.format(Locale.getDefault(), "%.2f", totalAmount);
    }


    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, monthOfYear, dayOfMonth);
                selectedDate.set(Calendar.HOUR_OF_DAY, 0);
                selectedDate.set(Calendar.MINUTE, 0);
                selectedDate.set(Calendar.SECOND, 0);
                selectedDate.set(Calendar.MILLISECOND, 0);

                Calendar today = Calendar.getInstance();
                today.set(Calendar.HOUR_OF_DAY, 0);
                today.set(Calendar.MINUTE, 0);
                today.set(Calendar.SECOND, 0);
                today.set(Calendar.MILLISECOND, 0);

                long diffInMillis = selectedDate.getTimeInMillis() - today.getTimeInMillis();
                long dayDiff = diffInMillis / (24 * 60 * 60 * 1000);
                Log.e("date", String.valueOf(dayDiff));
                if (dayDiff >= 0 && dayDiff <= 30) {
                    String formattedDate = formatDate(year, monthOfYear, dayOfMonth);
                    eventDateEditText.setText(formattedDate);
                    callSoltAPI(formattedDate);
                } else {
                    showToast("Please select a date within the next 30 days.");
                }
            }
        }, currentYear, currentMonth, currentDay);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }

    private String formatDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }

    private void showToast(String message) {
        Toast.makeText(CheckoutPayment.this, message, Toast.LENGTH_SHORT).show();
    }

    private void callSoltAPI(String solt) {
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("datetime", solt);

        Call<solt_response_model> call = RetrofitClient.getInstance().getMyApi().solt("Bearer " + token, queryParams);
        call.enqueue(new Callback<solt_response_model>() {
            @Override
            public void onResponse(Call<solt_response_model> call, Response<solt_response_model> response) {
                if (response.isSuccessful()) {
                    solt_response_model soltResponse = response.body();
                    if (soltResponse != null && soltResponse.getSlots() != null) {
                        alldata1 = soltResponse.getSlots();
                        alldata3 = new ArrayList<>();
                        alldata33 = new ArrayList<>();

                        for(int i = 0; i < alldata1.size(); i++) {
                            String time_to = soltResponse.getSlots().get(i).getTime_to();
                            String time_from = soltResponse.getSlots().get(i).getTime_from();
                            int id = soltResponse.getSlots().get(i).getId();
                            alldata33.add(id); // Add each time value to the ArrayList
                            String combinedTime = time_from + " - " + time_to; // Concatenate from and to times
                            alldata3.add(combinedTime);

                            SharedPreferences sharedpreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("slot_id", id);
                            editor.apply();
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(CheckoutPayment.this, android.R.layout.simple_spinner_item, alldata3);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        eventTimeEditText.setAdapter(adapter);
                    } else {
                        showToast("Failed to get response from the server.");
                    }
                }
            }

            @Override
            public void onFailure(Call<solt_response_model> call, Throwable t) {
                showToast("Error: " + t.getMessage());
            }
        });
    }
    private AddressModel.NewAddressData getDefaultAddressFromSharedPreferences() {

        SharedPreferences sharedPreferencess = getSharedPreferences("AddressPreferences", Context.MODE_PRIVATE);
        int addressId = sharedPreferencess.getInt("defaultAddressId", -1);
        String companyname = sharedPreferencess.getString("defaultCompanyname", "");
        String address = sharedPreferencess.getString("defaultAddress", "");
        String pincode = sharedPreferencess.getString("defaultPincode", "");
        String state = sharedPreferencess.getString("defaultState", "");
        String city = sharedPreferencess.getString("defaultCity", "");
        String phone = sharedPreferencess.getString("defaultPhone", "");
        String landmark = sharedPreferencess.getString("defaultLandmark", "");

        Log.d(TAG, "getDefaultAddressFromSharedPreferences: Address ID: " + addressId);

        Log.d(TAG, "getDefaultAddressFromSharedPreferences: Company Name: " + companyname +
                ", Address: " + address +
                ", Pincode: " + pincode +
                ", State: " + state +
                ", City: " + city +
                ", Phone: " + phone +
                ", Landmark: " + landmark);

        AddressModel.NewAddressData defaultAddress = new AddressModel.NewAddressData(addressId, companyname, address, state, pincode, city, phone, landmark);
        defaultAddress.setDefault(true);

        return defaultAddress;
    }

    private void setAddressTextView(AddressModel.NewAddressData addressData) {
        if (addressData != null) {
            addressTextView.setText(
                    addressData.getCompanyname() +
                            "\n" + addressData.getAddress() + " " +
                            addressData.getState() + "\t" +
                            addressData.getCity() + " " +
                            addressData.getPincode() +
                            "\n" + addressData.getLandmark() +
                            "\nPhone: " + addressData.getPhone()
            );

            Log.d(TAG, "setAddressTextView: Address set successfully");
        } else {
            Log.e(TAG, "setAddressTextView: Address data is null");
        }
    }

}




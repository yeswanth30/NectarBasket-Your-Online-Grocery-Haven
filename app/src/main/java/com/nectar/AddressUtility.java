package com.nectar;// AddressUtility.java

import android.content.Context;
import android.content.SharedPreferences;

import com.nectar.Retrofitclient.AddressModel;

import java.util.List;

// AddressUtility.java

import android.content.Context;
import android.content.SharedPreferences;

public class AddressUtility {

    private static final String PREFS_NAME = "AddressPreferences";
    private static final String DEFAULT_ADDRESS_POSITION_KEY = "defaultAddressPosition";

    public static int getDefaultAddressPosition(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(DEFAULT_ADDRESS_POSITION_KEY, -1);
    }

    public static void setDefaultAddressPosition(Context context, int defaultAddressPosition) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(DEFAULT_ADDRESS_POSITION_KEY, defaultAddressPosition);
        editor.apply();
    }
}

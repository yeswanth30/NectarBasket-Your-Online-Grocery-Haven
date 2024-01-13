package com.nectar;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.picasso.BuildConfig;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build();

            StrictMode.setThreadPolicy(policy);
        }
    }
}

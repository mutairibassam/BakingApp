package com.example.android.bakingapp.networking.generators;

import android.util.Log;

import com.example.android.bakingapp.networking.Routes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {

    private static final String TAG = "DataServiceGenerator";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Routes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(TAG, "getRetrofit: inside method " + retrofit);

        }
        Log.d(TAG, "getRetrofit: outside " + retrofit);
        return retrofit;
    }

}


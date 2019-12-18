package com.example.android.bakingapp.networking.generators;

import android.util.Log;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.networking.Routes;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {

    private static final String TAG = "DataServiceGenerator";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Routes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(TAG, "getRetrofit() method " + retrofit);

        }

        return retrofit;

    }

}


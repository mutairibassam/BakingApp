package com.example.android.bakingapp.networking.api;

import com.example.android.bakingapp.networking.Routes;
import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET(Routes.END_POINT)
    Call<JsonArray> fetchBakingData();
}

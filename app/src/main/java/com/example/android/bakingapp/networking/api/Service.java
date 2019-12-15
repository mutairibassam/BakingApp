package com.example.android.bakingapp.networking.api;

import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.networking.Routes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<BakingProcess> fetchBakingData();


}

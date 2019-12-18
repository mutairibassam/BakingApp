package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.adapter.BakingAdapter;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.networking.Routes;
import com.example.android.bakingapp.networking.api.Service;
import com.example.android.bakingapp.networking.generators.DataServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    List<Ingredient> mItem = new ArrayList<>();
    List<BakingProcess> mBakingProcessList = new ArrayList<>();
    RecyclerView mRecyclerView;
    BakingAdapter mBakingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchBakingDataFromJSON();
    }

    private void fetchBakingDataFromJSON() {

        Service service = DataServiceGenerator.getRetrofit().create(Service.class);
        Log.d(TAG, "fetchBakingDataFromJSON: " + service);
        Call<List<BakingProcess>> call = service.fetchBakingData();
        call.enqueue(new Callback<List<BakingProcess>> () {
            @Override
            public void onResponse(Call<List<BakingProcess>> call, Response<List<BakingProcess>> response) {
                Log.d(TAG, "onResponse:  response " + response);
                mBakingProcessList = response.body();
                Log.d(TAG, "onResponse: \n" + mBakingProcessList);

                mRecyclerView = findViewById(R.id.recycler_view_id);
                mBakingAdapter = new BakingAdapter(getApplicationContext(), mBakingProcessList);
                mRecyclerView.setAdapter(mBakingAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<BakingProcess>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });

    }
}
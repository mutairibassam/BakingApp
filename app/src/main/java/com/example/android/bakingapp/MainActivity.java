package com.example.android.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredient;
import com.example.android.bakingapp.networking.api.Service;
import com.example.android.bakingapp.networking.generators.DataServiceGenerator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchBakingDataFromJSON();
    }

    private void fetchBakingDataFromJSON() {

        Service service = DataServiceGenerator.getRetrofit().create(Service.class);
        Log.d(TAG, "fetchBakingDataFromJSON: " + service);
        Call<BakingProcess> call = service.fetchBakingData();

        call.enqueue(new Callback<BakingProcess>() {
            @Override
            public void onResponse(Call<BakingProcess> call, Response<BakingProcess> response) {

              List<Ingredient> ingredientsList = response.body().getIngredients();
                Log.d(TAG, "onResponse: " + ingredientsList);

            }

            @Override
            public void onFailure(Call<BakingProcess> call, Throwable t) {
                Log.e(TAG, "onFailure: something went wrong" + t.getMessage());
            }
        });
    }
}
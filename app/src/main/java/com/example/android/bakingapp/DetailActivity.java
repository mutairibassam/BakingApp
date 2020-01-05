package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.adapter.IngredientAdapter;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.networking.api.Service;
import com.example.android.bakingapp.networking.generators.DataServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private RecyclerView mRecyclerView;
    private BakingProcess ingredients;
    private List<Ingredients> ingredientsList;
    private IngredientAdapter ingredientAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        Intent intentThatStartedThisActivity = getIntent();
        if(intentThatStartedThisActivity.hasExtra("ingredients")) {
            ingredients = getIntent().getParcelableExtra("ingredients");
            ingredientsList = ingredients.getIngredients();

            mRecyclerView = findViewById(R.id.recycler_view_ingredient_id);
            ingredientAdapter = new IngredientAdapter(this, ingredientsList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(ingredientAdapter);

        }

    }

}

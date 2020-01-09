package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.adapter.BakingAdapter;
import com.example.android.bakingapp.adapter.IngredientAdapter;
import com.example.android.bakingapp.adapter.StepAdapter;
import com.example.android.bakingapp.fragment.RecipeStepsFragment;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Step;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private RecyclerView mRecyclerView, stepRecyclerView;
    private BakingProcess ingredients;
    private List<Ingredients> ingredientsList;
    private IngredientAdapter ingredientAdapter;

    private List<Step> stepList;
    private StepAdapter stepAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

//        Intent intentThatStartedThisActivity = getIntent();
//        if(intentThatStartedThisActivity.hasExtra("ingredients")) {
//            ingredients = getIntent().getParcelableExtra("ingredients");
//            ingredientsList = ingredients.getIngredients();
//            Log.d(TAG, "DetailActivity: " + ingredientsList);

//            mRecyclerView = findViewById(R.id.recycler_view_ingredient_id);
//            ingredientAdapter = new IngredientAdapter(this, ingredientsList);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mRecyclerView.setAdapter(ingredientAdapter);
//
//            stepList = ingredients.getSteps();
//            stepRecyclerView = findViewById(R.id.recycler_view_steps_id);
//            stepAdapter = new StepAdapter(this, stepList);
//            stepRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            stepRecyclerView.setAdapter(stepAdapter);


//    }
//


        // ################# NEW CODE ##################### //

        RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
        Intent intentCameFromBakingAdapter = getIntent();
        if(intentCameFromBakingAdapter.hasExtra(getString(R.string.ingredients_key))) {
            ingredients = getIntent().getParcelableExtra(getString(R.string.ingredients_key));
            Bundle bundle = new Bundle();
            bundle.putParcelable(getString(R.string.step_key), ingredients);
            recipeStepsFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.fragment_recipe_list_id, recipeStepsFragment).commit();
        }
    }

}

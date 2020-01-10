package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.bakingapp.fragment.RecipeStepsFragment;
import com.example.android.bakingapp.model.BakingProcess;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private BakingProcess ingredients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        Intent intentCameFromBakingAdapter = getIntent();
        if(intentCameFromBakingAdapter.hasExtra(getString(R.string.ingredients_key))) {
            ingredients = getIntent().getParcelableExtra(getString(R.string.ingredients_key));
            RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(getString(R.string.step_key), ingredients);
            recipeStepsFragment.setArguments(bundle);

            String backStackName = getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
            if(!fragmentPopped) {
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_recipe_list_id, recipeStepsFragment, backStackName)
                        .addToBackStack(backStackName)
                        .commit();
            }
        }
    }
}

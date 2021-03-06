package com.example.android.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.bakingapp.adapter.StepAdapter;
import com.example.android.bakingapp.fragment.RecipeStepsFragment;
import com.example.android.bakingapp.fragment.RecipeStepsVideoFragment;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Step;
import com.google.android.exoplayer2.C;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private BakingProcess ingredients;
    private long id;
    private SharedPreferences sharedPreferences;
    private LinearLayout linearLayout;
    String recipeName;

    List<Ingredients> listOfIngredients;

    public static boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        Intent intentCameFromBakingAdapter = getIntent();
        linearLayout = findViewById(R.id.recipe_detail);

        ingredients = getIntent().getParcelableExtra(getString(R.string.ingredients_key));
        listOfIngredients = ingredients.getIngredients();

        id = ingredients.getId();
        recipeName = ingredients.getName();
        if (intentCameFromBakingAdapter.hasExtra(getString(R.string.ingredients_key))) {
            ingredients = getIntent().getParcelableExtra(getString(R.string.ingredients_key));
            Bundle bundle = new Bundle();
            bundle.putParcelable(getString(R.string.step_key), ingredients);

            if (savedInstanceState == null) {

                if (findViewById(R.id.fragmentipad_recipe_list_id) != null) {

                    mTwoPane = true;

                    //Tablet
                    RecipeStepsFragment recipeStepsFragmentForTablet = new RecipeStepsFragment();
                    recipeStepsFragmentForTablet.setArguments(bundle);

                    FragmentManager fragmentManagerTablet = getSupportFragmentManager();

                    String backStackNameForTablet = getClass().getName();
                    boolean fragmentPoppedTablet = fragmentManagerTablet.popBackStackImmediate(backStackNameForTablet, 0);
                    if (!fragmentPoppedTablet) {
                        fragmentManagerTablet.beginTransaction()
                                .replace(R.id.fragmentipad_recipe_list_id, recipeStepsFragmentForTablet, backStackNameForTablet)
                                .addToBackStack(backStackNameForTablet)
                                .commit();

                    }
                }
                else {
                        //mobile
                        mTwoPane = false;
                        RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
                        String backStackName = getClass().getName();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStackName, 0);
                        bundle.putParcelable(getString(R.string.step_key), ingredients);
                        recipeStepsFragment.setArguments(bundle);
                        if (!fragmentPopped) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_recipe_list_id, recipeStepsFragment)
                                    .commit();
                        }


                    }
                }
            }
        }

    private String ingredientToString() {
        StringBuilder result = new StringBuilder();
        for(Ingredients ingredients : listOfIngredients) {
            result.append("- " + ingredients.getIngredient() + "\n");
        }
        return result.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        // persistence.  Set checked state based on the fetchPopular boolean
        sharedPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        if ((sharedPreferences.getInt("ID", -1) == id)){
            menu.findItem(R.id.fav_widget).setIcon(R.drawable.ic_favorite_white_24dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.fav_widget){
            boolean isRecipeInWidget = (sharedPreferences.getInt("ID", -1) == id);

            // If recipe already in widget, remove it
            if (isRecipeInWidget){
                sharedPreferences.edit()
                        .remove("ID")
                        .remove("WIDGET_TITLE")
                        .remove("WIDGET_LIST")
                        .apply();

                item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                Snackbar.make(findViewById(android.R.id.content), "Widget Recipe Removed", Snackbar.LENGTH_SHORT).show();
            }
            // if recipe not in widget, then add it
            else{
                sharedPreferences
                        .edit()
                        .putInt("ID", (id))
                        .putString("WIDGET_TITLE", recipeName)
                        .putString("WIDGET_LIST",ingredientToString())
                        .apply();

                item.setIcon(R.drawable.ic_favorite_white_24dp);
                Snackbar.make(findViewById(android.R.id.content), "Widget Recipe Added", Snackbar.LENGTH_SHORT).show();
            }

            // Put changes on the Widget
            ComponentName provider = new ComponentName(this, BakeAppWidgetProvider.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] ids = appWidgetManager.getAppWidgetIds(provider);
            BakeAppWidgetProvider bakingWidget = new BakeAppWidgetProvider();
            bakingWidget.onUpdate(this, appWidgetManager, ids);
        }

        return super.onOptionsItemSelected(item);


    }

//
//    @Override
//    public void onNextClicked(int index) {
//        index++;
//        openFragment(index);
//    }
//
//    @Override
//    public void onPreClicked(int index) {
//        index--;
//        openFragment(index);
//    }
//
//    private void openFragment(int index){
//        RecipeStepsVideoFragment recipeStepsVideoFragment = new RecipeStepsVideoFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(this.getString(R.string.video_key), ingredients);
//        bundle.putInt("index", index);
//        recipeStepsVideoFragment.setArguments(bundle);
//        FragmentManager fragmentManager = this.getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.fragment_recipe_list_id,
//                recipeStepsVideoFragment)
//                .commit();
//    }

}

package com.example.android.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.IngredientAdapter;
import com.example.android.bakingapp.adapter.StepAdapter;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Step;

import java.util.List;

public class RecipeStepsFragment extends Fragment {

    public RecipeStepsFragment() {
    }

//    OnImageClickListener mCallback;
//
//    public interface OnImageClickListener {
//        void onImageSelected(int position);
//    }

    /**
     * to make sure the host activity implement the callback
     */
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        try {
//            mCallback = (OnImageClickListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + "must implement OnImageClickListener");
//        }
//    }

    private Context mContext;

    private RecyclerView ingredientRecyclerView;
    private BakingProcess bakingProcess;
    private List<Ingredients> ingredientsList;
    private IngredientAdapter ingredientAdapter;

    private RecyclerView stepRecyclerView;
    private List<Step> stepList;
    private StepAdapter stepAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            bakingProcess = bundle.getParcelable(getString(R.string.step_key));
            ingredientsList = bakingProcess.getIngredients();
            ingredientRecyclerView = rootView.findViewById(R.id.recycler_view_ingredient_id);
            ingredientAdapter = new IngredientAdapter(mContext, ingredientsList);
            ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            ingredientRecyclerView.setAdapter(ingredientAdapter);

            stepList = bakingProcess.getSteps();
            stepRecyclerView = rootView.findViewById(R.id.recycler_view_steps_id);
            stepAdapter = new StepAdapter(mContext, stepList);
            stepRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            stepRecyclerView.setAdapter(stepAdapter);

        }

        return rootView;

    }
}

package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredients;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder> {

    private static final String TAG = "IngredientAdapter";

    private Context mContext;
    private List<Ingredients> ingredientList;

    public IngredientAdapter(Context mContext, List<Ingredients> ingredientList) {
        this.mContext = mContext;
        this.ingredientList = ingredientList;
    }

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView mIngredient;

        public IngredientAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mIngredient = itemView.findViewById(R.id.ingredient_recipe_row_item_id);

        }
    }

    @NonNull
    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_row_item, parent, false);
        IngredientAdapterViewHolder holder = new IngredientAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapterViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");

        holder.mIngredient.setText(ingredientList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }


}

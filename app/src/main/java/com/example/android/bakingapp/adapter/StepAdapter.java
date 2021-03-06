package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.DetailActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.fragment.RecipeStepsVideoFragment;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private static final String TAG = "StepAdapter";
    private Context mContext;
    private List<Step> stepList;

    public StepAdapter(Context mContext, List<Step> stepList) {
        this.mContext = mContext;
        this.stepList = stepList;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mDesc;
        int id;

        final View view;

        public StepAdapterViewHolder(@NonNull final View itemView) {
            super(itemView);

            view = itemView;

            view.setOnClickListener(this);
            mDesc = itemView.findViewById(R.id.description_step_row_item_id);

        }

        @Override
        public void onClick(View view) {
            if(DetailActivity.mTwoPane == true) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Step item = stepList.get(position);
                    RecipeStepsVideoFragment recipeStepsVideoFragment = new RecipeStepsVideoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(view.getContext().getString(R.string.video_key), item);
                    recipeStepsVideoFragment.setArguments(bundle);

                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container_detail, recipeStepsVideoFragment)
                            .commit();
                }
            }   else {
                DetailActivity.mTwoPane = false;
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Step item = stepList.get(position);
                    List<Step> arrayList = stepList;
                    RecipeStepsVideoFragment recipeStepsVideoFragment = new RecipeStepsVideoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(view.getContext().getString(R.string.video_key), item);
                    bundle.putParcelableArrayList(view.getContext().getString(R.string.array_key), (ArrayList<? extends Parcelable>) arrayList);
                    recipeStepsVideoFragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((FragmentActivity) view.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_recipe_list_id,
                            recipeStepsVideoFragment)
                            .commit();
                }
            }
        }
    }


    @NonNull
    @Override
    public StepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_row_item, parent, false);
        StepAdapterViewHolder holder = new StepAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepAdapterViewHolder holder, int position) {
        holder.mDesc.setText(stepList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

}

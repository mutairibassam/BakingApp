package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.BakingProcess;
import com.example.android.bakingapp.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.BakingAdapterViewHolder> {

    private static final String TAG = "BakingAdapter";

    private Context mContext;
    private List<BakingProcess> mNumberOfItems;

    public BakingAdapter(Context mContext, List<BakingProcess> mNumberOfItems) {
        this.mContext = mContext;
        this.mNumberOfItems = mNumberOfItems;
    }

    public class BakingAdapterViewHolder extends RecyclerView.ViewHolder {
        final View view;

        TextView mTitle;

        public BakingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            mTitle = itemView.findViewById(R.id.item_title);

        }
    }

    //################### Methods ########################//

    @NonNull
    @Override
    public BakingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        BakingAdapterViewHolder holder = new BakingAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BakingAdapterViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
        holder.mTitle.setText(mNumberOfItems.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Position: "+ position, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNumberOfItems.size();
    }

}

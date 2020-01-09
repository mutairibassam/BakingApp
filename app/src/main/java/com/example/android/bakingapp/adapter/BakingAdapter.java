package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.DetailActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.BakingProcess;

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

        TextView mTitle;

        final View view;

        public BakingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.item_title);
            view = itemView;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        BakingProcess item = mNumberOfItems.get(position);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra(mContext.getString(R.string.ingredients_key), item);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);

                    }
                }
            });

        }
    }

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

    }

    @Override
    public int getItemCount() {
        return mNumberOfItems.size();
    }


}

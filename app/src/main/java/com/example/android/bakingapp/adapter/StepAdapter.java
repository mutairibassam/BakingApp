package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepAdapterViewHolder> {

    private Context mContext;
    private List<Step> stepList;

    public StepAdapter(Context mContext, List<Step> stepList) {
        this.mContext = mContext;
        this.stepList = stepList;
    }

    public class StepAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView mDesc;

        final View view;

        public StepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            mDesc = itemView.findViewById(R.id.description_step_row_item_id);
//            mShortDesc = itemView.findViewById(R.id.shortDescription_step_row_item_id);
//            mVideoURL = itemView.findViewById(R.id.videoURL_step_row_item_id);
//            mThumbnailURL = itemView.findViewById(R.id.thumbnailURL_step_row_item_id);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Step item = stepList.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("recipeVideo", item);
                    }
                }
            });

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

//        String videoURL = stepList.get(position).getVideoURL();
//        holder.mVideoURL.setText(videoURL);
//        Linkify.addLinks(holder.mVideoURL, Linkify.WEB_URLS);
//        Glide.with(mContext).load(videoURL).placeholder(R.drawable.ic_launcher_background).into(holder.mVideoURL);

//        String thumbnailURL = stepList.get(position).getThumbnailURL();
//        holder.mThumbnailURL.setText(thumbnailURL);
//        Linkify.addLinks(holder.mThumbnailURL, Linkify.WEB_URLS);
//        Glide.with(mContext).load(thumbnailURL).placeholder(R.drawable.ic_launcher_foreground).into(holder.mThumbnailURL);
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }
}

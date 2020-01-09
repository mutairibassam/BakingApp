package com.example.android.bakingapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;

public class RecipeStepsVideoFragment extends Fragment {

    private Context mContext;
    private Step step;
    private String video;

    private ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_videos_list, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            step = bundle.getParcelable("recipeVideo");
            video = step.getVideoURL();

            imageView = rootView.findViewById(R.id.img_media_id);

            Glide.with(mContext).load(video).placeholder(R.drawable.ic_launcher_background).into(imageView);

        }


        return rootView;
    }
}

package com.example.android.bakingapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class RecipeStepsVideoFragment extends Fragment {

    public static final String LIST_VIDEO = "list_video";
    public static final String LIST_DESC = "list_desk";

    private Context mContext;
    private Step step;
    private List<Step> stepList;

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;

    String videoUrl;
    String descList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_videos_list, container, false);

        if(savedInstanceState != null) {
            videoUrl = savedInstanceState.getString(LIST_VIDEO);
            descList = savedInstanceState.getString(LIST_DESC);
        }

        TextView desc = rootView.findViewById(R.id.desc_id);
        simpleExoPlayerView = rootView.findViewById(R.id.playerView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            step = bundle.getParcelable(getString(R.string.video_key));
            String video = step.getVideoURL();
            String descList = step.getDescription();

            desc.setText(descList);

            if (!video.equals("")) {
                simpleExoPlayerView.setVisibility(View.VISIBLE);
                videoUrl = video;
                initializePlayer(Uri.parse(videoUrl));
            } else {
                simpleExoPlayerView.setVisibility(View.GONE);
            }
        } else {
            String video = step.getVideoURL();
            String descList = step.getDescription();
            desc.setText(descList);

            if(video != null) {
                if(video.equals("")) {
                    simpleExoPlayerView.setVisibility(View.VISIBLE);
                    videoUrl = video;
                    initializePlayer(Uri.parse(videoUrl));
                } else {
                    simpleExoPlayerView.setVisibility(View.GONE);
                }
            }

        }

        return rootView;
    }

    private void initializePlayer(Uri mediaUri) {
        if(simpleExoPlayer == null) {

            Context context = getActivity().getApplicationContext();
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            String userAgent = Util.getUserAgent(context, "Baking App");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    context, userAgent), new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if(simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(LIST_VIDEO, videoUrl);
        outState.putString(LIST_DESC, descList);
    }


}

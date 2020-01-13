package com.example.android.bakingapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private Context mContext;
    private Step step;
    private List<Step> stepList;

    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    private TextView desc;
    private Button navBack, navNext;
    private String videoUrl;
    int stepid;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_videos_list, container, false);


//        if(savedInstanceState != null) {
//            simpleExoPlayer = savedInstanceState.getseek("boo");
//            simpleExoPlayer.seekTo(currentWindow, playbackPosition);
//            simpleExoPlayerView.setPlayer(simpleExoPlayer);
//
//        }

            desc = rootView.findViewById(R.id.desc_id);
            simpleExoPlayerView = rootView.findViewById(R.id.playerView);
//            navBack = rootView.findViewById(R.id.nav_back);
//            navNext = rootView.findViewById(R.id.nav_next);


            Bundle bundle = this.getArguments();
            if (bundle != null) {

                step = bundle.getParcelable(getString(R.string.video_key));
                stepid = step.getId();
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

                if (video != null) {
                    if (video.equals("")) {
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

    private void next() {
        stepid++;

    }

    private void back() {
        stepid--;
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
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            simpleExoPlayer.seekTo(currentWindow, playbackPosition);
            simpleExoPlayer.prepare(mediaSource, false, false);
        }
    }

    private void releasePlayer() {
        if(simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer.getPlayWhenReady();
            playbackPosition = simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
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
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            releasePlayer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initializePlayer(Uri.parse(videoUrl));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        hideSystemUi();
        if ((Util.SDK_INT < 24 || simpleExoPlayer == null)) {
            initializePlayer(Uri.parse(videoUrl));
        }
    }

//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        simpleExoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }



//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//            outState.putInt("int", currentWindow);
//            outState.putBoolean("boo", playWhenReady);
//    }
//
//    @Override
//    public void onRestoreInstanceSatae(Bundle inState) {
//        super.onSaveInstanceState(inState);
//
//    }


}

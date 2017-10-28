package com.example.alexey.audiostreamer.ui.details;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.ui.base.BaseFragment;
import com.ohoussein.playpause.PlayPauseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.graphics.PorterDuff.Mode.MULTIPLY;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by alexey
 */

public class DetailsFragment extends BaseFragment implements DetailsContract.View {

    private static final String STATION_BUNDLE_KEY = "station";

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.play_btn)
    PlayPauseView playButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    DetailsContract.Presenter<DetailsContract.View> presenter;

    public static DetailsFragment newInstance(Station station) {
        Bundle args = new Bundle();

        args.putParcelable(STATION_BUNDLE_KEY, station);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_station, container, false);

        FragmentComponent component = getComponent();
        component.inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        Station station = getArguments().getParcelable(STATION_BUNDLE_KEY);
        presenter.setStation(station);

        setUpViews();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.destroyMediaPlayer();
    }

    private void setUpViews() {
        presenter.takeView(this);

        togglePlayButton(false);

        int color = getContext().getResources().getColor(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ColorStateList stateList = ColorStateList.valueOf(color);
            progressBar.setIndeterminateTintList(stateList);
        } else {
            progressBar.getIndeterminateDrawable()
                    .setColorFilter(color, MULTIPLY);
        }
    }

    @Override
    public void togglePlayButton(boolean enabled) {
        playButton.setEnabled(enabled);

        int enabledColor = getContext().getResources().getColor(R.color.colorPrimary);
        int disabledColor = getContext().getResources().getColor(R.color.disableButtonColor);

        playButton.setColor(enabled ? enabledColor : disabledColor);
    }

    @OnClick(R.id.play_btn)
    public void onClick() {
        presenter.togglePlaying();

        playButton.change(!playButton.isPlay());
    }

    @Override
    public void setName(String text) {
        name.setText(text);
    }

    @Override
    public void setImage(String imageUrl) {
        Glide.with(getContext())
                .load(imageUrl)
                .asBitmap()
                .fitCenter()
                .into(image);
    }

    @Override
    public void toggleProgressBar(boolean visibility) {
        progressBar.setVisibility(visibility ? VISIBLE : GONE);
    }

    @Override
    protected String getToolbarTitle() {
        return "Details";
    }

    @Override
    protected boolean showsBackButton() {
        return true;
    }

}

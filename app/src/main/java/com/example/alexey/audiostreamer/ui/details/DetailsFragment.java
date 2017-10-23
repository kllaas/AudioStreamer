package com.example.alexey.audiostreamer.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.ui.base.BaseFragment;
import com.ohoussein.playpause.PlayPauseView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    PlayPauseView palyButton;

    @Inject
    DetailsContract.Presenter<DetailsContract.View> presenter;

    public static DetailsFragment newInstance(Station station) {
        Bundle args = new Bundle();

        args.putSerializable(STATION_BUNDLE_KEY, station);

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

        Station station = (Station) getArguments().getSerializable(STATION_BUNDLE_KEY);

        presenter.setStation(station);
        presenter.takeView(this);

        return view;
    }

    @OnClick(R.id.play_btn)
    public void onClick() {
        if (palyButton.isPlay()) {
            presenter.stopPlaying();
        } else {
            presenter.startPlaying();
        }
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
    protected String getToolbarTitle() {
        return "Details";
    }

    @Override
    protected boolean showsBackButton() {
        return true;
    }

}

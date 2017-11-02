package com.example.alexey.audiostreamer.ui.details_item;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.ui.base.BaseFragment;
import com.example.alexey.audiostreamer.utils.ImageUtils;
import com.ohoussein.playpause.PlayPauseView;
import com.pnikosis.materialishprogress.ProgressWheel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by alexey
 */

public class DetailsFragment extends BaseFragment implements DetailsMVPContract.View {

    private static final String ID_BUNDLE_KEY = "station_id";

    @BindView(R.id.thumb)
    ImageView thumb;

    @BindView(R.id.details_container)
    View rootView;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.categories)
    TextView categories;

    @BindView(R.id.listeners)
    TextView listeners;

    @BindView(R.id.play_btn)
    PlayPauseView playButton;

    @BindView(R.id.progress_bar)
    ProgressWheel progressBar;

    @Inject
    DetailsMVPContract.Presenter<DetailsMVPContract.View> presenter;

    public static DetailsFragment newInstance(Long stationId) {
        Bundle args = new Bundle();

        args.putSerializable(ID_BUNDLE_KEY, stationId);

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

        Long stationId = (Long) getArguments().getSerializable(ID_BUNDLE_KEY);
        presenter.setStationId(stationId);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onDestroy();
    }

    @Override
    protected void setUpViews() {
        presenter.takeView(this);
    }

    @OnClick(R.id.thumb)
    public void onClick() {
        presenter.togglePlaying();

        playButton.toggle();
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
                .into(new SimpleTarget<Bitmap>(100, 100) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                        setThumbColor(resource);

                        setBlurredImage(resource);
                    }
                });
    }

    @Override
    public void setCategories(String text) {
        categories.setText(String.format(getString(R.string.categories_description), text));
    }

    @Override
    public void setListeners(int count) {
        listeners.setText(String.format(getString(R.string.listeners_n), count));
    }

    private void setThumbColor(Bitmap resource) {
        thumb.setImageBitmap(resource);
        thumb.setColorFilter(getContext().getResources().getColor(R.color.colorGray),
                PorterDuff.Mode.DARKEN);
    }

    private void setBlurredImage(Bitmap resource) {
        Observable.fromCallable(() ->
                ImageUtils.createBlurredImageFromBitmap(resource, getContext(), 2))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drawable -> rootView.setBackground(drawable));
    }

    @Override
    public void setProgress(boolean finished) {
        if (finished) {
            progressBar.setProgress(1);
        } else {
            progressBar.spin();
        }
    }

    @Override
    public void setProgressBarVisibility(boolean visibility) {
        Log.d("progressBar", visibility + "");
        progressBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

}

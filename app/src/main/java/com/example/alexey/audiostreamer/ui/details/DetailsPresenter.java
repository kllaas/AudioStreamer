package com.example.alexey.audiostreamer.ui.details;


import android.media.MediaPlayer;

import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class DetailsPresenter<V extends DetailsContract.View>
        extends BasePresenterImpl<V> implements DetailsContract.Presenter<V> {

    @Inject
    MediaPlayer mediaPlayer;

    private Station station;

    @Inject
    DetailsPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                     CompositeDisposable compositeDisposable, NavigationManager navigationManager,
                     MediaPlayer mediaPlayer) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);

        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected void onViewPrepared() {
        if (station == null) return;

        setUpMediaPlayer(station);

        if (station.getImage() != null && station.getImage().getUrlToImage() != null) {
            getView().setImage(station.getImage().getUrlToImage());
        }

        if (station.getName() != null)
            getView().setName(station.getName());
    }

    private void setUpMediaPlayer(Station station) {
        int streamsAmount = station.getStreams().size() - 1;

        try {
            mediaPlayer.setOnPreparedListener(mp -> {
                getView().togglePlayButton(true);
                getView().toggleProgressBar(false);
            });

            mediaPlayer.reset();
            mediaPlayer.setDataSource(station.getStreams().get(streamsAmount).getUrl());
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public void togglePlaying() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    @Override
    public void destroyMediaPlayer() {
        if (mediaPlayer == null) return;

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}

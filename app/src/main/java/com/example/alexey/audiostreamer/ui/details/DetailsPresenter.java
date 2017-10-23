package com.example.alexey.audiostreamer.ui.details;


import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class DetailsPresenter<V extends DetailsContract.View>
        extends BasePresenterImpl<V> implements DetailsContract.Presenter<V> {

    private Station station;

    @Inject
    DetailsPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                     CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    protected void onViewPrepared() {
        if (station == null) return;

        if (station.getImage() != null && station.getImage().getUrlToImage() != null) {
            getView().setImage(station.getImage().getUrlToImage());
        }

        if (station.getName() != null)
            getView().setName(station.getName());
    }

    @Override
    public void stopPlaying() {

    }

    @Override
    public void startPlaying() {

    }

    @Override
    public void setStation(Station station) {
        this.station = station;
    }
}

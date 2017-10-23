package com.example.alexey.audiostreamer.ui.list;


import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class ListPresenter<V extends ListContract.View>
        extends BasePresenterImpl<V> implements ListContract.Presenter<V> {

    @Inject
    ListPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    public void onItemClick(Station station) {
//        App.appComponent.getNavigationManager().openDetailsFragment(station);
    }

    @Override
    protected void onViewPrepared() {
        getView().showLoading();

        getCompositeDisposable().add(getRepository()
                        .getStations()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(this::onStationsLoaded,
                                   throwable -> onLoadingFailed(throwable.getLocalizedMessage()))
        );
    }

    private void onLoadingFailed(String message) {
        System.out.println(message);

        getView().hideLoading();
    }

    private void onStationsLoaded(List<Station> stations) {
        if (stations != null && stations.size() != 0) {
            getView().updateList(stations);
        }

        getView().hideLoading();
    }
}

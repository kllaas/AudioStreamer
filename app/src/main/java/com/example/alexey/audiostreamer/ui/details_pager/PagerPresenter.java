package com.example.alexey.audiostreamer.ui.details_pager;


import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class PagerPresenter<V extends PagerMVPContract.View>
        extends BasePresenterImpl<V> implements PagerMVPContract.Presenter<V> {

    private Long currStation;

    private List<Long> ids;

    private PagerAdapter adapter;

    @Inject
    PagerPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                   CompositeDisposable compositeDisposable, NavigationManager navigationManager,
                   PagerAdapter adapter) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);

        this.adapter = adapter;
    }

    @Override
    protected void onViewPrepared() {
        adapter.setStationIds(ids);
        getView().initPager(adapter, getStationPosition(ids, currStation));
    }

    private int getStationPosition(List<Long> ids, Long currStation) {
        for (int i = 0; i < ids.size(); i++) {
            if (currStation.equals(ids.get(i)))
                return i;
        }

        return 0;
    }

    @Override
    public void setCurrStation(Long currStation) {
        this.currStation = currStation;
    }

    @Override
    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}

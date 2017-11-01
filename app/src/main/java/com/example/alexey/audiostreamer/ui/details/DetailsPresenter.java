package com.example.alexey.audiostreamer.ui.details;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import com.example.alexey.audiostreamer.PlayerService;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class DetailsPresenter<V extends DetailsMVPContract.View>
        extends BasePresenterImpl<V> implements DetailsMVPContract.Presenter<V> {

    private Station station;

    private boolean playing;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() == null) return;

            boolean isPrepared = intent.getExtras().getBoolean(PlayerService.EXTRA_PLAYER_PREPARED);
            Log.d("BroadcastReceiver", "onReceive: " + isPrepared);

            if (isPrepared) {
                getView().setProgress(true);
            } else {
                Toast.makeText(getView().getActivityContext(),
                        getView().getActivityContext().getResources()
                        .getString(R.string.failed_to_play_stream), Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    @Inject
    DetailsPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                     CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    protected void onViewPrepared() {
        if (station == null) return;

        if (station.getUrlToImage() != null) {
            getView().setImage(station.getUrlToImage());
        }

        if (station.getName() != null)
            getView().setName(station.getName());

        if (station.getStreamListeners() != null)
            getView().setListeners(station.getStreamListeners());

        if (station.getCategories() != null) {
            getView().setCategories(station.getCategories());
        }

        IntentFilter intentFilter = new IntentFilter(PlayerService.PLAYER_READY);
        getView().getActivityContext().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public void togglePlaying() {
        if (playing) {
            PlayerService.startActionStop(getView().getActivityContext());
        } else {
            PlayerService.startActionPlay(getView().getActivityContext(), station.getUrlToStream());
        }

        getView().setProgress(playing);
        playing = !playing;
        getView().setProgressBarVisibility(playing);
    }

    @Override
    public void onDestroy() {
        getView().getActivityContext().unregisterReceiver(receiver);
    }
}

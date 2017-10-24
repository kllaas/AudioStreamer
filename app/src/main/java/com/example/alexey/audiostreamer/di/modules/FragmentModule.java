package com.example.alexey.audiostreamer.di.modules;

import android.media.MediaPlayer;

import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.details.DetailsContract;
import com.example.alexey.audiostreamer.ui.details.DetailsPresenter;
import com.example.alexey.audiostreamer.ui.list.ListContract;
import com.example.alexey.audiostreamer.ui.list.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class FragmentModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    ListContract.Presenter<ListContract.View> provideListPresenter(
            ListPresenter<ListContract.View> presenter) {
        return presenter;
    }

    @Provides
    List<Station> provideNotes() {
        return new ArrayList<>();
    }

    @Provides
    DetailsContract.Presenter<DetailsContract.View> provideDetailsPresenter(
            DetailsPresenter<DetailsContract.View> presenter) {
        return presenter;
    }

    @Provides
    MediaPlayer provideMediaPlayer() {
        return new MediaPlayer();
    }
}

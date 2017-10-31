package com.example.alexey.audiostreamer.di.modules;

import android.media.MediaPlayer;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.details.DetailsMVPContract;
import com.example.alexey.audiostreamer.ui.details.DetailsPresenter;
import com.example.alexey.audiostreamer.ui.list.ListMVPContract;
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
    ListMVPContract.Presenter<ListMVPContract.View> provideListPresenter(
            ListPresenter<ListMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    List<Station> provideNotes() {
        return new ArrayList<>();
    }

    @Provides
    DetailsMVPContract.Presenter<DetailsMVPContract.View> provideDetailsPresenter(
            DetailsPresenter<DetailsMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    MediaPlayer provideMediaPlayer() {
        return new MediaPlayer();
    }
}

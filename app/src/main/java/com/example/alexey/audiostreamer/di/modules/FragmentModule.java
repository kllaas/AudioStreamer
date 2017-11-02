package com.example.alexey.audiostreamer.di.modules;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.details_item.DetailsMVPContract;
import com.example.alexey.audiostreamer.ui.details_item.DetailsPresenter;
import com.example.alexey.audiostreamer.ui.details_pager.PagerMVPContract;
import com.example.alexey.audiostreamer.ui.details_pager.PagerPresenter;
import com.example.alexey.audiostreamer.ui.list.ListMVPContract;
import com.example.alexey.audiostreamer.ui.list.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

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

    @Provides
    PagerMVPContract.Presenter<PagerMVPContract.View> providePresenterPresenter(
            PagerPresenter<PagerMVPContract.View> presenter) {
        return presenter;
    }

    @Provides
    FragmentManager provideFragmentManager() {
        return fragment.getChildFragmentManager();
    }
}

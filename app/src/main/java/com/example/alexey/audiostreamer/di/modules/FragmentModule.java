package com.example.alexey.audiostreamer.di.modules;

import com.example.alexey.audiostreamer.data.entity.Station;
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
    List<Station> provideNotes() {
        List<Station> news = new ArrayList<>();
        news.add(new Station());
        news.get(0).setName("title");
        return news;
    }

    @Provides
    ListContract.Presenter<ListContract.View> provideListPresenter(
            ListPresenter<ListContract.View> presenter) {
        return presenter;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}

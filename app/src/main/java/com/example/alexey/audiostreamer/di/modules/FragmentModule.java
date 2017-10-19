package com.example.alexey.audiostreamer.di.modules;

import com.example.alexey.audiostreamer.data.Track;
import com.example.alexey.audiostreamer.di.FragmentScope;
import com.example.alexey.audiostreamer.ui.list.ListContract;
import com.example.alexey.audiostreamer.ui.list.ListPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexey
 */

@Module
@FragmentScope
public class FragmentModule {

    @Provides
    List<Track> provideNotes() {
        List<Track> news = new ArrayList<>();
        news.add(new Track());
        news.get(0).setTitle("title");
        return news;
    }

    @Provides
    ListContract.Presenter<ListContract.View> provideListPresenter(
            ListPresenter<ListContract.View> presenter) {
        return presenter;
    }

}

package com.example.alexey.audiostreamer.ui.list;


import com.example.alexey.audiostreamer.data.Track;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;

import javax.inject.Inject;

/**
 * Created by alexey
 */

public class ListPresenter<V extends ListContract.View>
        extends BasePresenterImpl<V> implements ListContract.Presenter<V> {

    @Inject
    ListPresenter() {

    }

    @Override
    public void onItemClick(Track track) {
//        App.appComponent.getNavigationManager().openDetailsFragment(track);
    }

    @Override
    protected void setUpView() {

    }
}

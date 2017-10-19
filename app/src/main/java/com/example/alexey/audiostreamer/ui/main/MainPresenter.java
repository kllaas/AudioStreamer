package com.example.alexey.audiostreamer.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;

import javax.inject.Inject;

/**
 * Created by alexey
 */

public class MainPresenter<V extends MainMvpContract.View> extends BasePresenterImpl<V>
        implements MainMvpContract.Presenter<V>, NavigationManager.NavigationListener {

    @Inject
    MainPresenter() {
    }

    @Override
    public void navigateBack(AppCompatActivity notesActivity) {
        App.appComponent.getNavigationManager().navigateBack(notesActivity);
    }

    @Override
    protected void setUpView() {
        App.appComponent.getNavigationManager().init(view.getFragmentManger(), this);
        App.appComponent.getNavigationManager().openListFragment();
    }

    @Override
    public void onBackStackChanged() {}
}

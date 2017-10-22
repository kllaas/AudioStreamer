package com.example.alexey.audiostreamer.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.base.BasePresenterImpl;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class MainPresenter<V extends MainMvpContract.View> extends BasePresenterImpl<V>
        implements MainMvpContract.Presenter<V>, NavigationManager.NavigationListener {

    @Inject
    MainPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    public void navigateBack(AppCompatActivity notesActivity) {
        getNavigationManager()
                .navigateBack(notesActivity);
    }

    @Override
    protected void onViewPrepared() {
        getNavigationManager()
                .init(getView().getFragmentManger(), this);

        getNavigationManager()
                .openListFragment();
    }

    @Override
    public void onBackStackChanged() {
    }
}

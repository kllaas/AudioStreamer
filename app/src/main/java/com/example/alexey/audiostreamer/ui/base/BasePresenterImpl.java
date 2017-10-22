package com.example.alexey.audiostreamer.ui.base;

import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private Repository dataSource;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private NavigationManager navigationManager;

    private V view;

    public BasePresenterImpl(Repository dataSource, SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        this.dataSource = dataSource;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.navigationManager = navigationManager;
    }

    @Override
    public void takeView(V view) {
        this.view = view;
        onViewPrepared();
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    public V getView() {
        return view;
    }

    public Repository getRepository() {
        return dataSource;
    }

    protected SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    protected NavigationManager getNavigationManager() {
        return navigationManager;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    protected abstract void onViewPrepared();
}

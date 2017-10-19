package com.example.alexey.audiostreamer.ui.base;

/**
 * Created by alexey
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V view;

    @Override
    public void takeView(V view) {
        this.view = view;
        setUpView();
    }

    @Override
    public void dropView() {
        this.view = null;
    }

    public V getView() {
        return view;
    }

    protected abstract void setUpView();

}

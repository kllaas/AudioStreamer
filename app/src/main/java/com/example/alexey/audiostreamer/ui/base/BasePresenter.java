package com.example.alexey.audiostreamer.ui.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}

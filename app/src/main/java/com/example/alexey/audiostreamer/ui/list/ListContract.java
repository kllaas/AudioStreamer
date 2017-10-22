package com.example.alexey.audiostreamer.ui.list;

import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

import java.util.List;

public interface ListContract {

    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void updateList(List<Station> stations);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void onItemClick(Station news);
    }

}

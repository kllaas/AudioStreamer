package com.example.alexey.audiostreamer.ui.list;

import com.example.alexey.audiostreamer.data.Track;
import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

public interface ListContract {

    interface View extends BaseView {

    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void onItemClick(Track news);
    }

}

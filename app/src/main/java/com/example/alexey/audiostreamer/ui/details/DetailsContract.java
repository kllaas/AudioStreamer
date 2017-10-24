package com.example.alexey.audiostreamer.ui.details;

import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

public interface DetailsContract {

    interface View extends BaseView {

        void setName(String text);

        void setImage(String imageUrl);

        void unBlockPlayButton();
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setStation(Station station);

        void togglePlaying();
    }

}

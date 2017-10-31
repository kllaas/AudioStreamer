package com.example.alexey.audiostreamer.ui.details;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

public interface DetailsMVPContract {

    interface View extends BaseView {

        void togglePlayButton(boolean enabled);

        void setName(String text);

        void setImage(String imageUrl);

        void toggleProgressBar(boolean visibility);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setStation(Station station);

        void togglePlaying();

        void destroyMediaPlayer();
    }

}

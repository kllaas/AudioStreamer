package com.example.alexey.audiostreamer.ui.details;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

public interface DetailsMVPContract {

    interface View extends BaseView {

        void setName(String text);

        void setImage(String imageUrl);

        void setCategories(String text);

        void setListeners(int count);

        void setProgress(boolean visibility);

        void setProgressBarVisibility(boolean visibility);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setStation(Station station);

        void togglePlaying();

        void onDestroy();
    }

}

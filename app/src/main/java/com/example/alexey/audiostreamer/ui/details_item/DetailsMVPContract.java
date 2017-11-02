package com.example.alexey.audiostreamer.ui.details_item;

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

        void setStationId(Long id);

        void togglePlaying();

        void onDestroy();
    }

}

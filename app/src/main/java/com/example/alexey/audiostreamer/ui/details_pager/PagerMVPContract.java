package com.example.alexey.audiostreamer.ui.details_pager;

import com.example.alexey.audiostreamer.ui.base.BasePresenter;
import com.example.alexey.audiostreamer.ui.base.BaseView;

import java.util.List;

public interface PagerMVPContract {

    interface View extends BaseView {

        void initPager(PagerAdapter adapter, int currPosition);
    }

    interface Presenter<V extends View> extends BasePresenter<V> {

        void setCurrStation(Long currStation);

        void setIds(List<Long> ids);
    }

}

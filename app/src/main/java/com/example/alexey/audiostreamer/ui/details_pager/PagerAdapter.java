package com.example.alexey.audiostreamer.ui.details_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alexey.audiostreamer.ui.details_item.DetailsFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by alexey
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Long> stationIds;

    @Inject
    PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return stationIds.size();
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(stationIds.get(position));
    }

    public void setStationIds(List<Long> stationIds) {
        this.stationIds = stationIds;
    }
}
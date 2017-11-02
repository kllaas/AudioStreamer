package com.example.alexey.audiostreamer.ui.details_pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.ui.base.BaseFragment;
import com.example.alexey.audiostreamer.ui.details_pager.PagerMVPContract.Presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerFragment extends BaseFragment implements PagerMVPContract.View {

    public static final String STATION_BUNDLE = "station";
    public static final String STATION_IDS_BUNDLE = "station_ids";

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    Presenter<PagerMVPContract.View> presenter;

    public static PagerFragment newInstance(Long id, ArrayList<Long> ids) {
        Bundle args = new Bundle();

        args.putSerializable(STATION_BUNDLE, id);
        args.putSerializable(STATION_IDS_BUNDLE, ids);

        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_pager, container, false);

        FragmentComponent component = getComponent();
        component.inject(this);

        setUnBinder(ButterKnife.bind(this, view));

        Long currStation = (Long) getArguments().getSerializable(STATION_BUNDLE);
        List<Long> ids = (List<Long>) getArguments().getSerializable(STATION_IDS_BUNDLE);

        presenter.setCurrStation(currStation);
        presenter.setIds(ids);
        presenter.takeView(this);

        return view;
    }


    @Override
    protected void setUpViews() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_black_24dp);
        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
    }

    @Override
    public void initPager(PagerAdapter adapter, int currPositon) {
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currPositon);
    }
}

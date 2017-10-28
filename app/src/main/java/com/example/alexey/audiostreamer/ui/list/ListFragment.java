package com.example.alexey.audiostreamer.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by alexey
 */

public class ListFragment extends BaseFragment implements ListContract.View {

    OnClick onClick = new OnClick() {
        @Override
        public void onClick(Station station) {
            presenter.onItemClick(station);
        }
    };

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    ListContract.Presenter<ListContract.View> presenter;

    @Inject
    ListAdapter listAdapter;

    RecyclerView.LayoutManager layoutManager;

    public static ListFragment newInstance() {
        Bundle args = new Bundle();

        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_list, container, false);

        FragmentComponent component = getComponent();
        component.inject(this);

        setUnBinder(ButterKnife.bind(this, view));
        setUpViews();

        presenter.takeView(this);

        return view;
    }

    protected void setUpViews() {
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

        listAdapter.setOnItemClick(onClick);
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected boolean showsBackButton() {
        return false;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateList(List<Station> stations) {
        listAdapter.setItems(stations);
    }
}

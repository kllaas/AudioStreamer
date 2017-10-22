package com.example.alexey.audiostreamer.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.di.components.DaggerFragmentComponent;
import com.example.alexey.audiostreamer.di.components.FragmentComponent;
import com.example.alexey.audiostreamer.di.modules.FragmentModule;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView {

    private FragmentComponent component;
    private Unbinder unbinder;

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initActionBar(showsBackButton(), getToolbarTitle());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        component = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .applicationComponent(App.appComponent)
                .build();
    }

    protected void initActionBar(boolean showBackButton, String title) {
        if (getActivity() == null) return;

        ActionBar actionBar = ((BaseActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(showBackButton);
            actionBar.setDisplayShowHomeEnabled(showBackButton);
        }
    }

    protected abstract String getToolbarTitle();

    protected abstract boolean showsBackButton();

    protected FragmentComponent getComponent() {
        return component;
    }

    protected void setUnBinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

}

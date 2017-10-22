package com.example.alexey.audiostreamer.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.ui.base.BaseActivity;
import com.example.alexey.audiostreamer.ui.main.MainMvpContract.Presenter;
import com.example.alexey.audiostreamer.ui.main.MainMvpContract.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View {

    @Inject
    Presenter<View> presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        App.appComponent.inject(this);

        presenter.takeView(this);

        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        presenter.navigateBack(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            presenter.navigateBack(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public FragmentManager getFragmentManger() {
        return getSupportFragmentManager();
    }

}

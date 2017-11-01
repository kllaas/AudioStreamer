package com.example.alexey.audiostreamer.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.R;
import com.example.alexey.audiostreamer.ui.base.BaseActivity;
import com.example.alexey.audiostreamer.ui.main.MainMvpContract.Presenter;
import com.example.alexey.audiostreamer.ui.main.MainMvpContract.View;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View {

    @Inject
    Presenter<View> presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        App.appComponent.inject(this);

        presenter.takeView(this);
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

    @Override
    public Context getActivityContext() {
        return getActivityContext();
    }
}

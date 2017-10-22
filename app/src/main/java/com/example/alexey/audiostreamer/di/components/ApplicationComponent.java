package com.example.alexey.audiostreamer.di.components;

import android.app.Application;
import android.content.Context;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.di.modules.ActivityModule;
import com.example.alexey.audiostreamer.di.modules.AppModule;
import com.example.alexey.audiostreamer.di.modules.NavigationModule;
import com.example.alexey.audiostreamer.ui.NavigationManager;
import com.example.alexey.audiostreamer.ui.main.MainActivity;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NavigationModule.class, ActivityModule.class})
public interface ApplicationComponent {

    void inject(App app);

    void inject(MainActivity activity);

    Context getAppContext(Context appContext);

    Application application();

    Repository getDataSource();

    SchedulerProvider getSchedulerProvider();

    NavigationManager getNavigationManager();
}

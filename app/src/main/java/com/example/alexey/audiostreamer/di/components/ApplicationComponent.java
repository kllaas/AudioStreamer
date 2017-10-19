package com.example.alexey.audiostreamer.di.components;

import android.app.Application;
import android.content.Context;

import com.example.alexey.audiostreamer.App;
import com.example.alexey.audiostreamer.data.DataSource;
import com.example.alexey.audiostreamer.di.modules.AppModule;
import com.example.alexey.audiostreamer.di.modules.NavigationModule;
import com.example.alexey.audiostreamer.ui.main.MainActivity;
import com.example.alexey.audiostreamer.ui.NavigationManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NavigationModule.class})
public interface ApplicationComponent {

    void inject(App app);

    void inject(MainActivity activity);

    @Singleton
    NavigationManager getNavigationManager();

    @Singleton
    Context getAppContext(Context appContext);

    @Singleton
    Application application();

    @Singleton
    DataSource getDataSource();
}

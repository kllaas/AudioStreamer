package com.example.alexey.audiostreamer.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.alexey.audiostreamer.BuildConfig;
import com.example.alexey.audiostreamer.data.DataSource;
import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.local.LocalRepository;
import com.example.alexey.audiostreamer.data.remote.RemoteRepository;
import com.example.alexey.audiostreamer.ui.main.MainMvpContract;
import com.example.alexey.audiostreamer.ui.main.MainPresenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Initialize singletons.
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;

        // Realm database init
        Realm.init(application.getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(BuildConfig.VERSION_SCHEMA)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    DataSource provideDataSource(LocalRepository localRepository, RemoteRepository remoteRepository) {
        return new Repository(localRepository, remoteRepository);
    }

    @Provides
    @Singleton
    LocalRepository provideLocalRepository(Realm realm) {
        return new LocalRepository(realm);
    }

    @Provides
    @Singleton
    RemoteRepository provideRemoteRepository() {
        return new RemoteRepository();
    }

    @Provides
    MainMvpContract.Presenter<MainMvpContract.View> provideMainPresenter(
            MainPresenter<MainMvpContract.View> presenter) {
        return presenter;
    }

}

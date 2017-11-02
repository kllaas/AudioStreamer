package com.example.alexey.audiostreamer.di.modules;

import android.app.Application;
import android.content.Context;

import com.example.alexey.audiostreamer.BuildConfig;
import com.example.alexey.audiostreamer.data.Repository;
import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;
import com.example.alexey.audiostreamer.data.local.LocalRepository;
import com.example.alexey.audiostreamer.data.mapping.StationsDeserializer;
import com.example.alexey.audiostreamer.data.remote.RemoteRepository;
import com.example.alexey.audiostreamer.utils.rx.AppSchedulerProvider;
import com.example.alexey.audiostreamer.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

@Module
@Singleton
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
    Repository provideRepository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        return new Repository(localRepository, remoteRepository);
    }

    @Provides
    @Singleton
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(RemoteStation.class, new StationsDeserializer())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder().build();
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}

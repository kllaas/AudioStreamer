package com.example.alexey.audiostreamer.di.modules;

import com.example.alexey.audiostreamer.ui.NavigationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexey
 */

@Module
@Singleton
public class NavigationModule {

    @Provides
    @Singleton
    NavigationManager provideNavigationManager() {
        return new NavigationManager();
    }

}

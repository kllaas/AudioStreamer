package com.example.alexey.audiostreamer.di.components;

import com.example.alexey.audiostreamer.di.FragmentScope;
import com.example.alexey.audiostreamer.di.modules.FragmentModule;
import com.example.alexey.audiostreamer.ui.list.ListFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ListFragment fragment);

}

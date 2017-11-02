package com.example.alexey.audiostreamer.di.components;

import com.example.alexey.audiostreamer.di.PerFragment;
import com.example.alexey.audiostreamer.di.modules.FragmentModule;
import com.example.alexey.audiostreamer.ui.details_item.DetailsFragment;
import com.example.alexey.audiostreamer.ui.details_pager.PagerFragment;
import com.example.alexey.audiostreamer.ui.list.ListFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(ListFragment fragment);

    void inject(PagerFragment fragment);

    void inject(DetailsFragment fragment);

}

package com.example.alexey.audiostreamer.data.remote;

import com.example.alexey.audiostreamer.BuildConfig;
import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;
import com.example.alexey.audiostreamer.data.remote.service.DirbleService;
import com.example.alexey.audiostreamer.data.remote.service.ServiceGenerator;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RemoteRepository implements RemoteSource {

    private ServiceGenerator serviceGenerator;

    private DirbleService dirbleService;

    @Inject
    RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;

        this.dirbleService = serviceGenerator.createService(DirbleService.class, BuildConfig.BASE_URL);
    }

    @Override
    public Observable<List<RemoteStation>> fetchStations() {
        return dirbleService.getPopularStations();
    }
}

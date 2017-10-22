package com.example.alexey.audiostreamer.data;

import com.example.alexey.audiostreamer.data.entity.Station;
import com.example.alexey.audiostreamer.data.local.LocalRepository;
import com.example.alexey.audiostreamer.data.remote.RemoteRepository;

import java.util.List;

import io.reactivex.Observable;

public class Repository {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public Repository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public Observable<List<Station>> getStations() {
        return remoteRepository.fetchStations()
                .doOnNext(stations -> localRepository.saveStations(stations));
    }

}

package com.example.alexey.audiostreamer.data;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.data.local.LocalRepository;
import com.example.alexey.audiostreamer.data.mapping.RemoteToPOJO;
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
                .map(RemoteToPOJO::transform);
    }

    public Station getStationById(Long id) {
        return localRepository.getStationById(id);
    }

    public void saveStations(List<Station> stations) {
        localRepository.saveStations(stations);
    }
}

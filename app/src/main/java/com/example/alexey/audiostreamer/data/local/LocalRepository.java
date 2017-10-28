package com.example.alexey.audiostreamer.data.local;


import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

public class LocalRepository implements LocalSource {

    private Realm realm;

    @Inject
    LocalRepository(Realm realm) {
        this.realm = realm;
    }

    public Observable<List<RemoteStation>> fetchStations() {
        return null;
    }

    @Override
    public void saveStations(List<RemoteStation> stations) {
    }
}

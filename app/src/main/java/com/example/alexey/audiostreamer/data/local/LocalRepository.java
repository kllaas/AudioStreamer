package com.example.alexey.audiostreamer.data.local;

import com.example.alexey.audiostreamer.data.DataSource;

import io.realm.Realm;

public class LocalRepository implements DataSource {

    private Realm realm;

    public LocalRepository(Realm realm) {
        this.realm = realm;
    }

}

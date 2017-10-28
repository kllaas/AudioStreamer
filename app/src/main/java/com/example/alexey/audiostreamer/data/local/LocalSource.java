package com.example.alexey.audiostreamer.data.local;

import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;

import java.util.List;

public interface LocalSource {

    void saveStations(List<RemoteStation> stations);
}

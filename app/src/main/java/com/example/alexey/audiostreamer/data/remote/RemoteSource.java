package com.example.alexey.audiostreamer.data.remote;

import com.example.alexey.audiostreamer.data.entity.Station;

import java.util.List;

import io.reactivex.Observable;

public interface RemoteSource {

    Observable<List<Station>> fetchStations();

}

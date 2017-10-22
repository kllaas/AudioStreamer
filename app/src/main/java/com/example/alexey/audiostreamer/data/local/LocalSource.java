package com.example.alexey.audiostreamer.data.local;

import com.example.alexey.audiostreamer.data.entity.Station;

import java.util.List;

public interface LocalSource {

    void saveStations(List<Station> stations);
}

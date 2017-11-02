package com.example.alexey.audiostreamer.data.local;

import com.example.alexey.audiostreamer.data.entity.local.Station;

import java.util.List;

public interface LocalSource {

    void saveStations(List<Station> stations);

    Station getStationById(Long id);
}

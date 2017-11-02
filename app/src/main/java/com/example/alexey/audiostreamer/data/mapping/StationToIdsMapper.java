package com.example.alexey.audiostreamer.data.mapping;

import com.example.alexey.audiostreamer.data.entity.local.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey
 */

public class StationToIdsMapper {

    public static ArrayList<Long> convert(List<Station> stations){
        ArrayList<Long> ids = new ArrayList<>();

        for (Station station : stations) {
            ids.add(station.getId());
        }

        return ids;
    }

}

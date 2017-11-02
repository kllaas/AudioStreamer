package com.example.alexey.audiostreamer.data.mapping;

import com.example.alexey.audiostreamer.data.entity.local.Station;
import com.example.alexey.audiostreamer.data.entity.remote.Category;
import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;
import com.example.alexey.audiostreamer.data.entity.remote.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey
 */

public class RemoteToPOJO {

    public static List<Station> transform(List<RemoteStation> remoteStations) {
        List<Station> stations = new ArrayList<>();

        for (RemoteStation remoteStation : remoteStations) {
            stations.add(transformStation(remoteStation));
        }

        return stations;
    }

    private static Station transformStation(RemoteStation remoteStation) {
        Station station = new Station();

        station.setId(remoteStation.getId());
        station.setName(remoteStation.getName());
        station.setUrlToImage(remoteStation.getImage().getUrlToImage());
        station.setCategories(getCategoriesString(remoteStation.getCategories()));

        Stream lastStream = getLastStream(remoteStation);

        station.setUrlToStream(lastStream.getUrl());
        station.setStreamListeners(lastStream.getListeners());

        return station;
    }

    private static Stream getLastStream(RemoteStation remoteStation) {
        int size = remoteStation.getStreams().size();
        if (size == 0) return new Stream();

        return remoteStation.getStreams().get(size - 1);
    }

    private static String getCategoriesString(List<Category> categories) {
        if (categories == null) return "";

        StringBuilder res = new StringBuilder();

        for (Category category : categories) {
            res.append(category.getTitle());
            res.append(", ");
        }

        res.deleteCharAt(res.length() - 2);
        res.setCharAt(res.length() - 1, '.');

        return res.toString();
    }


}

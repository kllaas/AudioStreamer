package com.example.alexey.audiostreamer.data.remote.service;

import com.example.alexey.audiostreamer.data.entity.remote.RemoteStation;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by alexey
 */

public interface DirbleService {

    @GET("stations/popular")
    Observable<List<RemoteStation>> getPopularStations();

}

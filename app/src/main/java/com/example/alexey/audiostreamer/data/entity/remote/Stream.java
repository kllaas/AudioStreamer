package com.example.alexey.audiostreamer.data.entity.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexey
 */

public class Stream {

    @SerializedName("stream")
    private String url;

    private int listeners;

    public Stream() {
    }

    public Stream(String url, int listeners) {
        this.url = url;
        this.listeners = listeners;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getListeners() {
        return listeners;
    }

    public void setListeners(int listeners) {
        this.listeners = listeners;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "url='" + url + '\'' +
                ", listeners=" + listeners +
                '}';
    }
}

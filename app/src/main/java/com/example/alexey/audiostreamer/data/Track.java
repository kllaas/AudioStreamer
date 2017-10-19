package com.example.alexey.audiostreamer.data;

import com.example.alexey.audiostreamer.utils.Constants;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Track extends RealmObject {

    @SerializedName(Constants.Realm.TITLE)
    private String title;

    @SerializedName(Constants.Realm.ID)
    private int id;

    @SerializedName(Constants.Realm.STREAM_URL)
    private String streamURL;

    @SerializedName(Constants.Realm.ARTWORK_URL)
    private String artworkURL;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreamURL() {
        return streamURL;
    }

    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }

    public String getArtworkURL() {
        return artworkURL;
    }

    public void setArtworkURL(String artworkURL) {
        this.artworkURL = artworkURL;
    }
}

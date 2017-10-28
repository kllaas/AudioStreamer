package com.example.alexey.audiostreamer.data.entity.local;

import android.os.Parcel;
import android.os.Parcelable;

public class Station implements Parcelable {

    private Integer id;

    private String name;

    private String urlToImage;

    private String categories;

    private String urlToStream;

    private Integer streamListeners;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getUrlToStream() {
        return urlToStream;
    }

    public void setUrlToStream(String urlToStream) {
        this.urlToStream = urlToStream;
    }

    public Integer getStreamListeners() {
        return streamListeners;
    }

    public void setStreamListeners(Integer streamListeners) {
        this.streamListeners = streamListeners;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.urlToImage);
        dest.writeString(this.categories);
        dest.writeString(this.urlToStream);
        dest.writeValue(this.streamListeners);
    }

    public Station() {
    }

    protected Station(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.urlToImage = in.readString();
        this.categories = in.readString();
        this.urlToStream = in.readString();
        this.streamListeners = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel source) {
            return new Station(source);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}

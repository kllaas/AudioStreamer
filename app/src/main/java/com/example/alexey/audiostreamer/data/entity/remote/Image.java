package com.example.alexey.audiostreamer.data.entity.remote;

public class Image {

    private String url;

    public String getUrlToImage() {
        return url;
    }

    public void setUrlToImage(String urlToImage) {
        this.url = urlToImage;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "url='" + url + '\'' +
                '}';
    }
}

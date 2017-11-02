package com.example.alexey.audiostreamer.data.entity.remote;

import java.util.List;

public class RemoteStation {

    private Long id;

    private String name;

    private Image image;

    private List<Category> categories;

    private List<Stream> streams;

    public RemoteStation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + image + '\'' +
                ", categories=" + categories +
                ", streams=" + streams +
                '}';
    }
}

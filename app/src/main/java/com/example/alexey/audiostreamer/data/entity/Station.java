package com.example.alexey.audiostreamer.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Station extends RealmObject {

    private Integer id;

    private String name;

    private Image image;

    private RealmList<Category> categories;

    private RealmList<Stream> streams;

    public Station() {
    }

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public RealmList<Stream> getStreams() {
        return streams;
    }

    public void setStreams(RealmList<Stream> streams) {
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

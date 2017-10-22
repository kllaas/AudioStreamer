package com.example.alexey.audiostreamer.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Station extends RealmObject {

    private Integer id;

    private String name;

    private Image image;

    private RealmList<Category> categories;

    private RealmList<Stream> streams;

    @SerializedName("total_listeners")
    private Integer listeners;

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

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
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

    public String getCategoriesString() {
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

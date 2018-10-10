package com.colvengames.wallpapertumblr.models;

import android.net.Uri;

public class TumblrItem {

    private String url_image;

    public Uri getUrl_image() {
        return Uri.parse(url_image);
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    private String autor;

}

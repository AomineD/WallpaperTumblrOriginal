package com.colvengames.wallpapertumblr.config;

import com.orm.SugarRecord;

public class FavoritesItem extends SugarRecord {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}

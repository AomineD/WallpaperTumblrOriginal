package com.colvengames.wallpapertumblr.api;

import com.colvengames.wallpapertumblr.models.TumblrAutor;

import java.util.ArrayList;

public interface ResponseAutor {
    void OnDataLoaded(TumblrAutor autor);

    void OnDataFailedToLoad(String error);
}

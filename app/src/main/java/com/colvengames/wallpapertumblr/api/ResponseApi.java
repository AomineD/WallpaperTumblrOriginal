package com.colvengames.wallpapertumblr.api;

import com.colvengames.wallpapertumblr.models.TumblrItem;

import java.util.ArrayList;

public interface ResponseApi {
    void Correct(ArrayList<TumblrItem> response);

    void Incorrect(String errno);
}

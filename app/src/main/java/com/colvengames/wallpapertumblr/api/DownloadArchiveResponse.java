package com.colvengames.wallpapertumblr.api;

import android.net.Uri;

public interface DownloadArchiveResponse {

    void Donwloaded(Uri path);

    void Failed(String errno);
}

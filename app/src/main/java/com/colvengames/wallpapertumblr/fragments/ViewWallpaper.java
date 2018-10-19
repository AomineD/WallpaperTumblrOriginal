package com.colvengames.wallpapertumblr.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.activities.WallpaperActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewWallpaper extends Fragment {

private Uri urr;
public ImageView image;

    public Uri getUrr() {
        return urr;
    }

    public ViewWallpaper() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bondol = getArguments();

        if(bondol != null){
            urr = Uri.parse(bondol.getString(WallpaperActivity.key_wall));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_view_wallpaper, container, false);

        image = view.findViewById(R.id.wallpaper);

        Picasso.get().load(urr).fit().into(image);

        //Log.e("MAIN", "FRAGMENTO: QUESESTE = "+urr.toString());

        return view;
    }

}

package com.colvengames.wallpapertumblr.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colvengames.wallpapertumblr.Adapters.WallpaperAdapter;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.config.FavoritesItem;
import com.colvengames.wallpapertumblr.models.TumblrItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    ArrayList<FavoritesItem> favoritesItemArrayList = new ArrayList<>();

    ArrayList<TumblrItem> itemFav = new ArrayList<>();

    private RecyclerView list_recycler;


    public FavoritesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

try {
    favoritesItemArrayList = (ArrayList<FavoritesItem>) FavoritesItem.listAll(FavoritesItem.class);


    Log.e("MAIN", "onCreate: SIZE = " + favoritesItemArrayList.size());

    for (int i = 0; i < favoritesItemArrayList.size(); i++) {
        TumblrItem item = new TumblrItem();
        item.setUrl_image(favoritesItemArrayList.get(i).getUrl());
        item.setName(favoritesItemArrayList.get(i).getName());

        itemFav.add(item);


    }
}catch (SQLiteException e){
    Log.e("MAIN", "onCreate: "+e.getMessage());
}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);


        list_recycler = view.findViewById(R.id.rec);

        WallpaperAdapter adapter = new WallpaperAdapter(getContext(), itemFav, getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        list_recycler.setLayoutManager(gridLayoutManager);
        list_recycler.setAdapter(adapter);

        return view;
    }


}

package com.colvengames.wallpapertumblr.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colvengames.wallpapertumblr.Adapters.WallpaperAdapter;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.api.ResponseApi;
import com.colvengames.wallpapertumblr.api.TumblrApi;
import com.colvengames.wallpapertumblr.config.Constant;
import com.colvengames.wallpapertumblr.models.TumblrItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {


    private ArrayList<TumblrItem> tumblrItemArrayList = new ArrayList<>();
    private RecyclerView List_wallpaper;


    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ResponseApi responseApi = new ResponseApi() {
            @Override
            public void Correct(ArrayList<TumblrItem> response) {
               tumblrItemArrayList.addAll(response);
               List_wallpaper.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void Incorrect(String errno) {

            }
        };


        String main_url = Constant.base_url+Constant.usuariosTumblr[1]+Constant.base_url2+Constant.limite_por_pagina+Constant.base_url3;

        TumblrApi api = new TumblrApi(main_url, getContext(), responseApi);

        api.RunApi();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        List_wallpaper = view.findViewById(R.id.rec);

        WallpaperAdapter adapter = new WallpaperAdapter(getContext(), tumblrItemArrayList, getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

List_wallpaper.setLayoutManager(gridLayoutManager);
List_wallpaper.setAdapter(adapter);



        return view;
    }

}

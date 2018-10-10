package com.colvengames.wallpapertumblr.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.colvengames.wallpapertumblr.Adapters.AutorAdapter;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.api.ResponseAutor;
import com.colvengames.wallpapertumblr.api.TumblrApi;
import com.colvengames.wallpapertumblr.config.Constant;
import com.colvengames.wallpapertumblr.models.TumblrAutor;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements ResponseAutor {

    private ArrayList<TumblrAutor> List_autor = new ArrayList<>();
private AutorAdapter autorAdapter;
private FrameLayout frameLayout;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i = 0; i < Constant.usuariosTumblr.length; i++){

            String main_url = Constant.base_url+Constant.usuariosTumblr[i]+Constant.base_url2+Constant.limite_por_pagina+Constant.base_url3;
            TumblrApi api = new TumblrApi(main_url, getContext(), this);

            api.RunApi();


        }


        autorAdapter = new AutorAdapter(List_autor, getContext(), getFragmentManager());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_category, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.rec);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
frameLayout = view.findViewById(R.id.fragm);
autorAdapter.SetFrameLayout(frameLayout);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(autorAdapter);

        return view;
    }

    @Override
    public void OnDataLoaded(TumblrAutor autor) {
        List_autor.add(autor);
        autorAdapter.notifyDataSetChanged();

    }

    @Override
    public void OnDataFailedToLoad(String error) {

    }
}

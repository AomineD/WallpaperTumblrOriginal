package com.colvengames.wallpapertumblr.config;

import android.graphics.drawable.Drawable;

import com.colvengames.wallpapertumblr.R;

import java.util.ArrayList;

public class Constant {

    // =========================================================================================================================================== //
    // ============================================= Aqui van los nombre de usuario separados por coma =========================================== //
    // =========================================================================================================================================== //
    public static String[] usuariosTumblr = new String[]{
            "artbyandy",
            "pepesegarra57",
            "papersways"
    };


    public static String[] nombre_usuarios = new String[]{
            "Art Andy",
            "Pepe Segarra",
            "Papers Ways"
    };

    public static int[] Drawables_perfil = new int[]{
          R.drawable.backbtn_unpressed, R.drawable.ic_launcher_background, R.drawable.backbtn_pressed1
    };

    // =========================================================================================================================================== //
    // ============================================= Aqui van los nombre de usuario para creditos =========================================== //
    // =========================================================================================================================================== //




    // =========================================================================================================================================== //
       // ============================================= Esto no se toca (A menos que lo necesites) =========================================== //
    // =========================================================================================================================================== //
    public static final String base_url = "https://";
    public static final String base_url2 = ".tumblr.com/api/read/json?type=photo&num=";
    public static final String base_url3 = "&start=";

    public static final String key_bundle1 = "akkeda";
    public static final String key_name_autor ="kkautor";




public static final int limite_por_pagina = 25;

public static final int Pop_up_frecuency = 3;
public static final int NATIVE_FRECUENCY = 4;
public static final int INTERSTICIAL_FRECUENCY = 2;
public static final int TYPE_ADS = 0;


}

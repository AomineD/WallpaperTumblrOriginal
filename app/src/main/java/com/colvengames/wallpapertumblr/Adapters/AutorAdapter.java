package com.colvengames.wallpapertumblr.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import  android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.config.Constant;
import com.colvengames.wallpapertumblr.fragments.WallpapersAutorFragment;
import com.colvengames.wallpapertumblr.models.TumblrAutor;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.HolderAutor> {

    private ArrayList<TumblrAutor> autors = new ArrayList<>();
    private Context mContext;

    private FragmentManager fragmentManager;
    private FrameLayout frameLayout;


    public AutorAdapter(ArrayList<TumblrAutor> list_autors, Context orig_context, FragmentManager manager){
        this.autors = list_autors;
        this.mContext = orig_context;
        this.fragmentManager = manager;

    }

    public void SetFrameLayout(FrameLayout frameLayoutss){
        this.frameLayout = frameLayoutss;
    }

    @NonNull
    @Override
    public HolderAutor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.autor_item, parent, false);

        return new HolderAutor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderAutor holder, final int position) {
        holder.name.setText(Constant.nombre_usuarios[position]);
        holder.cantidad.setText(String.valueOf(autors.get(position).getCantidadWallpaper()));
       // Picasso.get().load(autors.get(position).getUrl_avatar()).transform(new RoundedCornersTransformation(25, 5)).fit().into(holder.photo);
holder.photo.setImageDrawable(mContext.getResources().getDrawable(Constant.Drawables_perfil[position]));

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();


                WallpapersAutorFragment fragment = new WallpapersAutorFragment();
                Bundle bondol = new Bundle();
                bondol.putString(Constant.key_bundle1, autors.get(position).getNameAutor());
               // Log.e("MAIN", "onClick: "+position);
                bondol.putString(Constant.key_name_autor, Constant.nombre_usuarios[position]);

fragment.setArguments(bondol);

transaction.setCustomAnimations(R.anim.zoomin, R.anim.zoomout);
                transaction.replace(R.id.fragm, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
               frameLayout.setVisibility(View.VISIBLE);

            }
        });

    }


    @Override
    public int getItemCount() {
        return autors.size();
    }

    class HolderAutor extends RecyclerView.ViewHolder{

        private TextView cantidad;
        private ImageView photo;
        private TextView name;

        public HolderAutor(View itemView) {
            super(itemView);
            cantidad = itemView.findViewById(R.id.cantity_wall);
            photo = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name_autor);
        }
    }
}

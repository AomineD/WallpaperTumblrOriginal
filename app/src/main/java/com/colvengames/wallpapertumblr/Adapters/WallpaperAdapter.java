package com.colvengames.wallpapertumblr.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.models.TumblrItem;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.HolderWallpaper> {

    private Context mContext;
    private ArrayList<TumblrItem> tumblrItems;

    public WallpaperAdapter(Context context, ArrayList<TumblrItem> tumblrItemsarray){
this.tumblrItems = tumblrItemsarray;
this.mContext = context;
    }

    @NonNull
    @Override
    public HolderWallpaper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.wallpaper_item, parent, false);

        return new HolderWallpaper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderWallpaper holder, int position) {
        Picasso.get().load(tumblrItems.get(position).getUrl_image()).transform(new RoundedCornersTransformation(25, 5)).fit().into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return tumblrItems.size();
    }

    class HolderWallpaper extends RecyclerView.ViewHolder{

        private ImageView photo;

        public HolderWallpaper(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}

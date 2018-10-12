package com.colvengames.wallpapertumblr.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.activities.WallpaperActivity;
import com.colvengames.wallpapertumblr.models.TumblrItem;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.HolderWallpaper> {

    private Context mContext;
    private ArrayList<TumblrItem> tumblrItems;
    private Activity activity;

    public WallpaperAdapter(Context context, ArrayList<TumblrItem> tumblrItemsarray, Activity mActivity){
this.tumblrItems = tumblrItemsarray;
this.mContext = context;
this.activity = mActivity;
    }

    @NonNull
    @Override
    public HolderWallpaper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.wallpaper_item, parent, false);

        return new HolderWallpaper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HolderWallpaper holder, final int position) {
        Picasso.get().load(tumblrItems.get(position).getUrl_image()).transform(new RoundedCornersTransformation(25, 5)).fit().into(holder.photo);
        final String urlOfPhoto = String.valueOf(tumblrItems.get(position).getUrl_image());
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WallpaperActivity.class);
                intent.putExtra(WallpaperActivity.key_wall, urlOfPhoto);
                intent.putExtra(WallpaperActivity.key_name, tumblrItems.get(position).getName());
                intent.putExtra(WallpaperActivity.key_id, position);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(holder.photo, "img");

                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(activity, pairs);

                    mContext.startActivity(intent, options.toBundle());
                }else{
                    mContext.startActivity(intent);
                }
            }
        });
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

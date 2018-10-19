package com.colvengames.wallpapertumblr.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.activities.WallpaperActivity;
import com.colvengames.wallpapertumblr.models.TumblrItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
import okhttp3.Request;
import okhttp3.Response;
import pl.droidsonroids.gif.GifImageView;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.HolderWallpaper>{

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

Picasso.get().load(tumblrItems.get(position).getUrl_image()).transform(new RoundedCornersTransformation(25, 5)).fit().into(holder.photo, new Callback() {
    @Override
    public void onSuccess() {
       // Log.e("MAIN", "onSuccess: SUCCESS CSM");
        holder.gifLoading.setVisibility(View.GONE);
        holder.photo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Exception e) {
        //Log.e("MAIN", "onError: "+e.getMessage());
        holder.gifLoading.setVisibility(View.VISIBLE);
        holder.photo.setVisibility(View.GONE);
    }
});




        final String urlOfPhoto = String.valueOf(tumblrItems.get(position).getUrl_image());
        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WallpaperActivity.class);
                intent.putExtra(WallpaperActivity.key_wall, urlOfPhoto);
                intent.putExtra(WallpaperActivity.key_name, tumblrItems.get(position).getName());
                intent.putExtra(WallpaperActivity.key_id, position);
                intent.putParcelableArrayListExtra(WallpaperActivity.key_list_tumblr, tumblrItems);
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
        private GifImageView gifLoading;

        public HolderWallpaper(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            gifLoading = itemView.findViewById(R.id.gifloading);
        }
    }
}

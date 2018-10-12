package com.colvengames.wallpapertumblr.activities;

import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.config.FavoritesItem;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

public class WallpaperActivity extends AppCompatActivity {

    // =========== Key to bundle =========== //
    public static final String key_name = "kksda";
    public static final String key_wall = "sjdas";
    public static final String key_id = "isdasd";

    // ===================================== //

    private String url;
    private String name_wall;
    private int idname;
    private long id;
    private boolean favorites;
    private List<FavoritesItem> favoritesItems;

    // ====================== VIEWS ======================= //

    private ImageView phototo;
    private Toolbar toolbarsito;
    private FloatingActionButton actionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        phototo = findViewById(R.id.wallpaper);
        toolbarsito = findViewById(R.id.tolbar);



        Bundle bondol = getIntent().getExtras();

        if(bondol != null){
            idname = bondol.getInt(key_id);
            url = bondol.getString(key_wall);
            name_wall = bondol.getString(key_name);
        }

        Picasso.get().load(Uri.parse(url)).fit().into(phototo);

SetupToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

MenuInflater menuInflater = getMenuInflater();
try {
    favoritesItems = FavoritesItem.listAll(FavoritesItem.class);

    for(int i=0; i < favoritesItems.size(); i++){
        if(favoritesItems.get(i).getUrl().equals(url)){
            favorites = true;
            id = favoritesItems.get(i).getId();
            break;
        }

    }

}catch (SQLiteException e){
    Log.e("MAIN", "Error SQLite: "+e.getMessage());
}
menuInflater.inflate(R.menu.wallpapermenu, menu);


return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.download:

                break;
            case R.id.favorite:
if(favorites){
FavoritesItem fav = FavoritesItem.findById(FavoritesItem.class, id);
fav.delete();
favorites = false;
item.setIcon(R.drawable.favorite_btn);
}else{
    item.setIcon(R.drawable.favorite_btn_on);
    favorites = true;
    FavoritesItem fav = new FavoritesItem();
    fav.setUrl(url);
    fav.setName(name_wall);
    fav.save();
}
                break;


        }


        return true;
    }

    private void SetupToolbar() {
        toolbarsito.setTitleTextColor(getResources().getColor(R.color.white));
setSupportActionBar(toolbarsito);

        Objects.requireNonNull(getSupportActionBar()).setTitle(name_wall);

    }
}

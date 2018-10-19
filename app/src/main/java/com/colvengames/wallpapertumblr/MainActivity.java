package com.colvengames.wallpapertumblr;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import com.colvengames.wallpapertumblr.config.PagerAdapter;
import com.orm.SugarContext;
import com.orm.util.SugarConfig;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


   private TabLayout tabLayout;
   private ViewPager viewPager;
   private NavigationView navigationView;
   private android.support.v7.widget.Toolbar toolbar;

   // ============================== GIF VIEWS ============================== //
   public static final String key_tt = "tytorial";
   private GifImageView gif_img;
   private TextView content;
   private GifImageView tuto_1;
   private GifImageView tuto_2;
   private GifImageView fireworks_gif;

   // ======================================================================= //


   private RelativeLayout relativeLayoutTutorial;
    private int valuest = 1;
    private SharedPreferences preferences;
    private MediaPlayer success;
    private MediaPlayer congrats;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetupTransitions();
        SugarContext.init(this);
        setContentView(R.layout.activity_main);

        success = MediaPlayer.create(this, R.raw.success);
        congrats = MediaPlayer.create(this, R.raw.complete);

        relativeLayoutTutorial = findViewById(R.id.tutorialrelative);
        tuto_1 = findViewById(R.id.giftuto_1);
        tuto_2 = findViewById(R.id.giftuto_2);
        fireworks_gif = findViewById(R.id.fireworks);

        gif_img = findViewById(R.id.pikaid);
        content = findViewById(R.id.instructions);

        preferences = getPreferences(MODE_PRIVATE);

        if(preferences.getInt(key_tt, 0) == 0){
            Showtutorial();
        }




        tabLayout = findViewById(R.id.nav_Tab);
        viewPager = findViewById(R.id.vipager);
toolbar = findViewById(R.id.toolbarMain);
SetupActionBar();

ChangeTitle(0);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title1vipager));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title2vipager));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title3vipager));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                ChangeTitle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

     PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

     viewPager.setAdapter(pagerAdapter);


    }


    // ============================================================== Configuración de Toolbar ====================================================================== //
    // ============================================================================================================================================================== //

    private void SetupActionBar() {




        DrawerLayout drawerLayout = findViewById(R.id.drawer);



toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,  R.string.opendrawer, R.string.closedrawer);
navigationView = findViewById(R.id.navi);

navigationView.setNavigationItemSelectedListener(this);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void SetupTransitions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setExitTransition(new ChangeImageTransform());

        }
    }


    // ====================================================================== Cambiar titulo de toolbar ==================================================================== //
   // ====================================================================================================================================================================== //

    private void ChangeTitle(int position) {

        switch (position){
            case 0:
                getSupportActionBar().setTitle(R.string.title1vipager);
                break;
            case 1:
                getSupportActionBar().setTitle(R.string.title2vipager);
                break;
            case 2:
                getSupportActionBar().setTitle(R.string.title3vipager);
                break;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.shareapp:
                Log.e("MAIN", "onNavigationItemSelected: "+item.getTitle());
                break;

        }


        return false;
    }

    void Showtutorial(){
        relativeLayoutTutorial.setVisibility(View.VISIBLE);

        content.setText(R.string.tuto1);
        valuest = 2;
    }


    public void Next(View view){
switch (valuest){
    case 2:
        content.setText(R.string.tuto2);
        tuto_1.setVisibility(View.VISIBLE);
        success.start();
        break;
    case 3:
        content.setText(R.string.tuto3);
        tuto_1.setVisibility(View.GONE);
        tuto_2.setVisibility(View.VISIBLE);
        success.start();
        break;
    case 4:
        content.setText(R.string.tuto4);
        tuto_2.setVisibility(View.GONE);
        fireworks_gif.setVisibility(View.VISIBLE);
        congrats.start();
        break;
    case 5:
        relativeLayoutTutorial.setVisibility(View.GONE);
        valuest = 0;
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(key_tt, 1);
        editor.commit();
        break;
}

valuest++;
    }

}

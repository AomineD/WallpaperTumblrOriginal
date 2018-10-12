package com.colvengames.wallpapertumblr;

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
import android.view.Window;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


   private TabLayout tabLayout;
   private ViewPager viewPager;
   private NavigationView navigationView;
   private android.support.v7.widget.Toolbar toolbar;

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



toolbar.setTitleTextColor(getResources().getColor(R.color.itemnav));

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
}

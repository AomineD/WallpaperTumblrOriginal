package com.colvengames.wallpapertumblr.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.colvengames.wallpapertumblr.MainActivity;
import com.colvengames.wallpapertumblr.R;
import com.colvengames.wallpapertumblr.intro.IntroActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    public static final long segundos = 7;
public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

preferences = getPreferences(MODE_PRIVATE);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(preferences.getInt(MainActivity.key_tt, 0) == 0){
                    startActivity(new Intent(SplashActivity.this, IntroActivity.class));

                }else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }
            }
        }, 1000 * segundos);
    }
}

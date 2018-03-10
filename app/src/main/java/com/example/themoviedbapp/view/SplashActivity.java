package com.example.themoviedbapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.themoviedbapp.R;

/**
 * Created by Jean on 2018/3/9.
 */

public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_SECONDMILL = 3000;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_SECONDMILL);
    }
}

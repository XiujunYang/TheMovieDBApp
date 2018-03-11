package com.example.themoviedbapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.example.themoviedbapp.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import static com.example.themoviedbapp.util.AppConstant.SPLASH_SCREEN_SECONDMILL;

/**
 * Created by Jean on 2018/3/9.
 */

public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_SCREEN_SECONDMILL);
    }
}

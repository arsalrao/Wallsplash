package com.media.wallpapers.wallsplash;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MyApp extends Application {
    public static MyApp instance = null;

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        instance = new MyApp();
    }
}

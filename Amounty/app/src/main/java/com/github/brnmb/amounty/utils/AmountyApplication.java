package com.github.brnmb.amounty.utils;

import android.content.Context;
import android.content.res.Configuration;
import com.activeandroid.ActiveAndroid;

public class AmountyApplication extends com.activeandroid.app.Application {
    private static Context context;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ActiveAndroid.initialize(this);
    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
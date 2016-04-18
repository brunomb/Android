package org.github.brnmb.android.active.utils;

import android.content.res.Configuration;
import com.activeandroid.ActiveAndroid;

/**
 * The type My application.
 */
public class MyApplication extends com.activeandroid.app.Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
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

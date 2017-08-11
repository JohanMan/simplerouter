package com.johan.simplerouter;

import android.app.Application;

import com.johan.library.simplerouter.SimpleRouter;

/**
 * Created by johan on 2017/8/11.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SimpleRouter.init(this);
    }

}

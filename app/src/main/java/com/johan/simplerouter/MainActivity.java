package com.johan.simplerouter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.johan.library.simplerouter.SimpleRouter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUserLogin(View view) {
        SimpleRouter.create(UserRouter.class).goLogin();
    }

}

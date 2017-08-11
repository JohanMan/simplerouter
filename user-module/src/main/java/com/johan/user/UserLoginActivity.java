package com.johan.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.johan.library.simplerouter.SimpleRouter;

/**
 * Created by johan on 2017/8/11.
 */

public class UserLoginActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_login);
    }

    public void goToMeInfo(View view) {
        SimpleRouter.create(MeRouter.class).goToMeInfo();
    }

}

package com.johan.user;

import com.johan.library.simplerouter.RouterDomain;
import com.johan.library.simplerouter.RouterPath;

/**
 * Created by johan on 2017/8/11.
 */

@RouterDomain("app://me")
public interface MeRouter {

    @RouterPath("/info")
    void goToMeInfo();

}

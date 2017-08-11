package com.johan.simplerouter;

import com.johan.library.simplerouter.RouterDomain;
import com.johan.library.simplerouter.RouterPath;

/**
 * Created by johan on 2017/8/11.
 */

@RouterDomain("app://user")
public interface UserRouter {

    @RouterPath("/login")
    void goLogin();

}

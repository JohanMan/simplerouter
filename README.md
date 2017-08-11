# simplerouter
自定义路由

## 使用实例

### 声明activity
```
<activity android:name=".UserLoginActivity">
    <intent-filter>
        <data android:scheme="app" android:host="user" android:path="/login" />
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
    </intent-filter>
</activity>
```

### 设计接口
```
@RouterDomain("app://user")
public interface UserRouter {
    @RouterPath("/login")
    void goLogin();
}
```

### 初始化
```
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SimpleRouter.init(this);
    }
}
```

### 跳转
```
SimpleRouter.create(UserRouter.class).goLogin();
```

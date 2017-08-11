package com.johan.library.simplerouter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.LruCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by johan on 2017/8/10.
 */

public class SimpleRouter {

    // 最大缓存
    private static final int DEFAULT_CACHE_SIZE = 10;
    // ApplicationContext
    private static Context routerContext;
    // 缓存已经放射过的数据，减少反射的次数
    private static LruCache<Method, String> urlCache;

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        init(context, DEFAULT_CACHE_SIZE);
    }

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context, int cacheSize) {
        routerContext = context.getApplicationContext();
        urlCache = new LruCache<>(cacheSize);
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> iRouter) {
        return (T) Proxy.newProxyInstance(iRouter.getClassLoader(), new Class[]{iRouter}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 如果之前解析过了，直接打开Activity
                String url = urlCache.get(method);
                if (url != null) {
                    openActivity(url);
                    return null;
                }
                // 开始解析
                // 域名，可以不写
                String domain = null;
                if (iRouter.isAnnotationPresent(RouterDomain.class)) {
                    RouterDomain routerDomain = iRouter.getAnnotation(RouterDomain.class);
                    domain = routerDomain.value();
                }
                // 路径，必须有，否则报错
                if (!method.isAnnotationPresent(RouterPath.class)) {
                    throw new RuntimeException(iRouter.getName() + " " + method.getName() + " should has RouterPath Annotation");
                }
                RouterPath uriPath = method.getAnnotation(RouterPath.class);
                String path = uriPath.value();
                StringBuilder urlBuilder = new StringBuilder();
                if (domain != null) urlBuilder.append(domain);
                urlBuilder.append(path);
                // 参数
                Annotation[][] paramAnnotations = method.getParameterAnnotations();
                for (int index = 0; index < paramAnnotations.length; index++) {
                    Annotation[] paramAnnotation = paramAnnotations[0];
                    if (paramAnnotation == null || paramAnnotation.length == 0) {
                        continue;
                    }
                    if (paramAnnotation[0] instanceof RouterParam) {
                        RouterParam uriParam = (RouterParam) paramAnnotation[0];
                        String param = uriParam.value();
                        String connector = index == 0 ? "?" : "&";
                        urlBuilder.append(connector).append(param).append("=").append(args[index]);
                    }
                }
                // 打开Activity
                if (openActivity(urlBuilder.toString())) {
                    // 保存起来
                    urlCache.put(method, urlBuilder.toString());
                }
                return null;
            }
        });
    }

    /**
     * 打开Activity
     * @param url
     * @return
     */
    private static boolean openActivity(String url) {
        PackageManager packageManager = routerContext.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isValid = !activities.isEmpty();
        if (isValid) {
            routerContext.startActivity(intent);
        }
        return isValid;
    }

}

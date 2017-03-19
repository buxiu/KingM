package com.km.app;

import okhttp3.OkHttpClient;

/**
 * OkHttp单例
 * <p>
 * Created by yuan on 2017/3/18.
 */

public class OkHttpSingleton {

    private static volatile OkHttpClient client;

    private OkHttpSingleton() {

    }
    //线程安全和效率
    public static OkHttpClient getOkHttpClient() {
        if (client == null) {
            synchronized (OkHttpSingleton.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }


}

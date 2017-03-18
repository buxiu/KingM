package com.km.app;

import okhttp3.OkHttpClient;

/**
 * OkHttp单例
 *
 * Created by yuan on 2017/3/18.
 */

public class OkHttpSingleton {

    private static OkHttpClient client;

    public static synchronized OkHttpClient getOkHttpClient(){
        if(client == null){
            client = new OkHttpClient();
        }
        return client;
    }
}

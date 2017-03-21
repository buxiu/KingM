package com.km.util;

import android.os.Handler;
import android.os.Looper;

import com.km.tools.CheckSumBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp单例
 * <p>
 * Created by SoYao on 2017/3/19.
 */

public class OkHttpManager {

    private static volatile OkHttpManager okHttpManager;

    private String mNonce, mCurTime, mCheckSum;

    private OkHttpClient mClient;
    private Handler mHandler;


    //线程安全和效率
    private static OkHttpManager getInstance() {
        if (okHttpManager == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpManager == null) {
                    okHttpManager = new OkHttpManager();
                }
            }
        }
        return okHttpManager;
    }

    private OkHttpManager() {
        mClient = new OkHttpClient();
        Builder builder = mClient.newBuilder();

        /**
         * 设置连接超时.读取超时，写入超时
         */
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * post表单请求
     */
    public static void postAsync(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().getChangHeader();
        getInstance().inner_postAsync(url, params, callBack);
    }

    private void inner_postAsync(String url, Map<String, String> params, final DataCallBack callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        FormBody.Builder builder = new FormBody.Builder();
        //在这对添加的参数进行遍历
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey();
            String value;
            //先判断值是否为空
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }
            //把键和值添加到FormBody
            builder.add(key, value);
        }
        RequestBody requestBody = builder.build();

        final Request request = new Request.Builder().url(url)
                .addHeader("AppKey", "9d3872403fd29e0a4c40e20026e90e3c")
                .addHeader("Nonce", mNonce)//变量，随机数
                .addHeader("CurTime", mCurTime)//变量，时间
                .addHeader("CheckSum", mCheckSum)//变量，检查数值
                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .post(requestBody).build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                deliverDataSuccess(jsonObject, callBack);
            }
        });
    }

    /**
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.requestFailure(request, e);
            }
        });
    }

    /**
     * 分发成功的时候调用
     *
     * @param jsonObject
     * @param callBack
     */
    private void deliverDataSuccess(final JSONObject jsonObject, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.requestSuccess(jsonObject);
            }
        });
    }

    /**
     * 回调接口
     */
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(JSONObject jsonObject);
    }

    /**
     * 获取可变请求变量
     */
    private void getChangHeader() {
        //appSecret
        String appSecret = "74a533580191";

        //随机数
        Random random = new Random();
        mNonce = random.nextInt(10) + "";

        //时间
        mCurTime = String.valueOf((new Date()).getTime() / 1000L);

        //CheckSum
        mCheckSum = CheckSumBuilder.getCheckSum(appSecret,mNonce,mCurTime);
    }

}

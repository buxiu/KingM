package com.km.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by SoYao on 2017/3/21.
 */

public class RegisterUtil {

    public static void registerUser(Map<String, String> user, final MsgCallBack callBack) {
        OkHttpManager.postAsync("https://api.netease.im/nimserver/user/create.action",
                user,
                new OkHttpManager.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {
                        Log.i(getClass().getName(), e.getMessage());
                    }

                    @Override
                    public void requestSuccess(JSONObject jsonObject) {
                        try {
                            String code = jsonObject.getString("code");
                            if ("200".equals(code)){
                                callBack.onSuccess("恭喜您！注册成功");
                            }else if ("403".equals(code)){
                                callBack.onError("非法操作或没有权限");
                            }else if ("414".equals(code)){
                                callBack.onError("用户已存在，请换一个试试");
                            }else if ("416".equals(code)){
                                callBack.onError("您的操作过于频繁");
                            }else if ("431".equals(code)){
                                callBack.onError("HTTP重复请求");
                            }else if ("500".equals(code)){
                                callBack.onError("服务器内部错误");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public interface MsgCallBack {
        void onError(String e);

        void onSuccess(String tips);
    }
}

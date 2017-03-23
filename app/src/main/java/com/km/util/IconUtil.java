package com.km.util;

import android.text.TextUtils;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

import com.km.tools.CompressImage;
import com.km.util.RegisterUtil.MsgCallBack;
import com.netease.nim.uikit.session.actions.PickImageAction;
import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.ResponseCode;
import com.netease.nimlib.sdk.nos.NosService;

import java.io.File;


/**
 * Created by SoYao on 2017/3/21.
 */

public class IconUtil {
    /**
     * Bomb图片上传
     * @param path
     * @param callBack
     */
    public static void up(String path, final MsgCallBack callBack) {
        final BmobFile bmobFile = new BmobFile(CompressImage.doing(path));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    callBack.onSuccess(bmobFile.getFileUrl());
                } else {
                    callBack.onError(e.getMessage());
                }
            }
        });
    }

    /**
     * 云信存储图片
     * @param path
     * @param callBack
     */
    public static void upload(String path, final MsgCallBack callBack) {
        File file = CompressImage.doing(path);
        AbortableFuture<String> uploadFuture = NIMClient.getService(NosService.class)
                .upload(file, PickImageAction.MIME_JPEG);
        uploadFuture.setCallback(new RequestCallbackWrapper<String>() {
            @Override
            public void onResult(int code, String url, Throwable throwable) {
                if (code == ResponseCode.RES_SUCCESS && !TextUtils.isEmpty(url)) {
                    callBack.onSuccess(url);
                }
            }

            @Override
            public void onFailed(int i) {
                callBack.onError("上传头像失败");
                super.onFailed(i);
            }
        });
    }

}

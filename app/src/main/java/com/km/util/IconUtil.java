package com.km.util;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;
import com.km.tools.CompressImage;
import com.km.util.RegisterUtil.MsgCallBack;

/**
 * Created by SoYao on 2017/3/21.
 */

public class IconUtil {

    public static void up(String path, final MsgCallBack callBack){
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
}

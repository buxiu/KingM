package com.km.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by SoYao on 2017/1/12.
 */

public class PictureCamera {

    private Context context;
    private File mPictureFile;

    public PictureCamera(Context context){
        this.context = context;
    }

    public Intent initIntent(){
        // 设置相机拍照后照片保存路径
        mPictureFile = new File(context.getExternalCacheDir(),
                "picture" + System.currentTimeMillis() / 1000 + ".jpg");
        // 启动拍照,并保存到临时文件
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPictureFile));
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);

        return intent;
    }

    public String getCameraPath(){
        String fileSrc;
        if (null == mPictureFile) {
            return "";
        }
        fileSrc = mPictureFile.getAbsolutePath();
        return fileSrc;
    }
}

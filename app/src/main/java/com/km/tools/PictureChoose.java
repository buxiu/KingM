package com.km.tools;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by SoYao on 2017/1/12.
 */

public class PictureChoose {

    private Context context;

    public PictureChoose(Context context){
        this.context = context;
    }

    public Intent initIntent(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        return intent;
    }

    public String getPath(Intent data){
        String fileSrc;
        if ("file".equals(data.getData().getScheme())) {
            // 有些低版本机型返回的Uri模式为file
            fileSrc = data.getData().getPath();
        } else {
            // Uri模型为content
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(data.getData(), proj,
                    null, null, null);
            cursor.moveToFirst();
            int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            fileSrc = cursor.getString(idx);
            cursor.close();
        }
        return fileSrc;
    }
}

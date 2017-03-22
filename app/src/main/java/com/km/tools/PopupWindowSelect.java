package com.km.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.km.R;


/**
 * Created by SoYao on 2017/2/26.
 */

public class PopupWindowSelect implements View.OnClickListener {

    private PopupWindow mpw;
    private LayoutInflater inflater;
    private ImageButton ibtnGallery,ibtnCamera;
    private IbtnListener ibtnListener;

    public PopupWindowSelect(Context context,IbtnListener ibtnListener) {
        this.ibtnListener = ibtnListener;
        inflater = LayoutInflater.from(context);
        View contentView = inflater.inflate(R.layout.pw_select_view, null);

        mpw = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mpw.setBackgroundDrawable(new BitmapDrawable(context.getResources(), (Bitmap) null));
        mpw.setOutsideTouchable(true);
//        mpw.setAnimationStyle(R.style.PopupMenuAnim);
        mpw.setContentView(contentView);

        ibtnGallery = (ImageButton) contentView.findViewById(R.id.ibtn_gallery);
        ibtnCamera = (ImageButton) contentView.findViewById(R.id.ibtn_camera);

        ibtnGallery.setOnClickListener(this);
        ibtnCamera.setOnClickListener(this);
    }

    public void show(){
        View parentView = inflater.inflate(R.layout.activity_main,null);
        mpw.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ibtn_gallery:
                ibtnListener.galleryListener();
                mpw.dismiss();
                break;
            case R.id.ibtn_camera:
                ibtnListener.cameraListener();
                mpw.dismiss();
                break;
        }
    }

    public interface IbtnListener{
        void galleryListener();
        void cameraListener();
    }
}

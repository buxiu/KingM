package com.km.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.km.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {

    private static final int RUNTIME_PERMISSION_REQUEST_CODE = 1;
    private static RuntimePermissionListener mRuntimePermissionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);//进栈
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);//出栈
    }

    public static void requestPermission(String[] permissions, RuntimePermissionListener listener){
        Activity topActivity = ActivityCollector.getTopActivity();
        if(topActivity == null){//如果连栈顶Activity都没有，也就没必要申请权限了
            return;
        }
        mRuntimePermissionListener = listener;
        List<String> deniedPermissionList = new ArrayList<>();
        for (String permission : permissions){
            if(ContextCompat.checkSelfPermission(topActivity,permission) != PackageManager.PERMISSION_GRANTED){
                deniedPermissionList.add(permission);
            }
        }
        if(deniedPermissionList.isEmpty()){
            //权限全部允许
            mRuntimePermissionListener.onRuntimePermissionGranted();
        }else {
            //权限没有被全部允许
            String[] deniedPermissionArray = deniedPermissionList.toArray(new String[deniedPermissionList.size()]);
            //请求未被允许的权限
            ActivityCompat.requestPermissions(topActivity,deniedPermissionArray,RUNTIME_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case RUNTIME_PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0){
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++){
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            deniedPermissions.add(permission);
                        }
                    }
                    if(deniedPermissions.isEmpty()){
                        //允许所请求的全部权限
                        mRuntimePermissionListener.onRuntimePermissionGranted();
                    }else {
                        //拒绝所请求的部分或全部权限
                        mRuntimePermissionListener.onRuntimePermissionDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }
}
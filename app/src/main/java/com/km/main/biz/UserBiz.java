package com.km.main.biz;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.netease.nim.uikit.NimUIKit;

import org.w3c.dom.Text;

/**
 *
 * Created by yuan on 2017/3/19.
 */

public class UserBiz implements IUserBiz {
    @Override
    public void toChatUI(Context context, String account) {
        //启动单聊界面
        NimUIKit.startP2PSession(context, account);
    }

    @Override
    public void toUserUI(Context context) {
        //启动用户资料界面
        Toast.makeText(context,"user",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toContactUI(Context context) {
        //启动最近联系人界面
        Toast.makeText(context,"contact",Toast.LENGTH_SHORT).show();
    }


}

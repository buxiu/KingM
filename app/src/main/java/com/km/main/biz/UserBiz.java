package com.km.main.biz;

import android.content.Context;

import com.netease.nim.uikit.NimUIKit;

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
}

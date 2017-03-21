package com.km.main.biz;

import android.content.Context;

/**
 *
 * Created by yuan on 2017/3/19.
 */

public interface IUserBiz {

    /**
     * 进入聊天界面
     *
     * Activity 类型的Context 以及聊天对象的 Account */
    void toChatUI(Context context,String account);

}

package com.km.main.present;

import android.content.Context;

import com.km.base.BasePresent;
import com.km.main.biz.IUserBiz;
import com.km.main.biz.UserBiz;
import com.km.main.view.MainView;

/**
 *
 * Created by yuan on 2017/3/19.
 */

public class MainPresent extends BasePresent<MainView> {

    private IUserBiz userBiz;
    private Context mContext;

    public MainPresent(Context context){
        mContext = context;
        userBiz = new UserBiz();
    }

    public void toChatUI(){
        userBiz.toChatUI(mContext,mView.getAccount());
    }

}

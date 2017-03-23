package com.km.main.present;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.km.base.BasePresent;
import com.km.main.biz.IUserBiz;
import com.km.main.biz.UserBiz;
import com.km.main.view.MainView;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

    public void toChatUI(String account){
        userBiz.toChatUI(mContext,account);
    }

    public void toUserUI(){
        userBiz.toUserUI(mContext);
    }

    public void toContactUI(){
        userBiz.toContactUI(mContext);
    }

    /**
     * 发送消息
     */
    public void sendMessage(String sessionId,String content){
        // 创建文本消息
        IMMessage message = MessageBuilder.createTextMessage(
                sessionId,
                SessionTypeEnum.P2P,
                content
        );
        //设置为true,发送失败后能重发
        NIMClient.getService(MsgService.class).sendMessage(message,true);
    }

    public void showToast(String content){
        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
    }

    public void setUserIcon(final CircleImageView view , final String accounts){
        UserService userService = NIMClient.getService(UserService.class);
        NimUserInfo user = userService.getUserInfo(accounts);
        if(user != null){
            String icon_url = user.getAvatar();
            Log.d("-------", "setUserIcon: 本地");
            Glide.with(mContext).load(icon_url).into(view);
        }else {
            Log.d("-------", "setUserIcon: 服务器");
            List<String> list = new ArrayList<>();
            list.add(accounts);
            userService.fetchUserInfo(list).setCallback(new RequestCallback<List<NimUserInfo>>() {
                @Override
                public void onSuccess(List<NimUserInfo> nimUserInfos) {
                    String icon_url = nimUserInfos.get(0).getAvatar();
                    Log.d("-------", "onSuccess" + icon_url);
                     Glide.with(mContext).load(icon_url).into(view);
                }

                @Override
                public void onFailed(int i) {
                    Log.d("-------", "onFailed" + i);
                }

                @Override
                public void onException(Throwable throwable) {
                    Log.d("-------", "onException" + throwable.toString());
                }
            });
        }
    }

}

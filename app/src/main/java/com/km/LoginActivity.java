package com.km;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.km.base.BaseActivity;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class LoginActivity extends BaseActivity {
    private EditText account;//用户名
    private EditText passwd;//密码
    private Button login;//登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//设置第四步中的View
        account = (EditText) findViewById(R.id.account);//初始化控件
        passwd  = (EditText)findViewById(R.id.passwd);//初始化控件
        login = (Button) findViewById(R.id.login);//初始化控件
        //实现Button的监听
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        final String name = account.getText().toString().toLowerCase();//获取edittext上用户输入的account
        final String pwd = passwd.getText().toString();//获取edittext上用户输入的pwd
        LoginInfo info = new LoginInfo(name,pwd); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {
                        Toast.makeText(LoginActivity.this,"登录失败: "+code,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);//进行登录,实现登录的回调
    }
}

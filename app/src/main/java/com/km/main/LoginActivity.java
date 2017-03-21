package com.km.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.km.R;
import com.km.util.Preferences;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText account;//用户名
    private EditText passwd;//密码
    private Button login,toRegister;//登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);//设置第四步中的View
        account = (EditText) findViewById(R.id.account);//初始化控件
        passwd  = (EditText)findViewById(R.id.passwd);//初始化控件
        login = (Button) findViewById(R.id.login);//初始化控件
        //实现Button的监听
        login.setOnClickListener(this);
        toRegister.setOnClickListener(this);

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
                        saveLoginInfo(name, pwd);
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
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

    private void saveLoginInfo(final String account, final String token) {
        Preferences.saveUserAccount(account);
        Preferences.saveUserToken(token);
    }

    public static void actionActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.login:
                login();
                break;
            case R.id.btn_to_register:
                RegisterActivity.actionActivity(this);
                break;
        }
    }
}

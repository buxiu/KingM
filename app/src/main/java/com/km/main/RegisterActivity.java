package com.km.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.km.R;
import com.km.util.RegisterUtil;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToLogin, btnRegister;
    private EditText etAccid, etName, etPassword;
    private String mAccid, mName, mPassword;

    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        etAccid = (EditText) findViewById(R.id.et_accid);
        etName = (EditText) findViewById(R.id.et_name);
        etPassword = (EditText) findViewById(R.id.et_passwd);

        tvShow = (TextView) findViewById(R.id.tv_show);

        btnToLogin = (Button) findViewById(R.id.btn_to_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_to_login:
                toLogin();
                break;
            case R.id.btn_register:
                if (isOK()) {
                    register();
                }
                break;
        }
    }

    private void toLogin() {
        LoginActivity.actionActivity(this);
    }

    private void register() {

        Map<String, String> user = new HashMap<>();
        user.put("accid", mAccid);
        user.put("name", mName);
        user.put("token", mPassword);

        //
        RegisterUtil.registerUser(user, new RegisterUtil.MsgCallBack() {
            @Override
            public void onError(String e) {
                tvShow.setText(e);
            }

            @Override
            public void onSuccess(String tips) {
                tvShow.setText(tips);
            }
        });
    }

    private boolean isOK() {
        mAccid = etAccid.getText().toString();
        mName = etName.getText().toString();
        mPassword = etPassword.getText().toString();

        if (mAccid.isEmpty() && mName.isEmpty() && mPassword.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}

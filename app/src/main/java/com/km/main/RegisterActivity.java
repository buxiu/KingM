package com.km.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.km.R;
import com.km.tools.PictureCamera;
import com.km.tools.PictureChoose;
import com.km.tools.PopupWindowSelect;
import com.km.util.IconUtil;
import com.km.util.RegisterUtil;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_PICTURE_CHOOSE_HEAD = 0x1;
    private static final int REQUEST_PICTURE_CAMERA_HEAD = 0x2;

    private Button btnToLogin, btnRegister;
    private EditText etAccid, etName, etPassword;
    private String mAccid, mName, mPassword,mIcon = "";

    private TextView tvShow;

    private ImageView ivIcon;

    private PictureCamera mCamera;
    private PictureChoose mChoose;

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
        ivIcon = (ImageView) findViewById(R.id.iv_icon);

        btnToLogin = (Button) findViewById(R.id.btn_to_login);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnToLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        ivIcon.setOnClickListener(this);
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
            case R.id.iv_icon:
                showSelectPopupView();
                break;
        }
    }

    private void toLogin() {
        LoginActivity.actionActivity(this);
        finish();
    }

    private void register() {

        Map<String, String> user = new HashMap<>();
        user.put("accid", mAccid);
        user.put("name", mName);
        user.put("token", mPassword);
        user.put("icon",mIcon);

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

    //弹框
    private void showSelectPopupView() {
        PopupWindowSelect popupWindowSelect = new PopupWindowSelect(RegisterActivity.this,
                new PopupWindowSelect.IbtnListener() {
                    @Override
                    public void galleryListener() {
                        if (mChoose == null) {
                            mChoose = new PictureChoose(RegisterActivity.this);
                        }
                        startActivityForResult(mChoose.initIntent(), REQUEST_PICTURE_CHOOSE_HEAD);
                    }

                    @Override
                    public void cameraListener() {
                        if (mCamera==null){
                            mCamera = new PictureCamera(RegisterActivity.this);
                        }
                        startActivityForResult(mCamera.initIntent(), REQUEST_PICTURE_CAMERA_HEAD);
                    }
                });
        popupWindowSelect.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICTURE_CHOOSE_HEAD:
                    String chooseSrc = mChoose.getPath(data);
                    upIcon(chooseSrc);
                    break;
                case REQUEST_PICTURE_CAMERA_HEAD:
                    String cameraSrc = mCamera.getCameraPath();
                    if (cameraSrc.equals("")) {
                        return;
                    }
                    upIcon(cameraSrc);
                    break;
            }
        }
    }

    private void upIcon(String path){
        IconUtil.up(path, new RegisterUtil.MsgCallBack() {
            @Override
            public void onError(String e) {
                tvShow.setText(e);
            }

            @Override
            public void onSuccess(String tips) {
                mIcon = tips;
                Glide.with(RegisterActivity.this)
                        .load(tips)
                        .into(ivIcon);

                tvShow.setText("头像上传成功");
            }
        });
    }

    public static void actionActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}

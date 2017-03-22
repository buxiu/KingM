package com.km.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.km.R;
import com.km.app.KMCache;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    private void init() {
        if(TextUtils.isEmpty(KMCache.getAccount())){
            startActivity(new Intent(this,LoginActivity.class));
        }else {
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();
    }
}

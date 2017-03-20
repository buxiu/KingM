package com.km.main;

import android.os.Bundle;
import android.view.View;

import com.km.R;
import com.km.base.BaseActivity;
import com.km.main.present.MainPresent;
import com.km.main.view.MainView;

public class MainActivity extends BaseActivity<MainView,MainPresent> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public MainPresent initPresenter() {
        return new MainPresent(this);
    }

    @Override
    public String getAccount() {
        return "test2";
    }

    public void toChatUI(View v){
        present.toChatUI();
    }
}

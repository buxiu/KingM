package com.km.my;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.km.R;
import com.km.app.KMCache;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.uinfo.UserService;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDataActivity extends AppCompatActivity {

    NavigationView navView;
    public CircleImageView user_icon;
    public TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        initNavigationView();
        initBackView();
    }

    private void initBackView() {
        CircleImageView back_view = (CircleImageView) findViewById(R.id.nav_back);
        back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initNavigationView() {
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_question:
                        Toast.makeText(MyDataActivity.this,"q",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_setting:
                        Toast.makeText(MyDataActivity.this,"s",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        initHeaderView();
    }

    private void initHeaderView() {
        user_icon = (CircleImageView) navView.getHeaderView(0).findViewById(R.id.icon_user_image);
        user_name = (TextView) navView.getHeaderView(0).findViewById(R.id.user_name);
        getAllData();
    }

    private void getAllData(){
        UserService userService = NIMClient.getService(UserService.class);
        NimUserInfo user = userService.getUserInfo(KMCache.getAccount());
        if(user != null){
            String icon_url = user.getAvatar();
            Glide.with(this).load(icon_url).into(user_icon);
            user_name.setText(user.getName());
        }
    }
}

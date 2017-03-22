package com.km.main;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.km.R;
import com.km.base.BaseActivity;
import com.km.main.adapter.CardAdapter;
import com.km.main.adapter.CheckAnswerListener;
import com.km.main.bean.Answer;
import com.km.main.present.MainPresent;
import com.km.main.view.MainView;
import com.wangxiandeng.swipecardrecyclerview.ItemRemovedListener;
import com.wangxiandeng.swipecardrecyclerview.SwipeCardLayoutManager;
import com.wangxiandeng.swipecardrecyclerview.SwipeCardRecyclerView;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity<MainView,MainPresent> implements MainView {

    private static final String QUSTION = "  Q：";
    private static final String ME = "M：";

    SwipeCardRecyclerView mRecyclerView;
    CardAdapter mCardAdapter;
    CircleImageView user_icon_btn,contact_icon_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBtn();
        initCard();
    }

    private void initBtn() {
        user_icon_btn = (CircleImageView) findViewById(R.id.user_icon);
        contact_icon_btn = (CircleImageView) findViewById(R.id.contact_icon);
        user_icon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present.toUserUI();
            }
        });
        contact_icon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                present.toContactUI();
            }
        });
    }

    private void initCard() {
        mRecyclerView = (SwipeCardRecyclerView) findViewById(R.id.cardView);
        mRecyclerView.setLayoutManager(new SwipeCardLayoutManager());
        final List<Answer> mList = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            Answer answer = new Answer();
            answer.setAccount("test2");
            answer.setTitle(QUSTION + "你调皮了悟空" + i);
            answer.setA(ME + "没有啊" + i);
            answer.setB(ME + "就是，你能那我咋滴" + i);
            answer.setC(ME + "啦啦啦啦啦啦" + i );
            answer.setIcon_url("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490163389146&di=3c81a9a5442a4b349ac9ead6fa39001d&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fac4bd11373f082022a37d9a949fbfbedaa641bfa.jpg");
            mList.add(answer);
        }
        mCardAdapter = new CardAdapter(this, mList, new CheckAnswerListener() {
            @Override
            public void isTrue(String account) {
                //答对了
                present.toChatUI(account);
            }

            @Override
            public void isFalse() {
                //答错了
            }
        });
        mRecyclerView.setAdapter(mCardAdapter);
        mRecyclerView.setRemovedListener(new ItemRemovedListener() {
            @Override
            public void onRightRemoved() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftRemoved() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public MainPresent initPresenter() {
        return new MainPresent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}

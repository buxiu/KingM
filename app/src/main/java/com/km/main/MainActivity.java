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

public class MainActivity extends BaseActivity<MainView,MainPresent> implements MainView {

    private SwipeCardRecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCard();
    }

    private void initCard() {
        mRecyclerView = (SwipeCardRecyclerView) findViewById(R.id.cardView);
        mRecyclerView.setLayoutManager(new SwipeCardLayoutManager());
        final List<Answer> mList = new ArrayList<>();
        for(int i = 0;i < 10;i++){
            Answer answer = new Answer();
            answer.setTitle("你调皮了悟空" + i);
            answer.setA("没有啊" + i);
            answer.setB("就是，你能那我咋滴" + i);
            answer.setC("啦啦啦啦啦啦" + i );
            mList.add(answer);
        }
        mCardAdapter = new CardAdapter(this, mList, new CheckAnswerListener() {
            @Override
            public void isTrue() {
                //答对了
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
    public String getAccount() {
        return "test2";
    }

    public void toChatUI(View v){
        present.toChatUI();
    }
}

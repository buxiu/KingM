package com.km.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.km.R;
import com.km.main.bean.Answer;
import com.wangxiandeng.swipecardrecyclerview.SwipeCardAdapter;

import java.util.List;

/**
 *
 * Created by yuan on 2017/3/21.
 */

public class CardAdapter extends SwipeCardAdapter<CardAdapter.Holder>{

    private Context mContext;

    public CardAdapter(Context context,List list) {
        super(list);
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setData((Answer) mList.get(position));
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView title;
        private Button a_btn,b_btn,c_btn;

        Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_answer);
            a_btn = (Button) itemView.findViewById(R.id.A_answer);
            b_btn = (Button) itemView.findViewById(R.id.B_answer);
            c_btn = (Button) itemView.findViewById(R.id.C_answer);
        }

        public void setData(Answer answer){
            title.setText(answer.getTitle());
            a_btn.setText(answer.getA());
            b_btn.setText(answer.getB());
            c_btn.setText(answer.getC());
        }
    }
}

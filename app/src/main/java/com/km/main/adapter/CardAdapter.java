package com.km.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private CheckAnswerListener mCheckAnswerListener;

    public CardAdapter(Context context,List list,CheckAnswerListener checkAnswerListener) {
        super(list);
        mContext = context;
        mCheckAnswerListener = checkAnswerListener;
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
        private RadioGroup radioGroup;
        private RadioButton a_rb,b_rb,c_rb;
        private Button check_btn;
        int right_flag;
        int flag; //0-A 1-B 2-C

        Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_answer);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.answer_group);
            a_rb = (RadioButton) itemView.findViewById(R.id.A_answer);
            b_rb = (RadioButton) itemView.findViewById(R.id.B_answer);
            c_rb = (RadioButton) itemView.findViewById(R.id.C_answer);
            check_btn = (Button) itemView.findViewById(R.id.check_answer);
            setOnCheckedChangeListener();
            setCheckBtnOnclickListener();
        }

        private void setOnCheckedChangeListener(){
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.A_answer:
                            flag = 0;
                            break;
                        case R.id.B_answer:
                            flag = 1;
                            break;
                        case R.id.C_answer:
                            flag = 2;
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        private void setCheckBtnOnclickListener(){
            check_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkAnswer()){
                        mCheckAnswerListener.isTrue();
                    }else {
                        mCheckAnswerListener.isFalse();
                    }
                }
            });
        }

        public void setData(Answer answer){
            title.setText(answer.getTitle());
            a_rb.setText(answer.getA());
            b_rb.setText(answer.getB());
            c_rb.setText(answer.getC());
        }

        private boolean checkAnswer(){
            return (right_flag == flag);
        }

    }
}

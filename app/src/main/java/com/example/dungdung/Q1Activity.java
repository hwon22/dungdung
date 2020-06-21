package com.example.dungdung;

import android.content.Intent;
import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Q1Activity extends Activity {

    private Button Yes;
    private Button No;
    private int cnt=0;
    private int[] quiz_r=new int[2];
    ArrayList<String> quiz = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_1)
        ;
        final TextView q=(TextView)findViewById(R.id.q);
        quiz.add("혼자서 조용히 책 읽는 것을 좋아한다.");
        q.setText(quiz.get(cnt));
        cnt++;
        Yes = (Button) findViewById(R.id.yes);
        Yes.setOnClickListener(onClickListener);
        No = (Button) findViewById(R.id.no);
        No.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final TextView q=(TextView)findViewById(R.id.q);
            quiz.add("대체로 좋아하는 작가, 분야가 정해져 있다.");
            quiz.add("책을 읽고 느낀점을 쓰는 것을 좋아한다.");
            quiz.add("타인이 남긴 책의 리뷰를 꼼꼼히 살펴보지 않고 책을 구매한다.");
            quiz.add("책을 읽은 후 다른 사람들과의 독서토론을 한다면 참여하지 않을 것 이다..");
            quiz.add("인상깊은 부분에 밑줄을 긋는다.");
            quiz.add("책을 읽는 도중에 짧게 메모지에 책의 핵심 내용을 정리한다.");
            quiz.add("같은 책을 여러번 읽기도 한다.");

            if(cnt<3) {
                q.setText(quiz.get(cnt));
            }
            else{
                showToast(Q1Activity.this, "당신에게 꼭 맞는 독서법 입니다!");
                if(quiz_r[0]>quiz_r[1]){
                    myStartActivity(QR1Activity.class);
                }
                else{
                    myStartActivity(QR2Activity.class);
                }
            }

            switch (v.getId()) {
                case R.id.yes:
                    yes_bt(0);
                    break;
                case R.id.no:
                    no_bt(1);
                    break;
                default:
                    break;
            }
            cnt++;
        }
    };

    public void yes_bt(int n){
        quiz_r[n]++;
    }

    public void no_bt(int n){
        quiz_r[n]++;
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
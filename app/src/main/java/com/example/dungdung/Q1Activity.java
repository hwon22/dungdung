package com.example.dungdung;

import android.content.Intent;
import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Q1Activity extends Activity {

    private Button Yes;
    private Button No;
    private int e, i, s,n,t,f,j,p=0;
    private int cnt=0;
    private char[] quiz_r=new char[4];
    String[] mbti=new String[]{"estp"};
    private LinearLayout mLayout;
    ArrayList<String> quiz = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_1);
        final TextView q=(TextView)findViewById(R.id.q);
        quiz.add("목소리가 큰 편이고 말이 빠르며 제스처를 많이 사용한다.");
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
            quiz.add("다른 사람들이 하는 말에 쉽게 귀가 솔깃해진다.");
            quiz.add("하고 싶은 말을 참지 않고 즉각적으로 표현한다.");
            quiz.add("답답할 때 쇼핑을 하거나 돌아다니면 에너지가 생긴다.");
            quiz.add("늘 여러 사람들과 함께하며 무엇이든 두려움 없이 도전한다.");

            quiz.add("색깔을 고르는 감각이 뛰어나며, 만들기를 좋아한다.");
            quiz.add("실용적인 가치에 더 관심을 가진다.");
            quiz.add("눈에 보이지 않거나 경험하지 않은 추상적인 것은 얼른 이해하기가 힘들다.");
            quiz.add("여러 번 해본 일이나 반복적인 일을 할 때 안심이 되고 편하다.");

            quiz.add("판단을 할 때 감정보다는 원칙과 논리를 가장 중요시 여긴다.");
            quiz.add("주는 만큼 받아야 한다고 생각하며, 받는 만큼 돌려준다.");
            quiz.add("상대방이 울거나 화를 낼 때 어떻게 대응해야 할지 모를 때가 종종 있다.");
            quiz.add("위급한 순간, 힘든 순간 더 냉정하고 이성적으로 행동한다.");

            quiz.add("물건들이 제자리에 있어야 편안하다.");
            quiz.add("한 번 결정하면 다른 의견을 들으려고 하지 않는 경향이 있다.");
            quiz.add("해야 할 일의 순서를 정하고, 꼭 그 순서대로 일을 진행해야 한다.");
            quiz.add("싫어도 지시에 따라야 질서가 잡힌다고 생각한다.");

            if(cnt<4) {
                q.setText(quiz.get(cnt));
                switch (v.getId()) {
                    case R.id.yes:
                        e++;
                        break;
                    case R.id.no:
                        i++;
                        break;
                    default:
                        break;
                }
            }
            else if(cnt<9){
                q.setText(quiz.get(cnt));
                switch (v.getId()) {
                    case R.id.yes:
                        s++;
                        break;
                    case R.id.no:
                        n++;
                        break;
                    default:
                        break;
                }
            }
            else if(cnt<14){
                q.setText(quiz.get(cnt));
                switch (v.getId()) {
                    case R.id.yes:
                        t++;
                        break;
                    case R.id.no:
                        f++;
                        break;
                    default:
                        break;
                }
            }
            else if(cnt<19){
                q.setText(quiz.get(cnt));
                switch (v.getId()) {
                    case R.id.yes:
                        j++;
                        break;
                    case R.id.no:
                        p++;
                        break;
                    default:
                        break;
                }
            }
            else{
                if(e>i){
                    quiz_r[0]='e';
                }
                else if(e<i){
                    quiz_r[0]='i';
                }

                if(s>n){
                    quiz_r[1]='s';
                }
                else if(s<n){
                    quiz_r[1]='n';
                }

                if(t>f){
                    quiz_r[2]='t';
                }
                else if(t<f){
                    quiz_r[2]='f';
                }

                if(j>p){
                    quiz_r[3]='j';
                }
                else if(j<p){
                    quiz_r[3]='p';
                }
                String str = String.valueOf(quiz_r);
                showToast(Q1Activity.this, str+"형인 당신에게 꼭 맞는 독서법 입니다!");
                if(str.equals(mbti[0])){
                    myStartActivity(QR1Activity.class);
                }
            }
            cnt++;
        }
    };


    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }
}
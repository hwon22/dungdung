package com.example.dungdung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;


public class QuizMainActivity extends Activity {

    private Button QuizMain1;
    private Button QuizMain2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main);

        QuizMain1 = (Button) findViewById(R.id.bt1);
        QuizMain1.setOnClickListener(onClickListener);
        QuizMain2 = (Button) findViewById(R.id.bt2);
        QuizMain2.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt1:
                    bt1();
                    break;
                case R.id.bt2:
                    bt2();
                    break;
                default:
                    break;
            }
        }
    };

    public void bt1() {
        showToast(QuizMainActivity.this, "당신에게 꼭 맞는 독서법을 찾아보세요!");
        myStartActivity(Q1Activity.class);
    }

    public void bt2() {
        showToast(QuizMainActivity.this, "당신에게 꼭 맞는 독서 분야를 찾아보세요!");
        myStartActivity(SignInActivity.class);
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
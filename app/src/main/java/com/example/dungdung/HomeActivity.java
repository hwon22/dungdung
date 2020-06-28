
package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends Activity {
    private Button btnRead;
    private Button btnQuiz;
    private ImageButton btnRecord;
    private Button btnProfile;
    private Button btnHome;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(onClickListener);
        btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setOnClickListener(onClickListener);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(onClickListener);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(onClickListener);
        btnProfile= (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    showToast(HomeActivity.this, "추가 화면으로 이동합니다.");
                    myStartActivity(AddActivity.class);
                    break;
                case R.id.btnHome:
                        showToast(HomeActivity.this, "현재 화면입니다.");
                    break;
                case R.id.btnRead:
                        showToast(HomeActivity.this, "읽기 화면으로 이동합니다.");
                        myStartActivity(TimerActivity.class);
                    break;
                case R.id.btnProfile:
                        showToast(HomeActivity.this, "프로필 화면으로 이동합니다.");
                        myStartActivity(ProfileActivity.class);
                    break;
                case R.id.btnQuiz:
                        showToast(HomeActivity.this, "퀴즈 화면으로 이동합니다.");
                        myStartActivity(QuizMainActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(onClickListener);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(onClickListener);
        btnRecord = (ImageButton) findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(onClickListener);
        btnProfile= (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnRead:
                        showToast(HomeActivity.this, "타이머 화면으로 이동합니다.");
                        myStartActivity(TimerActivity.class);
                        finish();
                    break;
                case R.id.btnProfile:
                        showToast(HomeActivity.this, "구현x.");
                    break;
                case R.id.btnQuiz:
                        showToast(HomeActivity.this, "퀴즈 화면으로 이동합니다.");
                        myStartActivity(QuizMainActivity.class);
                        finish();
                    break;
                case R.id.btnRecord:
                        showToast(HomeActivity.this, "구현중");
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

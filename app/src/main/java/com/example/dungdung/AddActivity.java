package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dungdung.R;

public class AddActivity extends Activity {

    private ImageButton closeBtn;
    private Button recordSt;
    private Button readSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        closeBtn = (ImageButton)findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(onClickListener);
        recordSt =(Button)findViewById(R.id.recordSt);
        recordSt.setOnClickListener(onClickListener);
        readSt=(Button)findViewById(R.id.readSt);
        readSt.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.closeBtn:
                    myStartActivity(HomeActivity.class);
                    break;
                case R.id.recordSt:
                    showToast(AddActivity.this, "기록하기를 선택하셨습니다.");
                    myStartActivity(RecordActivity.class);
                    break;
                case R.id.readSt:
                    showToast(AddActivity.this, "읽기를 선택하셨습니다.");
                    myStartActivity(TimerActivity.class);
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

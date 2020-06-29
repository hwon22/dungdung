package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dungdung.R;

public class AddActivity extends Activity {

    private EditText inputName;
    private EditText inputSection;
    private ImageButton closeBtn;
    private Button recordSt;
    private Button readSt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inputName=(EditText)findViewById(R.id.nameInput);
        inputSection=(EditText)findViewById(R.id.sectionInput);

        closeBtn = (ImageButton)findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(onClickListener);
        recordSt =(Button)findViewById(R.id.recordSt);
        recordSt.setOnClickListener(onClickListener);
        readSt =(Button)findViewById(R.id.readSt);
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
                    add();
                    //myStartActivity(RecordActivity.class);
                    break;
                case R.id.readSt:
                    add2();
                    break;
            }
        }
    };

    public void add() {
        String namevalues = inputName.getText().toString();
        String sectionvalues = inputSection.getText().toString();
        if (namevalues.length() > 0 && sectionvalues.length() > 0) {
            showToast(AddActivity.this, "기록 화면으로 이동합니다.");
            myStartActivity(RecordActivity.class,namevalues);
            finish();
        } else {
            showToast(AddActivity.this, "빈칸을 모두 입력해주세요.");

        }
    }

    public void add2(){
        String namevalues = inputName.getText().toString();
        String sectionvalues = inputSection.getText().toString();
        if (namevalues.length() > 0 && sectionvalues.length() > 0) {
            showToast(AddActivity.this, "읽기 화면으로 이동합니다.");
            myStartActivity(TimerActivity.class,namevalues);
            finish();
        } else {
            showToast(AddActivity.this, "빈칸을 모두 입력해주세요.");

        }
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void myStartActivity(Class c,String a) {
        Intent intent = new Intent(this, c);
        intent.putExtra("책 제목",a);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }
}

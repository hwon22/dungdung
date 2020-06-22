package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.dungdung.SignUpActivity.showToast;

public class SignInActivity extends Activity {

    private EditText inputId;
    private EditText inputPass;

    private Button SignUpButton;
    private Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        inputId = (EditText) findViewById(R.id.id);
        inputPass = (EditText) findViewById(R.id.pass);

        SignUpButton = (Button) findViewById(R.id.button);
        SignUpButton.setOnClickListener(onClickListener);
        SignInButton = (Button) findViewById(R.id.button2);
        SignInButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button2://회원가입 버튼
                    signUp();
                    break;
                case R.id.button://로그인 버튼
                    login();
                    break;
                default:
                    break;
            }
        }
    };

    public void signUp() {
        //회원가입화면으로 이동
        showToast(SignInActivity.this, "회원가입 화면으로 이동합니다.");
        myStartActivity(SignUpActivity.class);
        finish();
    }

    public void login() {
        //로그인 시키기 - 주어진 정보로
        String idvalues = inputId.getText().toString();
        String pwvalues = inputPass.getText().toString();
        if (idvalues.length() > 0 && pwvalues.length() > 0) {
            showToast(SignInActivity.this, "로그인되었습니다.");
            myStartActivity(HomeActivity.class);
            finish();
        } else {
            showToast(SignInActivity.this, "빈칸을 모두 입력해주세요.");

        }


    }

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
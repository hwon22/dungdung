package com.example.dungdung;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity{
    SQLiteDatabase db;
    MySQLiteOpenHelper helper;

    private String id;
    private String pass;
    private String passCheck;
    private String email;
    private String partOne;
    private String partTwo;
    private String partThree;
    private Button signUp;
    private String result;

    private static final String DATABASE_NAME="user.db";
    private static final int DATABASE_VERSION=1;
    private static final String USER_TABLE_NAME="user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //helper= new MySQLiteOpenHelper(SignUpActivity.this,"user.db",null,1);

        signUp = (Button)findViewById(R.id.buttonSignup);
        signUp.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonSignup:
                   // SignUp();
                    break;
            }
        }
    };

    private void SignUp(){
        id=((EditText)findViewById(R.id.id)).getText().toString();
        pass=((EditText)findViewById(R.id.id)).getText().toString();
        passCheck=((EditText)findViewById(R.id.id)).getText().toString();
        email=((EditText)findViewById(R.id.id)).getText().toString();
        partOne=((EditText)findViewById(R.id.id)).getText().toString();
        partTwo=((EditText)findViewById(R.id.id)).getText().toString();
        partThree=((EditText)findViewById(R.id.id)).getText().toString();

        if(id.length()>0 && pass.length()>0 && passCheck.length()>0 && email.length()>0
        && partOne.length()>0 && partTwo.length()>0 && partThree.length()>0){
            //모든 칸에 입력 되어있다면 디비에 저장하고 토스트 메시지로 회원가입에 성공했습니다, 로그인화면으로 가기
            if(pass==passCheck) {
                insert(id, pass, email, partOne, partTwo, partThree); //디비에 저장하는 함수
                showToast(SignUpActivity.this, "회원가입에 성공했습니다.");
                myStartActivity(SignInActivity.class);
                finish();
            }
            else
                showToast(SignUpActivity.this,"비밀번호 확인을 해주세요.");
        }
        else{
            //다 입력 ㄴㄴ
            showToast(SignUpActivity.this, "빈칸을 채워주세요.");
        }
    }

    public void insert(String a,String b, String c, String d, String e, String f){
        db=helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",a);
        values.put("pass",b);
        values.put("email",c);
        values.put("partOne",partOne);
        values.put("partTwo",partTwo);
        values.put("partThree",partThree);

        db.insert(USER_TABLE_NAME,"",values);

    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        //Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

}

package com.example.dungdung;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {//회원가입 버튼을 클릭시 수행
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        final EditText idText=findViewById(R.id.idText);
        final EditText passwordText = findViewById(R.id.passwordText);

        final Button loginButton =findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPass = passwordText.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse=new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");

                            if (success) {//회원등록 성공한 경우
                               AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                               dialog=builder.setMessage("로그인에 성공했습니다.")
                                       .setPositiveButton("확인",null)
                                       .create();
                               dialog.show();
                               myStartActivity(HomeActivity.class);
                               finish(); overridePendingTransition(0, 0);
                            }
                            else{//회원등록 실패한 경우
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog=builder.setMessage("계정을 다시 확인하세요.")
                                        .setNegativeButton("다시 시도",null)
                                        .create();
                                dialog.show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest=new LoginRequest(userID,userPass,responseListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
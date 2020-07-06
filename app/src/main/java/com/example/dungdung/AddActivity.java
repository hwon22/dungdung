package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.dungdung.R;

import org.json.JSONObject;

public class AddActivity extends Activity {

    private Spinner s;
    private EditText inputName;

    private boolean validate = false;
    private AlertDialog dialog; //알림창

    private ImageButton closeBtn;
    private Button recordSt;
    private Button readSt;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        inputName = (EditText) findViewById(R.id.nameInput);
        s = (Spinner) findViewById(R.id.spinnerArea);

        closeBtn = (ImageButton) findViewById(R.id.closeBtn);
        recordSt = (Button) findViewById(R.id.recordSt);
        readSt = (Button) findViewById(R.id.readSt);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                value = (String) parent.getItemAtPosition(position);
                if (!value.equals("선택"))
                    showToast(AddActivity.this, "position : " + position + value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStartActivity(HomeActivity.class);
            }
        });

        recordSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //기록하기 버튼을 클릭 했을 경우 책 제목과 분야를 디비에 저장한다. 단, 있던 책은 안됨^^
                //중복확인임
                String bookName = inputName.getText().toString();
                String bookPart = value;
                String bookDo = "읽기 전";

                if (validate) {
                    return;
                }
                if (bookName.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    dialog = builder.setMessage("책 제목은 빈칸 ㄴㄴ")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(value.equals("선택")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    dialog = builder.setMessage("책 분야를 선택해주세요")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                                dialog = builder.setMessage("등록 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                inputName.setEnabled(false); //수정 불가능하게
                                validate = true;
                                finish();
                                myStartActivity(RecordActivity.class);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                                dialog = builder.setMessage("등록 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateAddRequest validateAddRequest = new ValidateAddRequest(bookName,bookPart,bookDo,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddActivity.this);
                queue.add(validateAddRequest);

            }
        });

        readSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //기록하기 버튼을 클릭 했을 경우 책 제목과 분야를 디비에 저장한다. 단, 있던 책은 안됨^^
                //중복확인임
                String bookName = inputName.getText().toString();
                String bookPart = value;
                String bookDo = "읽기 전";

                if (validate) {
                    return;
                }
                if (bookName.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    dialog = builder.setMessage("책 제목은 빈칸 ㄴㄴ")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                if(value.equals("선택")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                    dialog = builder.setMessage("책 분야를 선택해주세요")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                                dialog = builder.setMessage("등록 성공했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                inputName.setEnabled(false); //수정 불가능하게
                                validate = true;
                                finish();
                                myStartActivity(TimerActivity.class);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                                dialog = builder.setMessage("등록 실패했습니다.")
                                        .setNegativeButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                ValidateAddRequest validateAddRequest = new ValidateAddRequest(bookName,bookPart,bookDo,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddActivity.this);
                queue.add(validateAddRequest);

            }
        });

    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void myStartActivity(Class c, String a) {
        Intent intent = new Intent(this, c);
        intent.putExtra("책 제목", a);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

}

package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class RecordActivity extends Activity
{
  Button memoStart;
  Button recordStart;
  Button recordStop;
  Button recordPlay;
  Button recordPlay2;
  Button recordPlayStop;
  Button recordPlayStop2;

  ImageButton memoBtn;
  ImageButton recordBtn;
  ImageButton backBtn;
  private boolean clickIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setup();
    }

    private void setup(){
        memoStart=(Button)findViewById(R.id.memoStart);
        memoBtn=(ImageButton)findViewById(R.id.memoBtn);
        recordStart=(Button)findViewById(R.id.recordStart);
        recordStop=(Button)findViewById(R.id.recordStop);
        recordBtn=(ImageButton)findViewById(R.id.recordBtn);
        recordPlay=(Button)findViewById(R.id.recordPlay);
        recordPlayStop=(Button)findViewById(R.id.recordPlayStop);
        recordPlay2=(Button)findViewById(R.id.recordPlay2);
        recordPlayStop2=(Button)findViewById(R.id.recordPlayStop2);

        backBtn=(ImageButton)findViewById(R.id.backBtn);
        memoBtn.setOnClickListener(onClickListener);
        memoStart.setOnClickListener(onClickListener);
        recordBtn.setOnClickListener(onClickListener);
        recordStart.setOnClickListener(onClickListener);
        recordStop.setOnClickListener(onClickListener);
        recordPlay.setOnClickListener(onClickListener);
        recordPlayStop.setOnClickListener(onClickListener);
        recordPlay2.setOnClickListener(onClickListener);
        recordPlayStop2.setOnClickListener(onClickListener);
        backBtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.memoBtn: //메모 버튼 클릭 하면 리코드 버튼 사라지고 시작 버튼, 편집 버튼 나오게 함
                    recordBtn.setVisibility(View.INVISIBLE); //리코드 버튼 지우기
                    memoStart.setVisibility(View.VISIBLE);
                    memoStart.setOnClickListener(onClickListener);
                    clickIt=true;
                    break;

                case R.id.memoStart:
                    showToast(RecordActivity.this, "메모를 생성합니다.");
                    //메모생성할때 화면 전환하기 ^^
                    break;

                case R.id.recordBtn:
                    memoBtn.setVisibility(View.INVISIBLE); //리코드 버튼 지우기
                    recordStart.setVisibility(View.VISIBLE);
                    recordStop.setVisibility(View.VISIBLE);
                    recordPlay.setVisibility(View.VISIBLE);
                    recordPlay2.setVisibility(View.VISIBLE);
                    recordPlayStop.setVisibility(View.VISIBLE);
                    recordPlayStop2.setVisibility(View.VISIBLE);
                    clickIt=true;
                    break;

                case R.id.recordStart:
                    showToast(RecordActivity.this, "녹음을 시작합니다.");
                    break;

                case R.id.recordStop:
                    showToast(RecordActivity.this, "녹음을 중지합니다.");
                    break;

                case R.id.recordPlayStop:
                    showToast(RecordActivity.this, "재생을 중지합니다.");
                    break;

                case R.id.recordPlay:
                    showToast(RecordActivity.this,"재생을 시작합니다.");
                    break;

                case R.id.recordPlay2:
                    showToast(RecordActivity.this,"재시작합니다.");
                    break;

                case R.id.recordPlayStop2:
                    showToast(RecordActivity.this,"일시정지 합니다.");
                    break;

                case R.id.backBtn:
                    if(!clickIt){
                        myStartActivity(AddActivity.class);
                    }
                    else{
                        myStartActivity(RecordActivity.class); overridePendingTransition(0, 0); //화면 전환 애니메이션 제거 문장임!! (깔끔함)
                    }
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
    }

}

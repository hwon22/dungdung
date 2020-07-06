
package com.example.dungdung;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class HomeActivity extends Activity {

    private Button btnQuiz;
    private ImageButton btnRecord;
    private Button btnProfile;
    private Button btnHome;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView id = findViewById(R.id.idText);

        Intent secondIntent = getIntent();
        String idText = secondIntent.getStringExtra("아이디");

        id.setText(idText);
        ListView listview ;
        ListViewAdapter adapter;

        // Adapter 생성
        adapter = new ListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);
        // 첫 번째 아이템 추가.
        adapter.addItem(
                "도전! 웹소설 쓰기", "읽기 전","소설") ;
        // 두 번째 아이템 추가.
        adapter.addItem(
                    "걸어서 세계 한 바퀴", "읽는 중 -2020.07.07-","수필") ;
        // 세 번째 아이템 추가.
        adapter.addItem(
                    "운동은 삶의 낙", "읽는 중 -2020.07.07-","운동") ;

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(onClickListener);
        btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setOnClickListener(onClickListener);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(onClickListener);
        btnProfile= (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(onClickListener);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                String partStr = item.getDesc() ;

                // TODO : use item data.
            }
        }) ;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    showToast(HomeActivity.this, "추가 화면으로 이동합니다.");
                    myStartActivity(AddActivity.class); overridePendingTransition(0, 0);
                    break;
                case R.id.btnHome:
                        showToast(HomeActivity.this, "현재 화면입니다.");
                    break;
                case R.id.btnProfile:
                        showToast(HomeActivity.this, "프로필 화면으로 이동합니다.");
                        myStartActivity(ProfileActivity.class); overridePendingTransition(0, 0);
                    break;
                case R.id.btnQuiz:
                        showToast(HomeActivity.this, "퀴즈 화면으로 이동합니다.");
                        myStartActivity(QuizMainActivity.class); overridePendingTransition(0, 0);
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

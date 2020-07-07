
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

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    private Button btnQuiz;
    private ImageButton btnRecord;
    private Button btnProfile;
    private Button btnHome;
    private Button btnAdd;
    AlertDialog dialog;
    String idText;

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ListViewItem> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView id = findViewById(R.id.idText);

        Intent secondIntent = getIntent();
        idText = secondIntent.getStringExtra("아이디");
        id.setText(idText) ;

        ListView listview = null;
        bookList = new ArrayList<ListViewItem>();


        // Adapter 생성
        adapter = new ListViewAdapter(getApplicationContext(), bookList);


        // 리스트뷰 참조 및 Adapter달기


        listview.setAdapter(adapter);

        try{
            //intent로 값을 가져옵니다 이때 JSONObject타입으로 가져옵니다
            JSONObject jsonObject = new JSONObject(secondIntent.getStringExtra("userList"));


            //List.php 웹페이지에서 response라는 변수명으로 JSON 배열을 만들었음..
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;

            String bookName,bookPart,bookDo;

            //JSON 배열 길이만큼 반복문을 실행
            while(count < jsonArray.length()){
                //count는 배열의 인덱스를 의미
                JSONObject object = jsonArray.getJSONObject(count);

                bookName = object.getString("userID");//여기서 ID가 대문자임을 유의
                bookPart = object.getString("userPassword");
                bookDo = object.getString("userName");

                //값들을 User클래스에 묶어줍니다
                ListViewItem item = new ListViewItem(bookName, bookPart, bookDo);
                bookList.add(item);//리스트뷰에 값을 추가해줍니다
                count++;
            }


        }catch(Exception e){
            e.printStackTrace();
        }



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

                String nameStr = item.getName() ;
                String partStr = item.getPart() ;
                String doStr = item.getDo() ;

                // TODO : use item data.
            }
        }) ;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    showToast(HomeActivity.this, idText);
                    myStartActivity(AddActivity.class,idText); overridePendingTransition(0, 0);
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
    private void myStartActivity(Class c, String b) {
        Intent userIDintent = new Intent(this, c);
        userIDintent.putExtra("아이디",b);
        userIDintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(userIDintent);
    }


    public static void showToast(Activity activity, String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }


}

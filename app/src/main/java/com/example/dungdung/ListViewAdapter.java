package com.example.dungdung;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<ListViewItem> bookList;

    public ListViewAdapter(Context context, List<ListViewItem> bookList){
        this.context = context;
        this.bookList = bookList;
    }

    //출력할 총갯수를 설정하는 메소드
    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
    }

    //아이템별 아이디를 반환하는 메소드
    @Override
    public long getItemId(int i) {
        return i;
    }

    //가장 중요한 부분
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.listview_item, null);

        //뷰에 다음 컴포넌트들을 연결시켜줌
        TextView userID = (TextView)v.findViewById(R.id.userID);
        TextView bookName = (TextView)v.findViewById(R.id.userPassword);
        TextView userName = (TextView)v.findViewById(R.id.userName);
        TextView userAge = (TextView)v.findViewById(R.id.userAge);

        userID.setText(userList.get(i).getUserID());
        userPassword.setText(userList.get(i).getUserPassword());
        userName.setText(userList.get(i).getUserName());
        userAge.setText(userList.get(i).getUserAge());

        //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임
        v.setTag(userList.get(i).getUserID());

        //만든뷰를 반환함
        return v;
    }
}


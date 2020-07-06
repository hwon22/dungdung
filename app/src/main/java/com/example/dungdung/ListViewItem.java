package com.example.dungdung;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private String titleStr ;
    private String descStr ;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}

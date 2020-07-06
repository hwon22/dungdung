package com.example.dungdung;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    private String titleStr ;
    private String descStr ;
    private String partStr ;


    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setPart(String part) {
        partStr = part ;
    }


    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getPart() {
        return this.partStr ;
    }
}

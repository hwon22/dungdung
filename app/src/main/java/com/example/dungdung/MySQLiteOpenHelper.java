package com.example.dungdung;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context, String name, CursorFactory factory, int version){
        super(context,name,factory,version);

    }
    public void onCreate(SQLiteDatabase db){
        String sql="create table user ("+
                "id text primary key autoincrement, "+
                "pass text, "+
                "email text, "+
                "partOne text, "+
                "partTwo text, "+
                "partThree text);";

        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        String sql="drop table if exists user";
        db.execSQL(sql);
        onCreate(db);
    }
}

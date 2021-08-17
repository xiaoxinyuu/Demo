package com.idsbg.foxconn.myapplication.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqlite extends SQLiteOpenHelper {
    final String CREATE_TABLE_SQL = "create table user(_id integer primary key autoincrement,username,password)";

    public MySqlite(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(SQLiteDatabase sqLiteDatabase, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long id = sqLiteDatabase.insert("user", null, contentValues);
        return id;
    }
    public Cursor queryData(SQLiteDatabase sqLiteDatabase,String username,String password){
        Cursor cursor=sqLiteDatabase.rawQuery("select * from user where username=? and password=?",new String[]{username,password});
        return cursor;
    }
}

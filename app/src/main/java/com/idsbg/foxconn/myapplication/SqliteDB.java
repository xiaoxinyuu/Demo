package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

public class SqliteDB extends Activity {
    SQLiteDatabase db;
    Button bn = null;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertd);
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "my.db3", null);

        System.out.println("路径1111111111111111："+this.getFilesDir().toString());

        listView = (ListView) findViewById(R.id.show);
        bn = (Button) findViewById(R.id.bn3);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) findViewById(R.id.ed3)).getText().toString();
                String content = ((EditText) findViewById(R.id.ed4)).getText().toString();
                try {
                    insertData(db, title, content);
                    Cursor cursor = db.rawQuery("select * from news_inf", null);
                    inflateLite(cursor);
                } catch (SQLiteException se) {
                    //执行ddl创建数据表
                    db.execSQL("create table news_inf(_id integer primary key autoincrement,news_title varchar(50),news_content varchar(255))");
                    insertData(db, title, content);
                    Cursor cursor = db.rawQuery("select * from news_inf", null);
                    inflateLite(cursor);
                }
            }
        });
    }

    private void insertData(SQLiteDatabase sqLiteDatabase, String title, String content) {
        db.execSQL("insert into news_inf values(null,?,?)", new String[]{title, content});
    }

    private void inflateLite(Cursor cursor) {
        //填充SimpleCursorAdaptor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(SqliteDB.this, R.layout.line, cursor, new String[]{"news_title", "news_content"}, new int[]{R.id.my_title, R.id.my_content}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //显示数据

        //System.out.println(adapter);
        //listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}

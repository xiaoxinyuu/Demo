package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DictResolver extends Activity {
    ContentResolver contentResolver;
    Button insert=null;
    Button search=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dict);
        contentResolver=getContentResolver();
        insert=(Button) findViewById(R.id.insert);
        search=(Button) findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=((EditText)findViewById(R.id.word)).getText().toString();
                String detail=((EditText)findViewById(R.id.detail)).getText().toString();
                //插入生词记录
                ContentValues values=new ContentValues();
                values.put(Words.Word.WORD,word);
                values.put(Words.Word.DETAIL,detail);
                contentResolver.insert(Words.Word.DICT_CONTENT_URI,values);
                //显示提示信息
                Toast.makeText(DictResolver.this,"添加生词成功",Toast.LENGTH_SHORT).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=((EditText)findViewById(R.id.key)).getText().toString();
                //执行查询
                Cursor cursor=contentResolver.query(Words.Word.DICT_CONTENT_URI,null,"word like ? or detail like ?",new String[]{"%"+key+"%","%"+key+"%"},null);
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",convertCursorToList(cursor));
                //创建一个Intent
                Intent intent=new Intent(DictResolver.this,ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private ArrayList<Map<String,String>> convertCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> result=new ArrayList<>();
        while (cursor.moveToNext()){
            //将结果集中的数据存入ArrayList中
            Map<String,String> map=new HashMap<>();
            map.put(Words.Word.WORD,cursor.getString(1));
            map.put(Words.Word.DETAIL,cursor.getString(2));
            result.add(map);
        }
        return result;
    }
}






















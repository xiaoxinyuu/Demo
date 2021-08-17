package com.idsbg.foxconn.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        ListView listView=findViewById(R.id.show);
        Intent intent=getIntent();
        //获取该intent所携带的数据
        Bundle bundle=intent.getExtras();
        List<Map<String,String>> list= (List<Map<String, String>>) bundle.getSerializable("data");
        //将List封装为SimpleAdapter
        SimpleAdapter adapter=new SimpleAdapter(ResultActivity.this,list,R.layout.line,new String[]{"word","detail"},new int[]{R.id.my_title,R.id.my_content});
        listView.setAdapter(adapter);





/*        TextView textView=findViewById(R.id.tv);
        //获得启动该Activity的intent
        Intent intent=getIntent();
        Person p=(Person) intent.getSerializableExtra("person");
        textView.setText("name:"+p.getName()+" "+"password:"+p.getPassword()+" "+"gender:"+p.getGender());*/
    }
}

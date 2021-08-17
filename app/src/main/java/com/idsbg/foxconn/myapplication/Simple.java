package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Simple extends Activity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        //获得只能被本应用读写的sharedPreferences对象
        sharedPreferences = getSharedPreferences("simple", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Button read = findViewById(R.id.bn1);
        Button write = findViewById(R.id.bn2);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取字符串数据
                String time = sharedPreferences.getString("time", null);
                int randNum = sharedPreferences.getInt("random", 0);
                String result = time == null ? "您暂时还未写入数据" : "写入时间为:" + time + "\n上次生成的随机数为：" + randNum;
                Toast.makeText(Simple.this, result, Toast.LENGTH_SHORT).show();
            }
        });
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日" + "hh:mm:ss");
                //存入当前时间
                editor.putString("time", sdf.format(new Date()));
                editor.putInt("random", (int) (Math.random() * 100));
                editor.commit();
            }
        });
    }
}

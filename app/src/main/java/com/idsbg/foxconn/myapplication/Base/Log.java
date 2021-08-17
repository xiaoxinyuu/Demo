package com.idsbg.foxconn.myapplication.Base;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.idsbg.foxconn.myapplication.R;

public class Log extends AppCompatActivity {
    EditText name, userpassword;
    Button log, reg;
    MySqlite mySqlite = new MySqlite(Log.this, "MyDatabase.db3", 1);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        System.out.println(this.getFilesDir().toString());

        name = (EditText) findViewById(R.id.name);
        userpassword = (EditText) findViewById(R.id.userpassword);
        log = (Button) findViewById(R.id.log);
        reg = (Button) findViewById(R.id.zhuce);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String password = userpassword.getText().toString();
                Cursor cursor = mySqlite.getReadableDatabase().rawQuery("select * from user where username=? and password=?", new String[]{username, password});

                if (cursor.getCount()==0) {
                    Toast.makeText(Log.this, "登录失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Log.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Log.this,Register.class);
                startActivity(intent);
            }
        });
    }
}

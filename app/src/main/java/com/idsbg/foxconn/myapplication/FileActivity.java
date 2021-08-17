package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileActivity extends Activity {
    final String FILE_NAME = "D:\\a.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        Button bn1 = (Button) findViewById(R.id.bn1);//read
        Button bn2 = (Button) findViewById(R.id.bn2);//write
        final EditText ed1 = (EditText) findViewById(R.id.ed1);
        final EditText ed2 = (EditText) findViewById(R.id.ed2);
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed1.setText(read());
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write(ed2.getText().toString());
                ed1.setText("");
            }
        });
    }

    public String read() {
        try {
            //打开文件输入流
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            //读取文件内容
            while ((hasRead = fis.read(bytes)) > 0) {
                sb.append(new String(bytes, 0, hasRead));
            }
            //关闭文件输入流
            fis.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String content) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            PrintStream printStream = new PrintStream(fos);
            //输出文件内容
            printStream.println(content);
            //关闭文件输出流
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DaoRu extends Activity{
    public static final String FILE_NAME="F://my.db3";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
        FileInputStream fis = null;
        FileOutputStream fos=null;
        File file = new File(FILE_NAME);
        try {
            fos = new FileOutputStream(file);
            fis = openFileInput(this.getFilesDir().toString() + "my.db3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[1024];
        int hasRead = 0;
        //StringBuilder sb = new StringBuilder("");
        //读取文件内容
        while (true) {
            try {
                if (!((hasRead = fis.read(bytes)) > 0)) break;
                fos.write(bytes,0,fis.read(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //sb.append(new String(bytes, 0, hasRead));
        }
        //关闭文件输入流
        try {
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

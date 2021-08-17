package com.idsbg.foxconn.myapplication;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

public class LauncherActivityTest extends LauncherActivity {
    //定义两个Activity的名称
    String[] names = new String[]{"播放图片", "查看动物种类"};
    //定义两个Activity对应的实现类
    Class<?>[] classes={DongHua.class,ExpandableListActivityTest.class};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        setListAdapter(adapter);
    }

    //根据不同列表项返回不同的Intent
    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(LauncherActivityTest.this,classes[position]);
    }
}

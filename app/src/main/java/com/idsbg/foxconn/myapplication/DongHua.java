package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;


public class DongHua extends Activity {
    int[] imageIds=new int[]{
            R.drawable.tubiao,
            R.drawable.dog,
            R.drawable.handsome,
            R.drawable.shizi,
            R.drawable.dog1,
            R.drawable.xiaolu,
            R.drawable.doublep,
            R.drawable.superhero
    };
    int currentImageId=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_main);
        final ImageView imageView=findViewById(R.id.show);
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0x1233){
                    //动态的修改需要显示的图片
                    imageView.setImageResource(imageIds[currentImageId++%imageIds.length]);
                }
            }
        };
        //定义一个计时器，周期的完成任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x1233);
            }
        },0,1200);
    }
}

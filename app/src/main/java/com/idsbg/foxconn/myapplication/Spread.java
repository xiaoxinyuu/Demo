package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class Spread extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main4);
        ImageView imageView=findViewById(R.id.image);
        //获取图片所显示的ClipDrawable对象
        final ClipDrawable drawable= (ClipDrawable) imageView.getDrawable();
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==0x123){
                    //修改level值
                    drawable.setLevel(drawable.getLevel()+100);
                }
            }
        };
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=0x123;
                handler.sendMessage(message);
                //取消定时器
                if(drawable.getLevel()>=10000){
                    timer.cancel();
                }
            }
        },0,300);
    }
}

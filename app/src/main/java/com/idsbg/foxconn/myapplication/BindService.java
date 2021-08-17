package com.idsbg.foxconn.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BindService extends Service {
    private int count;
    private boolean quit;
    private MyBlind blinder = new MyBlind();


    public class MyBlind extends Binder {
        public int getCount() {
            return count;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("service is binded");
        return blinder;
    }

    //service被创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service is created");
        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("service is unbinded");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        System.out.println("service is destroyed");
    }
}

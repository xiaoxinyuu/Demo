package com.idsbg.foxconn.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread implements Runnable {
    private Socket s;
    //向线程发送消息的handler
    private Handler handler;
    //接受消息的handler
    public Handler revHandler;
    //该线程所处理的Socket所对应的流
    BufferedReader br = null;
    OutputStream os = null;

    public ClientThread(Handler handler) {
        this.handler = handler;
        //Log.e("tag1",revHandler.toString());
    }

    @Override
    public void run() {
        try {
            Log.e("tag1","      1111");
            s = new Socket("10.248.173.132", 30000);
            Log.e("tag1","222");
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = s.getOutputStream();
            new Thread() {
                @Override
                public void run() {
                    Log.e("tag1","333");
                    String content = null;
                    //不断读取socket输入流中的内容
                    try {
                        while ((content = br.readLine()) != null) {
                            //每当读取到来自服务器的数据之后，发送消息通知程序界面显示该数据
                            Message msg = new Message();
                            msg.what = 0x123;
                            msg.obj = content;
                            Log.e("tag1",revHandler.toString());
                            handler.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            Looper.prepare();
            Log.e("tag2",revHandler.toString());
            revHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    if (msg.what == 0x345) {
                        try {
                            os.write((msg.obj.toString() + "\r\n").getBytes("UTF-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Log.e("tag3",revHandler.toString());

            Looper.loop();
        } catch (SocketTimeoutException el) {
            System.out.println("网络连接超时");
        } catch (Exception e) {
            Log.e("exception",e.getMessage());
            e.printStackTrace();
        }
    }
}

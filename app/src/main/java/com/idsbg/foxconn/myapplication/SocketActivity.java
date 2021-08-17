package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class SocketActivity extends Activity {
    EditText input;
    TextView show;
    Button bn;
    Handler handler;
    //定义与服务器通信的子线程
    ClientThread clientThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socketxml);
        input=(EditText) findViewById(R.id.input1);
        show=(TextView) findViewById(R.id.show1);
        bn=(Button) findViewById(R.id.send1);
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //如果消息来源于子线程
                if(msg.what==0x123){
                    show.append("\n"+msg.obj.toString());
                }
            }
        };
        clientThread=new ClientThread(handler);

        //客户端启动ClientThread线程创建网络连接，读取来自服务器的数据
        new Thread(clientThread).start();
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Message msg=new Message();
                    msg.what=0x345;
                    msg.obj=input.getText().toString();
                    Log.e("tag",msg.obj.toString());


                    System.out.println(msg.obj.toString());

                    clientThread.revHandler.sendMessage(msg);
                    input.setText("");
                }
               catch (Exception e){
                    e.printStackTrace();
               }
            }
        });
    }
}













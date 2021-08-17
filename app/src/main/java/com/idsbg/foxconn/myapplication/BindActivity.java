package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BindActivity extends Activity {
    Button bind,unbind,getServiceStatus;
    BindService.MyBlind binder;
    private ServiceConnection conn=new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (BindService.MyBlind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindxml);
        bind=(Button) findViewById(R.id.bind);
        unbind=(Button)findViewById(R.id.unbind);
        getServiceStatus=(Button)findViewById(R.id.getServiceStatus);
        //创建启动service的intent
        final Intent intent=new Intent(BindActivity.this,BindService.class);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent,conn, Service.BIND_AUTO_CREATE);
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BindActivity.this,"service的count值为："+binder.getCount(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

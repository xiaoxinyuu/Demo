package com.idsbg.foxconn.myapplication;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SmsActivity extends Activity {
    EditText number,substance;
    Button send;
    SmsManager smsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        smsManager=SmsManager.getDefault();
        send=findViewById(R.id.send1);
        number=findViewById(R.id.phoneNum);
        substance=findViewById(R.id.substance);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pi=PendingIntent.getActivity(SmsActivity.this,0,new Intent(),0);
                smsManager.sendTextMessage(number.getText().toString(),null,substance.getText().toString(),pi,null);
                Toast.makeText(SmsActivity.this,"短信发送完成",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

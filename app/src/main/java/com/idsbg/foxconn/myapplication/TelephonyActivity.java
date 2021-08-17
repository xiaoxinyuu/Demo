package com.idsbg.foxconn.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public class TelephonyActivity extends Activity {
    TelephonyManager tManager;
    //声明代表手机状态的集合
    ArrayList<String> statusValues = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telephony);
        tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        PhoneStateListener listener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                switch (state) {
                    //无任何状态
                    case TelephonyManager.CALL_STATE_IDLE:
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        break;
                    //来电了
                    case TelephonyManager.CALL_STATE_RINGING:
                        OutputStream os = null;
                        try {
                            os = openFileOutput("phoneList", MODE_APPEND);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        PrintStream printStream = new PrintStream(os);
                        //将来电记录在文件里
                        printStream.println(new Date() + "来电" + phoneNumber);
                        printStream.close();
                        break;
                    default:
                        break;
                }
                super.onCallStateChanged(state, phoneNumber);
            }
        };
        tManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }
}

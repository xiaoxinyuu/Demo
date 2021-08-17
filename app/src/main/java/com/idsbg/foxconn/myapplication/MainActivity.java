package com.idsbg.foxconn.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends Activity {
    final static int MAX_PROGRESS = 100;
    //该程序模拟填充长度为100的数组
    private int[] data = new int[50];
    //记录进度对话框的完成百分比
    int progressStatus = 0;
    int hasData = 0;
    ProgressDialog pd1, pd2;
    //定义一个负责更新进度的Handler
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 0x123) {
                pd2.setProgress(progressStatus);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showSpinner(View source) {
        //调用静态方法显示环形进度条
        ProgressDialog.show(this, "任务执行中", "任务执行中，请等待", false, true);
    }

    public void showIndeterminate(View source) {
        pd1 = new ProgressDialog(MainActivity.this);
        //设置对话框的标题
        pd1.setTitle("任务正在执行中");
        //设置对话框显示的内容
        pd1.setMessage("任务正在执行中，敬请等待...");
        //设置对话框能用取消按钮关闭
        pd1.setCancelable(true);
        //设置对话框的进度条风格
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置对话框能否显示进度
        pd1.setIndeterminate(true);
        pd1.show();
    }
    public void showProcess(View source){
        //将进度条的完成进度重设为0
        progressStatus=0;
        hasData=0;
        pd2=new ProgressDialog(MainActivity.this);
        pd2.setMax(MAX_PROGRESS);
        pd2.setTitle("任务完成百分比");
        pd2.setMessage("耗时任务完成的百分比");
        pd2.setCancelable(false);
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd2.setIndeterminate(false);
        pd2.show();
        new Thread(){
            @Override
            public void run() {
                while (progressStatus<MAX_PROGRESS){
                    progressStatus=MAX_PROGRESS*doWork()/data.length;
                    handler.sendEmptyMessage(0x123);
                }
                if(progressStatus>=MAX_PROGRESS){
                    pd2.dismiss();
                }
            }
        }.start();
    }
    //模拟一个耗时操作
    public int doWork(){
        data[hasData++]=(int)(Math.random()*100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}































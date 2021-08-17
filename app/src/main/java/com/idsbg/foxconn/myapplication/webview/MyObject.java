package com.idsbg.foxconn.myapplication.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.idsbg.foxconn.myapplication.R;

public class MyObject {
    Context context;
    MyObject(Context c){
        this.context=c;
    }
    //将此方法暴露给JavaScript调用
    @JavascriptInterface
    public void showToast(String name){
        Toast.makeText(context,name+"你好",Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void showList(){
        new AlertDialog.Builder(context).setTitle("音乐列表").setIcon(R.drawable.dog).setItems(new String[]{"花花公子","离人","umbrella"},null).setPositiveButton("确定",null).create().show();

    }
}

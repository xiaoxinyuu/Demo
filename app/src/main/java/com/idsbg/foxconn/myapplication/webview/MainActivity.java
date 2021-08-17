package com.idsbg.foxconn.myapplication.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.idsbg.foxconn.myapplication.R;

public class MainActivity extends Activity {
    WebView show;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_);
        show=findViewById(R.id.show_);
        show.loadUrl("file:///android_asset/test.html");
        WebSettings webSettings=show.getSettings();
        webSettings.setJavaScriptEnabled(true);
        show.addJavascriptInterface(new MyObject(this),"myObj");
    }
}

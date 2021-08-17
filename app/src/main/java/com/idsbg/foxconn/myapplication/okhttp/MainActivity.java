package com.idsbg.foxconn.myapplication.okhttp;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.service.controls.ControlsProviderService.TAG;

public class MainActivity extends Activity {
    String url="http://www.baidu.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(url).get().build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("onResponse"+response.body().string());
            }
        });
    }
}

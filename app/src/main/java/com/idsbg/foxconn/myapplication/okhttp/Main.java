package com.idsbg.foxconn.myapplication.okhttp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Main {
    String url="http://www.baidu.com";
    OkHttpClient okHttpClient=new OkHttpClient();
    public static void main(String[] args) {
        Main main=new Main();
//        main.enqueueGet();
//        main.executeGet();
//        main.enqueuePost();
//        main.formBodyPost();
        main.interceptorGet();
    }
    public void enqueueGet(){
        final Request request=new Request.Builder().url(url).get().build();
        final Call call=okHttpClient.newCall(request);
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
    public void executeGet(){
        Request request=new Request.Builder().url(url).get().build();
        final Call call=okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response=call.execute();
                    System.out.println("run:"+response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void enqueuePost(){
        MediaType mediaType=MediaType.parse("text/x-markdown;charset=utf-8");
        String responseBody="I am superman";
        Request request=new Request.Builder().url(url).post(RequestBody.create(mediaType,responseBody)).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("onFailture");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Headers headers=response.headers();
                for(int i=0;i<headers.size();i++){
                    System.out.println(headers.name(i)+" "+headers.value(i));
                }
                System.out.println("onResponse"+response.body().string());
            }
        });
    }
    public void formBodyPost(){
        RequestBody requestBody=new FormBody.Builder().add("search","Jurassic Park").build();
        final Request request=new Request.Builder().url("https://en.wikipedia.org/w/index.php").post(requestBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.protocol()+" "+response.code()+" "+response.message());
                Headers headers=response.headers();
                for(int i=0;i<headers.size();i++){
                    System.out.println(headers.name(i)+" "+headers.value(i));
                }
                System.out.println("onResponse"+response.body().string());
            }
        });
    }
    public void interceptorGet(){
        OkHttpClient okHttpClient1=new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        final Request request=new Request.Builder().url(url).header("User-Agent","OkHttp Example").build();
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("OnFailure"+e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody responseBody=response.body();
                if(responseBody!=null){
                    System.out.println("OnResponse"+response.body().string());
                    responseBody.close();
                }
            }
        });
    }
}


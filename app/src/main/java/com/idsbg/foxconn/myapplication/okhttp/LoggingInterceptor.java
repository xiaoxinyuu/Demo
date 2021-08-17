package com.idsbg.foxconn.myapplication.okhttp;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoggingInterceptor implements Interceptor {
    private final static String TAG = "LoggingInterceptor";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.nanoTime();
        System.out.println(String.format("sending request %s on %s%s", request.url(), chain.connection(), request.headers()));
        Response response = chain.proceed(request);
        long endTime = System.nanoTime();
        System.out.println(String.format("received response for %s in %.1fms%n%s", response.request().url(), (endTime - startTime) / 1e6d, response.headers()));
        return response;
    }
}

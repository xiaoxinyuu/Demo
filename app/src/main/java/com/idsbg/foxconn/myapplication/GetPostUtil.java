package com.idsbg.foxconn.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPostUtil {
    /**
     * 向指定url发送get请求
     *
     * @param url    发送请求的url
     * @param params 请求参数，请求参数是name1=value1&name2=value2的形式
     * @return url所代表远程资源的响应
     */
    public static String sendGet(String url, String params) {
        String result = "";
        BufferedReader in = null;
        String urlName = url + "?" + params;
        try {
            URL realUrl = new URL(urlName);
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("use-argent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //建立实际的连接
            connection.connect();
            //获取所有的响应字段
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "------->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送get请求出现异常！" + e);
            e.printStackTrace();
        } finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPost(String url,String params){
        PrintWriter out=null;
        String result = "";
        BufferedReader in = null;
        String urlName = url + "?" + params;
        try {
            URL realUrl = new URL(urlName);
            URLConnection connection = realUrl.openConnection();
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("use-argent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");

            //发送post请求必须设置如下两行
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //获取URLConnection对象对应的输出流
            out=new PrintWriter(connection.getOutputStream());
            //发送请求参数
            out.print(params);
            //flush输出流的缓冲
            out.flush();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送post请求出现异常！" + e);
            e.printStackTrace();
        } finally {

            try {
                if(out!=null){
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

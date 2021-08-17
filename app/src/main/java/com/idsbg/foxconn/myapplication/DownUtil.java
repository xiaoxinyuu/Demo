package com.idsbg.foxconn.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownUtil {
    //定义下载资源的路径
    private String path;
    //定义所下载的文件保存的位置
    private String targetFile;
    //定义需要多少条线程下载资源
    private int threadNum;
    //定义下载的线程对象
    private DownThread[] threads;
    //定义下载文件的总大小
    private int fileSize;

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.threadNum = threadNum;
        //初始化Threads数组
        threads = new DownThread[threadNum];
        this.targetFile = targetFile;
    }

    public void downLoad() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/gif,image/jpeg,image/pjpeg,image/jpeg,application/x-shockwave-flash,application/xaml+xml,application/vnd.ms-xpsdocument,application/x-ms-xbap,application/x-ms-application,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        //得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i < threadNum; i++) {
            //设置每条线程开始下载的位置
            int startPos = i * currentPartSize;
            //每条线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            //定位该线程下载的位置
            currentPart.seek(startPos);
            //创建下载线程
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            //启动下载线程
            threads[i].start();
        }
    }

    //获取下载的百分比
    public double getComleteRate() {
        //统计多条线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }

        //返回已经完成的百分比
        return sumSize * 1.0 / fileSize;
    }

    private class DownThread extends Thread {
        //当前线程下载的位置
        private int startPos;
        //定义当前线程负责下载的文件大小
        private int currentPartSize;
        //当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        //定义已经下载的字节数
        public int length;

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPart = currentPart;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "image/gif,image/jpeg,image/pjpeg,image/jpeg,application/x-shockwave-flash,application/xaml+xml,application/vnd.ms-xpsdocument,application/x-ms-xbap,application/x-ms-application,application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword,*/*");
                conn.setRequestProperty("Accept-Language", "zh-CN");
                conn.setRequestProperty("Charset", "UTF-8");
                InputStream in = conn.getInputStream();
                skipFully(in, this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                //读取数据，并写入本地文件
                while (length < currentPartSize && (hasRead = in.read(buffer)) > 0) {
                    currentPart.write(buffer, 0, hasRead);
                    length += hasRead;
                }
                currentPart.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //定义一个为InputStream跳过bytes字节的方法
        public void skipFully(InputStream in, long bytes) throws IOException {
            long remainning = bytes;
            long len = 0;
            while (remainning > 0) {
                len = in.skip(remainning);
                remainning -= len;
            }
        }
    }
}

package com.idsbg.foxconn.myapplication;

import androidx.annotation.RestrictTo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {
    public static ArrayList<Socket> sockets=new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(30000);
        while (true){
            //此行代码会阻塞，一直等待别人的连接
            Socket s=ss.accept();
            sockets.add(s);
            new Thread(new ServerThread(s)).start();
        }
    }

}

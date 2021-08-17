package com.idsbg.foxconn.myapplication.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GreetingClient {
    public static void main(String[] args) {
        String serverName="localhost";
        int port=Integer.parseInt("8081");

        try {
            System.out.println("连接到主机："+serverName+",端口号："+port);
            Socket client=new Socket(serverName,port);
            System.out.println("远程主机地址为："+client.getRemoteSocketAddress());
            DataOutputStream dataOutputStream=new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeUTF("Hello from"+client.getLocalSocketAddress());
            DataInputStream inputStream=new DataInputStream(client.getInputStream());
            System.out.println("服务器响应："+inputStream.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

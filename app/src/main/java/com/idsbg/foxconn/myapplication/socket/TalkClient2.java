package com.idsbg.foxconn.myapplication.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TalkClient2 {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = Integer.parseInt("5500");

        try {
            Socket client = new Socket(serverName, port);
            System.out.println("连接到主机：" + serverName + ",端口号：" + port);

            try {
                Scanner scanner = new Scanner(System.in);
                DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
                DataInputStream inputStream = new DataInputStream(client.getInputStream());
                while (true) {
                    String words = scanner.nextLine();
                    dataOutputStream.writeUTF("2号:" + words);
                    String accept=inputStream.readUTF();
                    System.out.println("服务器响应：" + accept);
                }
            } finally {
                client.close();
            }
        } catch
        (IOException e) {
            e.printStackTrace();
        }
    }
}
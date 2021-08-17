package com.idsbg.foxconn.myapplication.socket;

import android.content.SyncAdapterType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {
    Socket socket;

    public ServerThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String accept=dataInputStream.readUTF();
                System.out.println(accept);
                dataOutputStream.writeUTF("服务器ok");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

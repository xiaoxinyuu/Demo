package com.idsbg.foxconn.myapplication.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer{
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(5500);
            while (true){
                Socket socket=serverSocket.accept();
                ServerThread st=new ServerThread(socket);
                st.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.idsbg.foxconn.myapplication;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;

public class ServerThread implements Runnable {
    Socket s = null;
    BufferedReader br = null;

    public ServerThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
    }

    @Override
    public void run() {
        try {
            String content = null;
            while ((content = readFromClient()) != null) {
                for (Iterator<Socket> it = MyServer.sockets.iterator(); it.hasNext(); ) {
                    Socket s = it.next();
                    try {
                        OutputStream os = s.getOutputStream();
                        os.write((content + "/n").getBytes("UTF-8"));
                    } catch (SocketException e) {
                        e.printStackTrace();
                        it.remove();
                        System.out.println(MyServer.sockets);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            MyServer.sockets.remove(s);
        }
        return null;
    }
}

package com.example.mindthegapchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionToServerThread extends Thread implements Runnable {
    String[] msg;
    String username;

    public ConnectionToServerThread(String[] m, String s) {
        msg = m;
        username = s;
    }

    public void run() {
        Socket socket;
        try {
            socket = new Socket("10.0.6.1", 8081);
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.write("<SENDING>" + username);
            output.flush();

            while (isAlive()) {
                if (msg[0] != null) {
                    output.write(msg[0]);
                    output.flush();
                    msg[0] = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

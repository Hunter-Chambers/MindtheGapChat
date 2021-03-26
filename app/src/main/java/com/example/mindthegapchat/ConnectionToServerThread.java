package com.example.mindthegapchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionToServerThread extends Thread implements Runnable {
    String[] msg;
    Socket socket;

    public ConnectionToServerThread(String[] m, Socket s) {
        msg = m;
        socket = s;
    }

    public void run() {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());

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

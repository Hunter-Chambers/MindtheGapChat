package com.example.mindthegapchat;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionFromServerThread extends Thread implements Runnable {
    Activity activity;
    LinearLayout server_messages;
    ScrollView scrollview;

    public ConnectionFromServerThread(Activity a, LinearLayout ll, ScrollView sv) {
        activity = a;
        server_messages = ll;
        scrollview = sv;
    }

    public void run() {
        Socket socket;
        try {
            socket = new Socket("10.0.6.1", 8081);
            BufferedReader input;

            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.write("<RECEIVING>");
            output.flush();

            while (isAlive()) {
                Log.i("HERE", "HERE");
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = input.readLine();
                //Log.i("HERE", "HERE");
                if (message != null) {
                    activity.runOnUiThread(() -> {
                        ChatBubble bubble = new ChatBubble(activity, server_messages, scrollview, message, false);
                        bubble.createBubble();
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

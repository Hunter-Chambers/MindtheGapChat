package com.example.mindthegapchat;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionFromServerThread extends Thread implements Runnable {
    Activity activity;
    LinearLayout server_messages;
    ScrollView scrollview;
    Socket socket;

    public ConnectionFromServerThread(Activity a, LinearLayout ll, ScrollView sv, Socket s) {
        activity = a;
        server_messages = ll;
        scrollview = sv;
        socket = s;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (isAlive()) {
                String message = input.readLine();
                if (message != null) {
                    activity.runOnUiThread(() -> {
                        TextView msg = new TextView(activity);
                        msg.setBackgroundColor(Color.argb(255, 217, 217, 217));
                        msg.setText(message);
                        msg.setTextColor(Color.BLACK);
                        msg.setTextSize(25);
                        msg.setPadding(40, 40, 40, 40);

                        LinearLayout container = new LinearLayout(activity);
                        container.setOrientation(LinearLayout.HORIZONTAL);
                        container.setWeightSum(1);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        params.setMargins(0, 0, 0, 40);
                        container.setLayoutParams(params);

                        msg.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f));
                        msg.setGravity(Gravity.START);
                        container.addView(msg);
                        container.setGravity(Gravity.START);

                        server_messages.addView(container);
                        scrollview.post(() -> scrollview.fullScroll(View.FOCUS_DOWN));
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

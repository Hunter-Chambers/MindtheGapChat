package com.example.mindthegapchat;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChatBubble {
    Activity activity;
    LinearLayout server_messages;
    ScrollView scrollview;
    boolean isClients;

    String message;
    String username;

    public ChatBubble(Activity a, LinearLayout ll, ScrollView sv, String msg, boolean tOrF) {
        activity = a;
        server_messages = ll;
        scrollview = sv;
        isClients = tOrF;

        String[] parts = msg.split(":");
        username = parts[0];
        message = parts[1];
    }

    public void createBubble() {
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

        TextView title = new TextView(activity);
        title.setText(username);
        title.setTextSize(10);
        title.setPadding(0, 0, 0, 10);

        if (isClients) {
            msg.setGravity(Gravity.END);
            title.setGravity(Gravity.END);
            container.setGravity(Gravity.END);
        } else {
            msg.setGravity(Gravity.START);
            title.setGravity(Gravity.START);
            container.setGravity(Gravity.START);
        }

        container.addView(msg);

        server_messages.addView(title);
        server_messages.addView(container);
    }
}

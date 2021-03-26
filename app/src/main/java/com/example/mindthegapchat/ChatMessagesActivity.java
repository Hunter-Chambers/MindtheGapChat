package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChatMessagesActivity extends AppCompatActivity {

    Intent intent;

    ScrollView scrollview;

    LinearLayout server_messages;
    EditText message_to_send;

    String username, ipv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        intent = getIntent();

        scrollview = findViewById(R.id.messages_scrollview);

        server_messages = findViewById(R.id.server_messages);
        message_to_send = findViewById(R.id.message_field);

        username = intent.getStringExtra("USERNAME");
        ipv4 = intent.getStringExtra("IPv4");
        Log.i("HERE", username);
        Log.i("HERE", ipv4);
    }

    public void sendMessage(View view) {
        TextView message = new TextView(this);
        message.setBackgroundColor(Color.argb(255, 217, 217, 217));
        message.setText(message_to_send.getText().toString());
        message.setTextColor(Color.BLACK);
        message.setTextSize(25);

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.HORIZONTAL);
        container.setWeightSum(1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 20);
        container.setLayoutParams(params);

        message.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f));
        message.setGravity(Gravity.END);
        container.addView(message);
        container.setGravity(Gravity.END);

        server_messages.addView(container);
        scrollview.post(() -> scrollview.fullScroll(View.FOCUS_DOWN));
    }
}
package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChatMessagesActivity extends AppCompatActivity {

    ScrollView scrollview;

    LinearLayout server_messages;
    EditText message_to_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        scrollview = findViewById(R.id.messages_scrollview);

        server_messages = findViewById(R.id.server_messages);
        message_to_send = findViewById(R.id.message_field);
    }

    public void sendMessage(View view) {
        TextView message = new TextView(this);
        message.setBackgroundColor(Color.argb(255, 217, 217, 217));
        message.setText(message_to_send.getText().toString());
        message.setTextColor(Color.BLACK);
        message.setTextSize(25);

        server_messages.addView(message);
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
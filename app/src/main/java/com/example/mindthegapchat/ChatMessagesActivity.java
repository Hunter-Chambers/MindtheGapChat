package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ChatMessagesActivity extends AppCompatActivity {

    Intent intent;

    ScrollView scrollview;

    LinearLayout server_messages;
    EditText message_to_send;

    String username;

    String[] message_array = {null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);

        intent = getIntent();

        scrollview = findViewById(R.id.messages_scrollview);

        server_messages = findViewById(R.id.server_messages);
        message_to_send = findViewById(R.id.message_field);

        username = intent.getStringExtra("USERNAME");
        Log.i("HERE", username);

        ConnectionToServerThread connectionToThread = new ConnectionToServerThread(message_array, username);
        connectionToThread.start();

        ConnectionFromServerThread connectionFromThread = new ConnectionFromServerThread(ChatMessagesActivity.this, server_messages, scrollview);
        connectionFromThread.start();
    }

    public void sendMessage(View view) {
        String temp = String.format(message_to_send.getText().toString());

        ChatBubble bubble = new ChatBubble(this, server_messages, scrollview, username + ":" + temp, true);
        bubble.createBubble();

        message_array[0] = temp;
        message_to_send.getText().clear();
    }
}

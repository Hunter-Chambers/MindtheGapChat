package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    View username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submitButton);
    }

    public void submitUsername(View view) {
        Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);
        username = findViewById(R.id.userUsername);
        if (String.valueOf(username).length() == 0) {
            String anonUsername = "Anonymous";
            intent.putExtra("USERNAME", anonUsername);
        } else {
            intent.putExtra("USERNAME", String.valueOf(username));
        }
        startActivity(intent);
    }
}
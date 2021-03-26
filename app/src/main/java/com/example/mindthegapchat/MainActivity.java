package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText usernameObj;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submitButton);
    }

    public void submitUsername(View view) {
        Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);
        usernameObj = (EditText) findViewById(R.id.userUsername);
        username = usernameObj.getText().toString();
        if (username.length() == 0) username = "Anonymous";
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }
}
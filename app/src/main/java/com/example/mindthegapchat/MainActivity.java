package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submitButton);
    }

    public void tableActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);
        

        startActivity(intent);
    }
}
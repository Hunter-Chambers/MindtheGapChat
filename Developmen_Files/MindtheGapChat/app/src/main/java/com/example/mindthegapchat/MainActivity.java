package com.example.mindthegapchat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    EditText usernameObj;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.submitButton);
        usernameObj = findViewById(R.id.userUsername);
    }

    public void submitUsername(View view) {
        username = usernameObj.getText().toString();
        if (!(username.contains(":"))) {

            Intent intent = new Intent(getApplicationContext(), ChatMessagesActivity.class);

            if (username.length() == 0) username = "Anonymous";
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Username cannot contain a colon.", Toast.LENGTH_SHORT);
            //toast.setMargin(); NEED TO FIX MARGINS LATER, TOAST APPEARS IN BAD LOCATION
            toast.show();
        }
    }
}
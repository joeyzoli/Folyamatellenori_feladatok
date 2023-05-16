package com.example.folyamatellenori_feladatok;

import static com.example.folyamatellenori_feladatok.MainActivity.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.TextView;

public class Ertesites extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ertesites);
        textView = findViewById(R.id.textView);
        //getting the notification message
        String message = getIntent().getStringExtra("message");
        textView.setText(message);
    }


}

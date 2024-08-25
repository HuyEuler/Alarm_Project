package com.example.alarm_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DestinationActivity extends AppCompatActivity {
    private TextView tvNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_destination);

//        int userChoice = getIntent().getExtras().getInt("userChoice");
//        if(userChoice == 1){
//            tvNotification.setText("Turn off alarm");
//        } else if (userChoice == 2) {
//            tvNotification.setText("Pause alarm ");
//        }
    }
}
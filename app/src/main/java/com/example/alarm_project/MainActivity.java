package com.example.alarm_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btn_addAlarm;
    private RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addAlarm = findViewById(R.id.alarm_btn_addAlarm);
        recyclerView = findViewById(R.id.alarm_rv_listAlarm);

        btn_addAlarm.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), AlarmSetup.class);
            startActivity(intent);
        });
    }

}
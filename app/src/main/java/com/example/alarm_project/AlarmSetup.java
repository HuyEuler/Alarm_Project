package com.example.alarm_project;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.alarm_project.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.Map;

public class AlarmSetup extends AppCompatActivity {
    private TextView tv_alarmSetup;
    private Button btn_timeSelect, btn_alarmSet, btn_alarmCancel;

    private TextView tv_alarmSetup2;
    private Button btn_timeSelect2, btn_alarmSet2, btn_alarmCancel2;

    private MaterialTimePicker timePicker;
    Calendar calendar;
    private AlarmManager alarmManager1, alarmManager2;
    private PendingIntent pendingIntent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setup);

        tv_alarmSetup = findViewById(R.id.alarmSetup_tv_selectedTime);
        btn_timeSelect = findViewById(R.id.alarmSetup_btn_selectTime);
        btn_alarmSet = findViewById(R.id.alarmSetup_btn_setAlarm);
        btn_alarmCancel = findViewById(R.id.alarmSetup_btn_cancelAlarm);

        tv_alarmSetup2 = findViewById(R.id.alarmSetup_tv_selectedTime2);
        btn_timeSelect2 = findViewById(R.id.alarmSetup_btn_selectTime2);
        btn_alarmSet2 = findViewById(R.id.alarmSetup_btn_setAlarm2);
        btn_alarmCancel2 = findViewById(R.id.alarmSetup_btn_cancelAlarm2);

        btn_timeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        btn_alarmSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm1();
            }
        });

        btn_alarmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });

        btn_alarmSet2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm2();
            }
        });

    }

    private void cancelAlarm() {
        alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        if(alarmManager1 == null){
            alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager1.cancel(pendingIntent);
        Toast.makeText(this, "Alarm cancel successfully", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm1() {
        alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        alarmManager1.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.d("MainActivity", Integer.toString(timePicker.getHour()) + ":" + Integer.toString(timePicker.getMinute()));
        Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm2() {
        alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        alarmManager2.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 6000, pendingIntent);
        Log.d("MainActivity", "Alarm 2 ring");
        Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Set Alarm")
                .build();
        timePicker.show(getSupportFragmentManager(), "channel_id");

        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timePicker.getHour() > 12){
                    tv_alarmSetup.setText(
                            String.format("%02d", timePicker.getHour()) + ":"
                                    + String.format("%02d", timePicker.getMinute())

                    );
                }
                else{
                    tv_alarmSetup.setText(
                            String.format("%02d", timePicker.getHour()) + ":"
                                    + String.format("%02d", timePicker.getMinute())

                    );
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });

    }

}
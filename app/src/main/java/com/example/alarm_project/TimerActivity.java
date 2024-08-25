package com.example.alarm_project;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimerActivity extends AppCompatActivity {
    private int duration = 120;
    int statusPause = 0, statusStart = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_timer);

        TextView timer_tv_countdown = findViewById(R.id.timer_tv_countdown);
        Button timer_btn_countdown = findViewById(R.id.timer_btn_startCountDown);
        Button timer_btn_pause = findViewById(R.id.timer_btn_pause);
        CircularProgressIndicator timer_pb_progressBar = findViewById(R.id.circularProgressIndicator);

        timer_pb_progressBar.setMax(duration);
        timer_btn_pause.setVisibility(View.INVISIBLE);
        timer_pb_progressBar.setMax(duration);
        MyCountDown countDownTimer = new MyCountDown(duration * 1000L, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                // Used for formatting digits to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timer_tv_countdown.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                Log.d("TAG", Integer.toString((int)millisUntilFinished/1000) + "/" + Integer.toString((int) timer_pb_progressBar.getMax()));
                timer_pb_progressBar.setProgress(timer_pb_progressBar.getMax()- (int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timer_tv_countdown.setText("00:00:00\nEnd countdown");
                Uri alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_NOTIFICATION );
                MediaPlayer mp = MediaPlayer. create (getBaseContext(), alarmSound);
                mp.start();
            }
        };

        timer_btn_countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusStart == 0){
                    countDownTimer.start();
                    timer_btn_countdown.setText("Cancel");
                    timer_btn_pause.setVisibility(View.VISIBLE);
                    statusStart = 1;
                }
                else{
                    countDownTimer.cancel();
                    timer_btn_pause.setVisibility(View.INVISIBLE);
                    timer_btn_countdown.setText("Start");
                    statusStart = 0;
                }
            }
        });


        timer_btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statusPause == 0){
                    countDownTimer.pause();
                    timer_btn_pause.setText("Resume");
                    statusPause = 1;
                }
                else{
                    countDownTimer.resume();
                    timer_btn_pause.setText("Pause");
                    statusPause = 0;
                }
            }
        });
    }
}


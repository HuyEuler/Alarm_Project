package com.example.alarm_project;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Wake cmm up Alarm Receiver", Toast.LENGTH_SHORT).show();
        Uri alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_ALARM );
        MediaPlayer mp = MediaPlayer. create (context, alarmSound);
        mp.start();

        intent = new Intent(context, DestinationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.alarm_icon)
                .setContentTitle("Alarm")
                .setContentText("Wake up ")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
//                .addAction(R.drawable.alarm_icon, "Pause alarm for 5 minutes", pauseAlarmPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(123, builder.build());
    }
}


//        Bundle bundle_stopAlarm = new Bundle();
//        bundle_stopAlarm.putInt("userChoice", 1);
//        Intent intent_stopAlarm = new Intent(context, StopAlarmActivity.class);
//        intent_stopAlarm.putExtras(bundle_stopAlarm);
//        PendingIntent pi_stopAlarm = PendingIntent.getBroadcast(context, 1, intent_stopAlarm,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

//        Bundle bundlePauseAlarm = new Bundle();
//        bundlePauseAlarm.putInt("userChoice", 2);
//        Intent pauseAlarmFor5Minute = new Intent(context, DestinationActivity.class);
//        pauseAlarmFor5Minute.putExtras(bundlePauseAlarm);
//        PendingIntent pauseAlarmPendingIntent = PendingIntent.getBroadcast(context, 1, pauseAlarmFor5Minute,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
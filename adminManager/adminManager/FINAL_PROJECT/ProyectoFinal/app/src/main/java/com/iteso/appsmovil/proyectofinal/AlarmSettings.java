package com.iteso.appsmovil.proyectofinal;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iteso.appsmovil.proyectofinal.alarm.AlarmReceiver;
import com.iteso.appsmovil.proyectofinal.utility.Utility;

import java.util.Calendar;

public class AlarmSettings extends Activity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_settings);

        /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(AlarmSettings.this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(AlarmSettings.this, 0, alarmIntent, 0);

        findViewById(R.id.startAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private String getUsername(){

        SharedPreferences settings = this.getSharedPreferences(Utility.MY_SETTINGS, Context.MODE_PRIVATE);

        return settings.getString("username",null);

    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 15000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Payment Notifications Enabled.", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Payment Notifications Disabled.", Toast.LENGTH_SHORT).show();
    }

}
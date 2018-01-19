package com.aajdindevinc.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notifManager;
    private Boolean firstTime = null;
    final int NOTIFY_ID = 1002;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("HOME");
                    transaction.replace(R.id.frame, new BlankFragment()).commit();
                    return true;

                case R.id.navigation_dashboard:
                    transaction.replace(R.id.frame, new FragmentLocator()).commit();
                    System.out.println("Dashboard");

                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.frame, new FragmentSettings()).commit();
                    System.out.println("Settings");

                    return true;
            }
            return false;
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        isFirstTime();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, new BlankFragment()).commit();



/*

          OVO JE TRAZENJE CEPA
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String id = "my_channel_01";
        CharSequence name = "Notifikacija";
        String description = "TraceBrace vas prati";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(id, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(channel);
        }
        //ZA ANDROID 8.1 i +
        else {

            channel = new NotificationChannel(this, id);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(channel);
            }
*/




    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreferences = this.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            if (firstTime) {
                int i = 0;
                notifikacija("Sa nama ste sigurni", " ");
                ArrayList<Integer> remove = new ArrayList<Integer>();
                TinyDB base = new TinyDB(this);
                base.putInt("id",i);
                base.putListInt("remover",remove);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }

    public void notifikacija(String message1, String message2) {


        String ime = "paks";
        String id = "paks_channel";
        String descript = "paks_chanel";

        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;

        if(notifManager == null) {
            notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int important = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = notifManager.getNotificationChannel(id);
            if(channel==null){
                channel = new NotificationChannel(id, ime, important);
                channel.setDescription(descript);
                channel.enableLights(true);
                channel.setLightColor(Color.rgb(255,159,0));
                notifManager.createNotificationChannel(channel);
            }
            builder = new NotificationCompat.Builder(this, id);

            intent = new Intent(this, MainActivity.class);
            pendingIntent = PendingIntent.getActivity(this,0,intent,0);
            builder.setContentTitle("TraceBrace");
            builder.setContentText(message1);
            builder.setSmallIcon(R.drawable.location);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setAutoCancel(false);
            builder.setContentIntent(pendingIntent);
        }
        else {
            builder = new NotificationCompat.Builder(this);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this,0,intent,0);
            builder.setContentTitle(message1);
            builder.setSmallIcon(R.drawable.location);
            builder.setDefaults(Notification.DEFAULT_ALL);
            builder.setAutoCancel(false);
            builder.setContentIntent(pendingIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }

        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);

    }


}

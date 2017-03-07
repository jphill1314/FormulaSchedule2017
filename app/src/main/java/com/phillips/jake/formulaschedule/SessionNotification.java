package com.phillips.jake.formulaschedule;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Jake on 3/7/2017.
 */

public class SessionNotification extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        String country, session;
        SharedPreferences pref = context.getSharedPreferences(context.getString(R.string.Shared_Pref_Key), Context.MODE_PRIVATE);
        country = pref.getString(context.getString(R.string.Shared_Pref_Country), "ERROR");
        session = pref.getString(context.getString(R.string.Shared_Pref_Session), "ERROR");


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.australia)
                .setContentTitle(session + " is starting soon!")
                .setContentText(session + " in " + country + " is about to begin!");

        NotificationManager nManger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManger.notify(0, mBuilder.build());

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}

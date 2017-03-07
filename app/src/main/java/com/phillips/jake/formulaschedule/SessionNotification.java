package com.phillips.jake.formulaschedule;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Jake on 3/7/2017.
 */

public class SessionNotification extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.australia)
                .setContentTitle("A session is starting soon!")
                .setContentText("A session is about to start!");

        NotificationManager nManger = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManger.notify(0, mBuilder.build());
    }
}

package amen.clock;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;


import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by Andrew on 11/9/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 112211;

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getExtras().getString("message");
        if(msg == "")
            msg = "BEEP! BEEP!";

        notification = new  NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.clock);
        notification.setTicker("Test");
        notification.setContentTitle("Alarm Clock!");
        notification.setContentText(msg);
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setPriority(PRIORITY_HIGH);

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
    }
}

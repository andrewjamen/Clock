package amen.clock;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v7.app.NotificationCompat;
//import android.widget.Toast;
//import android.os.Vibrator;


import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;


/**
 * Created by Andrew on 11/9/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    SetAlarm alarm;
    NotificationCompat.Builder notification;
    private static final int uniqueID = 112211;


    @Override
    public void onReceive(Context context, Intent intent) {

        notification = new  NotificationCompat.Builder(context);
        notification.setAutoCancel(true);

        notification.setSmallIcon(R.drawable.clock);
        notification.setTicker("Test");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Alarm Clock!");
        notification.setContentText("Test");
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setPriority(PRIORITY_HIGH);


        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(context, 0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent2);

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

        /*Toast.makeText(context, alarm.getMessage(), Toast.LENGTH_LONG).show();
        // Vibrate
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        intent = new Intent(context, MainActivity.class);

        context.startService(intent);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);*/
    }
}

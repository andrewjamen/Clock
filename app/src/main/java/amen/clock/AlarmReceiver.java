package amen.clock;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v7.app.NotificationCompat;
import android.os.Vibrator;
import android.app.Service;
import android.support.v7.app.NotificationCompat;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;


import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;


/**
 * Created by Andrew on 11/9/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 112211;


    @Override
    public void onReceive(Context context, Intent intent) {

        double latitude = 40.508625 ;
        double longitude =-88.989027;

        String msg = intent.getExtras().getString("message");

        notification = new  NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.clock);
        notification.setTicker("Test");
        //notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(msg);
        notification.setContentText("319 West North Street");
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setPriority(PRIORITY_HIGH);

        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());

        //Intent service1 = new Intent(context, AlarmManager.class);
        //context.startService(service1);

        // Vibrate
        //Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(2000);



    }
}

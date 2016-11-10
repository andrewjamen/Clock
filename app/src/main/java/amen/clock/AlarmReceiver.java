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


        Intent service1 = new Intent(context, AlarmManager.class);
        context.startService(service1);

        // Vibrate
        //Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(2000);

    }
}

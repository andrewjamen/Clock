package amen.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.Toast;
import android.os.Vibrator;


/**
 * Created by Andrew on 11/9/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {

    SetAlarm alarm;

    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, alarm.getMessage(), Toast.LENGTH_LONG).show();
        // Vibrate
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        intent = new Intent(context, MainActivity.class);

        context.startService(intent);

        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
    }
}

package amen.clock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import static android.app.Notification.PRIORITY_HIGH;

public class TimerCountDown extends AppCompatActivity {

    TextView time;
    //Start notification
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    //End Notification

    Calendar rightNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_count_down);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        long hour = extras.getInt("Hour");
        long min = extras.getInt("Minute");
        long sec = extras.getInt("Seconds");
        final String userMessage = extras.getString("userMsg");

        rightNow = Calendar.getInstance();

        long totalTime = ( (sec*1000) + hour*3600000 + min*60000);

        rightNow.set(Calendar.HOUR, (int)hour);
        rightNow.set(Calendar.MINUTE, (int)min);
        rightNow.set(Calendar.SECOND, (int)sec);



        Log.i("Time " + totalTime, "milliseconds");

        //notification
        notification = new  NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

       time = (TextView) findViewById(R.id.timer);

        new CountDownTimer(totalTime, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText("Time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                time.setText("done!");

        //Build the notification
        notification.setSmallIcon(R.drawable.clock);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle(userMessage);
        notification.setContentText("Click here to go to app.");
        notification.setDefaults(Notification.DEFAULT_ALL);
        notification.setPriority(PRIORITY_HIGH);


        Intent intent2 = new Intent(TimerCountDown.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(TimerCountDown.this, 0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it to the device
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID, notification.build());
            }
        }.start();


    }



}

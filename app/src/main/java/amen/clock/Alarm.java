package amen.clock;

/**
 * Created by Andrew on 11/8/2016.
 */
import android.location.Location;



public class Alarm {

    private double time;
    private String message;
    private boolean[] rptDay;
    private Location location;



    public Alarm(double time, String message, boolean[] rptDay, Location location){
        this.time = time;
        this.message = message;
        this.rptDay = rptDay;
        this.location = location;
    }


    public Alarm(double time, String message, Location location){
        this.time = time;
        this.message = message;
        this.location = location;
    }

    public Alarm(double time, Location location){
        this.time = time;
        this.location = location;
    }

//    void setAlarm()
//    {
//        PendingIntent alarmIntent;
//        AlarmManager alarmMgr  = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(context,AlarmDisplay.class);
//        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
//        alarmManager.setExact(AlarmManager.RTC,time,alarmIntent);
//        //https://developer.android.com/reference/android/app/AlarmManager.html
//    }





}

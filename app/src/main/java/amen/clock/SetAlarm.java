package amen.clock;
//https://developer.android.com/training/scheduling/alarms.html

/**
 * Created by Andrew on 11/9/2016.
 */

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.location.Criteria;
import android.widget.Toast;


public class SetAlarm extends Activity {


    //Set globals
    private TimePicker timePicker;
    private DatePicker datePicker;
    private EditText alarmMessage;
    public String alarmMessageText;
    private int timeZoneSelection;
    private boolean sundayCB, mondayCB, tuesdayCB, wednesdayCB, thursdayCB, fridayCB, saturdayCB;
    private AlarmManager alarmManager;
    private CheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    private Spinner timeZoneSpinner;
    private LocationManager locationMgr;
    private Location location;
    private Criteria criteria;
    private String provider;
    private Intent intent;
    private PendingIntent pendingIntent;
    public Button setAlarmB;
    private long alarmTime;
    public int hour;
    public int minute;

    //On Create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);



        setAlarmB = (Button) findViewById(R.id.setAlarmButton);

        //Set spinners and stuff
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        alarmMessage = (EditText) findViewById(R.id.alarmMessage);
        timeZoneSpinner = (Spinner) findViewById(R.id.timeZoneSpinner);
        sunday = (CheckBox) findViewById(R.id.sundayCheckBox);
        monday = (CheckBox) findViewById(R.id.mondayCheckBox);
        tuesday = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        wednesday = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        thursday = (CheckBox) findViewById(R.id.thursdayCheckBox);
        friday = (CheckBox) findViewById(R.id.fridayCheckBox);
        saturday = (CheckBox) findViewById(R.id.saturdayCheckBox);

        fillZones();

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        //Sets Time and date picker default values to current time
        if(Build.VERSION.SDK_INT >= 23){
            timePicker.setHour(cal.get(Calendar.HOUR));
            timePicker.setMinute(cal.get(Calendar.MINUTE));
        }
        else{
            timePicker.setCurrentHour(cal.get(Calendar.HOUR));
            timePicker.setCurrentMinute(cal.get(Calendar.MINUTE));
        }

        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH), null);

        //On click listener to button
        setAlarmB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.e("onClick called", "");

                //Sets Time and date picker default values to current time
                if(Build.VERSION.SDK_INT >= 23){
                    //get inputs from user
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }
                else {
                    //get inputs from user
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }

                int month = datePicker.getMonth();
                int dayOfYr = datePicker.getDayOfMonth();
                int year = datePicker.getYear();
                alarmMessageText = alarmMessage.getText().toString();

                Log.e("Month: " +month, "");
                Log.e("dayOfYr: " +dayOfYr, "");
                Log.e("year: " +year, "");

                //Convert time/date into a time in milliseconds
                Calendar alarmCal = Calendar.getInstance();
                alarmCal.set(Calendar.MONTH, month);
                alarmCal.set(Calendar.YEAR, year);
                alarmCal.set(Calendar.DAY_OF_MONTH, dayOfYr);
                alarmCal.set(Calendar.HOUR_OF_DAY, hour);
                alarmCal.set(Calendar.MINUTE, minute);
                alarmCal.set(Calendar.SECOND, 0);
                /*if (hour > 12){
                    alarmCal.set(Calendar.AM_PM, Calendar.AM);
                }
                if (hour < 12 ){
                    alarmCal.set(Calendar.AM_PM, Calendar.PM);
                }*/

                alarmTime = alarmCal.getTimeInMillis();

                //get timezone selection (not exactly sure how this output will look, haven't tested)
                timeZoneSelection = timeZoneSpinner.getSelectedItemPosition();

                if(timeZoneSelection != 5)
                    alarmTime = timeZone(timeZoneSelection, alarmTime);

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.MONTH, 11);
                calendar.set(Calendar.YEAR, 2016);
                calendar.set(Calendar.DAY_OF_MONTH, 10);
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 30);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.AM_PM, Calendar.AM);

                Log.e("alarmTime: ", alarmTime+"");

                intent = new Intent(SetAlarm.this, AlarmReceiver.class);
                intent.putExtra("message",alarmMessageText);

                pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, (long)alarmTime, pendingIntent);

                //get repeat days
                boolean repeated = false;
                String repeatedString = "";
                if (sunday.isChecked()) {
                    sundayCB = true;
                    repeatedString += "Sunday. ";
                    repeated = true;
                }
                else
                    sundayCB = false;

                if (monday.isChecked()) {
                    mondayCB = true;
                    repeatedString += "Monday. ";
                    repeated = true;
                }
                else
                    mondayCB = false;

                if (tuesday.isChecked()) {
                    tuesdayCB = true;
                    repeatedString += "Tuesday. ";
                    repeated = true;
                }
                else
                    tuesdayCB = false;

                if (wednesday.isChecked()) {
                    wednesdayCB = true;
                    repeatedString += "Wednesday. ";
                    repeated = true;
                }
                else
                    wednesdayCB = false;

                if (thursday.isChecked()) {
                    thursdayCB = true;
                    repeatedString += "Thursday. ";
                    repeated = true;
                }
                else
                    thursdayCB = false;

                if (friday.isChecked()) {
                    fridayCB = true;
                    repeatedString += "Friday. ";
                    repeated = true;
                }
                else
                    fridayCB = false;

                if (saturday.isChecked()) {
                    saturdayCB = true;
                    repeatedString += "Saturday.";
                    repeated = true;
                }
                else
                    saturdayCB = false;

                boolean[] rptDays = {sundayCB, mondayCB, tuesdayCB, wednesdayCB, thursdayCB, fridayCB, saturdayCB};

                String toastMsg;

                if(repeated)
                {
                    toastMsg = "Alarm set for " +(month+1) + "/" + dayOfYr + "/" + year + "\n Repeated every " +repeatedString;
                }
                else
                    toastMsg = "Alarm set for " +(month+1) +"/" +dayOfYr +"/" +year;

                Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();

                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }


        });
    }


    public long timeZone(int selection, long timely){

        long hrDif = 0;

        long convert = (long) (3.6 * Math.pow(10, 6));

            switch (selection) {
                case 0:
                    hrDif = -5 * convert;
                    break;
                case 1:
                    hrDif = -4 * convert;
                    break;
                case 2:
                    hrDif = -3 * convert;
                    break;
                case 3:
                    hrDif = -2 * convert;
                    break;
                case 4:
                    hrDif = -1 * convert;
                    break;
                case 6:
                    hrDif = 1 * convert;
                    break;
                case 7:
                    hrDif = 2 * convert;
                    break;
                case 8:
                    hrDif = 3 * convert;
                    break;
                case 9:
                    hrDif = 4 * convert;
                    break;
                case 10:
                    hrDif = 5 * convert;
                    break;
                case 11:
                    hrDif = 6 * convert;
                    break;
                case 12:
                    hrDif = 7 * convert;
                    break;
                case 13:
                    hrDif = 8 * convert;
                    break;
                case 14:
                    hrDif = 9 * convert;
                    break;
                case 15:
                    hrDif = 10 * convert;
                    break;
                case 16:
                    hrDif = 11 * convert;
                    break;
                case 17:
                    hrDif = 12 * convert;
                    break;
                case 18:
                    hrDif = 13 * convert;
                    break;
                case 19:
                    hrDif = 14 * convert;
                    break;
                case 20:
                    hrDif = 15 * convert;
                    break;
                case 21:
                    hrDif = 16 * convert;
                    break;
                case 22:
                    hrDif = 17 * convert;
                    break;
                case 23:
                    hrDif = 18 * convert;
                    break;
                default:
                    break;
            }

        timely = timely + hrDif;
        Log.e("" +String.valueOf(timely),"");
        return timely;
    }

    public void fillZones(){

        //This fills the timezone spinner with all the timezones
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> TZ = new ArrayList<>();

        TZ.add("Nieu Time");    //GMT-11
        TZ.add("Hawaiian Standard Time"); //GMT-10
        TZ.add("Alaskan Standard Time");
        TZ.add("Pacific Standard Time");
        TZ.add("Mountain Standard Time");
        TZ.add("Central Standard Time"); //GMT-6
        TZ.add("Eastern Standard Time");
        TZ.add("Atlantic Standard Time"); //GMT-4
        TZ.add("West Greenland Time");  //GMT-3
        TZ.add("South Georgia Time");
        TZ.add("East Greenland Time");
        TZ.add("Greenwich Mean Time"); //GMT
        TZ.add("Central European Time");    //GMT+01
        TZ.add("Eastern European Time");
        TZ.add("Moscow Standard Time");
        TZ.add("Gulf Standard Time");   //GMT+4
        TZ.add("Yekaterinburg Standard Time");
        TZ.add("Bangladesh Standard Time");
        TZ.add("Krasnoyarsk Time");
        TZ.add("China Standard Time");    //GMT+8
        TZ.add("Japan Standard Time");  //GMT+9
        TZ.add("Papua New Guinea Time");
        TZ.add("Pohnpei Standard Time");    //GMT+11
        TZ.add("Tuvalu Time");  //GMT+12

        for (int i = 0; i < TZ.size(); i++) {
            adapter.add(TZ.get(i));
        }

        timeZoneSpinner.setAdapter(adapter);
        timeZoneSpinner.setSelection(5);
    }

    public void locationBS(){
        // Get the location manager
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Define the criteria how to select the location provider
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationMgr.getBestProvider(criteria, false);

        //Auto generated permission check start
        if (ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SetAlarm.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //Auto generated permission check end

        location = locationMgr.getLastKnownLocation(provider);
    }




}

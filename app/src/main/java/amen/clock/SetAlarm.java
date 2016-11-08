package amen.clock;
//https://developer.android.com/training/scheduling/alarms.html
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;



public class SetAlarm extends AppCompatActivity {

    //Set globals
    TimePicker timePicker;
    DatePicker datePicker;
    EditText alarmMessage;
    String alarmMessageText;
    int timeZoneSelection;
    boolean sundayCB, mondayCB, tuesdayCB, wednesdayCB, thursdayCB, fridayCB, saturdayCB;
    AlarmManager alarmManager;
    CheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    Spinner timeZoneSpinner;
    LocationManager locationMgr;
    Location location;


    //On Create method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        //Set spinners and stuff
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); //Already there
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        alarmMessage = (EditText) findViewById(R.id.alarmMessage);
        timeZoneSpinner = (Spinner) findViewById(R.id.timeZoneSpinner);
        Button setAlarm = (Button) findViewById(R.id.setAlarmButton);


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


        for(int i = 0; i < TZ.size(); i++) {
            adapter.add(TZ.get(i));
        }

        timeZoneSpinner.setAdapter(adapter);

        timeZoneSpinner.setSelection(5);




        //Sets Time and date picker default values to current time
        Calendar cal = Calendar.getInstance();
        timePicker.setHour(cal.get(Calendar.HOUR));
        timePicker.setMinute(cal.get(Calendar.MINUTE));
        datePicker.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),null);

        //On click listener to button
        setAlarm.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                //get inputs from user
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                int month = datePicker.getMonth();
                int dayOfYr = datePicker.getDayOfMonth();
                int year = datePicker.getYear();


                //Convert time/date into a time in milliseconds
                Calendar alarmCal = Calendar.getInstance();
                alarmCal.set(year,month,dayOfYr,hour,minute,0);
                double alarmTime = alarmCal.getTimeInMillis();


                //get alarm message
                alarmMessageText = alarmMessage.getText().toString();

                //get repeat days
                if(sunday.isChecked())
                    sundayCB = true;
                else
                    sundayCB = false;

                if(monday.isChecked())
                    mondayCB = true;
                else
                    mondayCB = false;

                if(tuesday.isChecked())
                    tuesdayCB = true;
                else
                    tuesdayCB = false;

                if(wednesday.isChecked())
                    wednesdayCB = true;
                else
                    wednesdayCB = false;

                if(thursday.isChecked())
                    thursdayCB = true;
                else
                    thursdayCB = false;

                if(friday.isChecked())
                    fridayCB = true;
                else
                    fridayCB = false;

                if(saturday.isChecked())
                    saturdayCB = true;
                else
                    saturdayCB = false;
                //Save repeats days into boolean array, sunday - saturday
                boolean[] rptDays = {sundayCB,mondayCB,tuesdayCB,wednesdayCB,thursdayCB,fridayCB,saturdayCB};

                //get timezone selection (not exactly sure how this output will look, haven't tested)
                timeZoneSelection = timeZoneSpinner.getSelectedItemPosition();

                double hrDif = 0;

                double convert = Math.pow((3.6*10),6);

                if (timeZoneSelection != 5){

                    switch (timeZoneSelection){
                        case 0: hrDif = -5 * convert;
                            break;
                        case 1: hrDif = -4 *convert;
                            break;
                        case 2: hrDif = -3 *convert;
                            break;
                        case 3: hrDif = -2 *convert;
                            break;
                        case 4: hrDif = -1 *convert;
                            break;
                        case 6: hrDif = 1 *convert;
                            break;
                        case 7: hrDif = 2 *convert;
                            break;
                        case 8: hrDif = 3 *convert;
                            break;
                        case 9: hrDif = 4 *convert;
                            break;
                        case 10: hrDif = 5 *convert;
                            break;
                        case 11: hrDif = 6 *convert;
                            break;
                        case 12: hrDif = 7 *convert;
                            break;
                        case 13: hrDif = 8 *convert;
                            break;
                        case 14: hrDif = 9 *convert;
                            break;
                        case 15: hrDif = 10 *convert;
                            break;
                        case 16: hrDif = 11 *convert;
                            break;
                        case 17: hrDif = 12 *convert;
                            break;
                        case 18: hrDif = 13 *convert;
                            break;
                        case 19: hrDif = 14 *convert;
                            break;
                        case 20: hrDif = 15 *convert;
                            break;
                        case 21: hrDif = 16 *convert;
                            break;
                        case 22: hrDif = 17 *convert;
                            break;
                        case 23: hrDif = 18 *convert;
                            break;
                    }

                    alarmTime = alarmTime + hrDif;

                }

                //get location of phone
                locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                //location = locationMgr.getLastKnownLocation(/*provider*/);

                Alarm alarm = new Alarm(alarmTime, alarmMessageText, rptDays, location);

            }


        });





    }

}

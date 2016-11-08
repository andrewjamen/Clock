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
    String timeZoneSelection;
    boolean sundayCB, mondayCB, tuesdayCB, wednesdayCB, thursdayCB, fridayCB, saturdayCB;
    AlarmManager alarmManager;
    CheckBox sunday, monday, tuesday, wednesday, thursday, friday, saturday;
    Spinner timeZoneSpinner;
    LocationManager location;

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
        //http://stackoverflow.com/questions/3407582/launch-time-zone-list-in-android-for-pick-result
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String[]TZ = TimeZone.getAvailableIDs();
        ArrayList<String> TZ1 = new ArrayList<>();
        for(int i = 0; i < TZ.length; i++) {
            if(!(TZ1.contains(TimeZone.getTimeZone(TZ[i]).getDisplayName()))) {
                TZ1.add(TimeZone.getTimeZone(TZ[i]).getDisplayName());
            }
        }
        for(int i = 0; i < TZ1.size(); i++) {
            adapter.add(TZ1.get(i));
        }

        timeZoneSpinner.setAdapter(adapter);

        for(int i = 0; i < TZ1.size(); i++) {
            if(TZ1.get(i).equals(TimeZone.getDefault().getDisplayName())) {
                timeZoneSpinner.setSelection(i);
            } }

        //Sets Time and date picker default values to current time
        Calendar cal = Calendar.getInstance();
        timePicker.setHour(cal.get(Calendar.HOUR));
        timePicker.setMinute(cal.get(Calendar.MINUTE));
        datePicker.init(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),null);

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
                long alarmTime = alarmCal.getTimeInMillis();

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
                timeZoneSelection = timeZoneSpinner.getSelectedItem().toString();

                //get location of phone
                location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //send to setAlarm1 method. This could probably just go down in here, if it isn't too much code.
                //Parameters of this method are prety straight forward (look at method header)
                setAlarm1(alarmTime,alarmMessageText,rptDays,timeZoneSelection,location);
            }


        });





    }

    void setAlarm1(long time, String message, boolean[] repeatDay, String timezone, LocationManager loc)
    {
        PendingIntent alarmIntent;
        AlarmManager alarmMgr  = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmDisplay.class);
        alarmIntent = PendingIntent.getBroadcast(context,0,intent,0);
        alarmManager.setExact(AlarmManager.RTC,time,alarmIntent);
        //https://developer.android.com/reference/android/app/AlarmManager.html
    }
}

package amen.clock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class LocationAlarm extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner timer = (Spinner) findViewById(R.id.spinner1);

        timer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Spinner t = (Spinner) findViewById(R.id.spinner1);
                Toast.makeText(parent.getContext(), "Inactivity timer set to " +parent.getItemAtPosition(pos) ,Toast.LENGTH_SHORT).show();
                alarm(parent.getSelectedItemPosition());
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.inactivity_Timer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timer.setAdapter(adapter);

        int defaultTime = adapter.getPosition("2 Minutes");
        timer.setSelection(defaultTime);
    }

    private void alarm(int timer){
        AlarmManager alm;
        Intent in;
        PendingIntent alarmIntent;
        Spinner t;

        alm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        in = new Intent(LocationAlarm.this, AlarmReceiver.class);
        t = (Spinner) findViewById(R.id.spinner1);
        alarmIntent = PendingIntent.getActivity(this, 000001, in, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 15);//set 15 to the timer add a method that changes timer to the correct time in seconds.

        alm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
    }
}
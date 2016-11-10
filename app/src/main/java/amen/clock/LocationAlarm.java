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

import java.util.ArrayList;
import java.util.Calendar;


public class LocationAlarm extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);


        final Spinner timer = (Spinner) findViewById(R.id.spinner1);

        timer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                int selected = parent.getSelectedItemPosition() + 1;
                Toast.makeText(parent.getContext(), "Inactivity timer set to " +selected +" minutes." ,Toast.LENGTH_SHORT).show();
                selected = selected * 60;
                alarm(selected);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> arrayL = new ArrayList<>();

        arrayL.add("1");
        arrayL.add("2");
        arrayL.add("3");
        arrayL.add("4");
        arrayL.add("5");
        arrayL.add("6");
        arrayL.add("7");
        arrayL.add("8");
        arrayL.add("9");
        arrayL.add("10");

        for (int i = 0; i < arrayL.size(); i++) {
            adapter.add(arrayL.get(i));
        }

        timer.setAdapter(adapter);
        timer.setSelection(0);
    }

    private void alarm(int timer){
        AlarmManager alm;
        Intent in;
        PendingIntent alarmIntent;

        alm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        in = new Intent(LocationAlarm.this, LocationReceiver.class);
        alarmIntent = PendingIntent.getActivity(this, 000001, in, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);//set to 5 to test

        //alm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
    }
}
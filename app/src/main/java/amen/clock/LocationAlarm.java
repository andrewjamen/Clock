package amen.clock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


public class LocationAlarm extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);


        final Spinner timer = (Spinner) findViewById(R.id.spinner1);

        timer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                //Spinner t = (Spinner) findViewById(R.id.spinner1);
                int selection = (int) parent.getSelectedItemPosition() + 1;
                selection = selection * 60;
                Toast.makeText(parent.getContext(), "Inactivity timer set to " +selection +" seconds." ,Toast.LENGTH_SHORT).show();
                alarm(selection);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.inactivity_Timer, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
        adapter.add("5");
        adapter.add("6");
        adapter.add("7");
        adapter.add("8");
        adapter.add("9");
        adapter.add("10");
        adapter.add("20");
        adapter.add("30");
        adapter.add("60");

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
        in = new Intent(LocationAlarm.this, LocationReceiver.class);
        //t = (Spinner) findViewById(R.id.spinner1);
        alarmIntent = PendingIntent.getActivity(this, 000001, in, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, timer);

        alm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
    }
}
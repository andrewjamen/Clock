package amen.clock;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import android.os.CountDownTimer;

import org.w3c.dom.Text;


public class LocationAlarm extends AppCompatActivity{


    Intent intent;
    AlarmManager alarmMgr;
    PendingIntent pendingIntent;
    int i = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);


        final Spinner timer = (Spinner) findViewById(R.id.spinner1);

        final TextView countDown = (TextView) findViewById(R.id.countDown);

        timer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {


            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {

                int selected = parent.getSelectedItemPosition();

                if (selected == 0){
                    return;
                }

                Toast.makeText(parent.getContext(), "Inactivity timer set to " +selected +" minutes." ,Toast.LENGTH_LONG).show();
                selected = selected * 60;
                alarm(selected);
                CountDownTimer ctr = new CountDownTimer(selected * 1000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        countDown.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        countDown.setText("done!");
                    }
                }.start();

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> arrayL = new ArrayList<>();

        arrayL.add("");
        arrayL.add("1 minute");
        arrayL.add("2 minutes");
        arrayL.add("3 minutes");
        arrayL.add("4 minutes");
        arrayL.add("5 minutes");
        arrayL.add("6 minutes");
        arrayL.add("7 minutes");
        arrayL.add("8 minutes");
        arrayL.add("9 minutes");
        arrayL.add("10 minutes");

        for (int i = 0; i < arrayL.size(); i++) {
            adapter.add(arrayL.get(i));
        }

        timer.setAdapter(adapter);

    }

    private void alarm(int userTimer){
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(this, LocationReceiver.class);
        pendingIntent = PendingIntent.getActivity(this, 000001, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, userTimer);//set to 5 to test

        alarmMgr.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

    }
}
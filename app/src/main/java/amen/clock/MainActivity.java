package amen.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

// TODO:    onCreate() vs onStart() vs onResume()
public class MainActivity extends AppCompatActivity {

    public Button setAlarms;
    public Button timer;
    public Button locationAlarm;
    public Button alarmList;

    public void activitySwitch() {


        setAlarms = (Button) findViewById(R.id.setAlarms);
        timer = (Button) findViewById(R.id.setTimer);
        locationAlarm = (Button) findViewById(R.id.locationAlarm);



        setAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SetAlarm.class);
                startActivity(intent);
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Timer.class);
                startActivity(intent);
            }
        });
        locationAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, LocationAlarm.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activitySwitch();
    }



}

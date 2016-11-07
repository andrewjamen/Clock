package amen.clock;

import java.util.Calendar;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;



public class SetAlarm extends AppCompatActivity {

    TimePicker timePicker;

    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        final Calendar calendar = Calendar.getInstance();

        timePicker.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                calendar.set(Calendar.MINUTE, timePicker.getMinute());



            }


        });





    }
}

package amen.clock;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class SetTimer extends Activity {

    EditText hoursET;
    EditText minutesET;
    EditText secondsET;
    EditText notificationMsgET;

    String hours;
    String minutes;
    String seconds;

    int hoursInt = 0;
    int minutesInt = 0;
    int secondsInt = 0;
    String userMessage = "";

    TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

    }

    public void btnClicked(View view){

        //creating EditText objects
        hoursET = (EditText) findViewById(R.id.hours);
        minutesET = (EditText) findViewById(R.id.minutes);
        secondsET = (EditText) findViewById(R.id.seconds);
        notificationMsgET = (EditText) findViewById(R.id.notificationMsg);

        //converting EditText to a string
        hours = hoursET.getText().toString();
        minutes = minutesET.getText().toString();
        seconds = secondsET.getText().toString();

        //Converting Hours, mins and secs to integers.
        userMessage = notificationMsgET.getText().toString();
        hoursInt = Integer.parseInt(hours);
        minutesInt = Integer.parseInt(minutes);
        secondsInt = Integer.parseInt(seconds);



        //Testing user input.
        Log.i("Hours: " + hoursInt, "hours" );
        Log.i("Minutes: " + minutesInt, "minutes");
        Log.i("Seconds: " + seconds, "seconds");
        Log.i("Message: ", userMessage);



        Intent countDownTimerActivity = new Intent(this, TimerCountDown.class);
        Bundle extras = new Bundle();
        extras.putInt("Hour", hoursInt);
        extras.putInt("Minute", minutesInt);
        extras.putInt("Seconds", secondsInt);
        extras.putString("userMsg", userMessage);
        countDownTimerActivity.putExtras(extras);
        startActivity(countDownTimerActivity);
    }

}



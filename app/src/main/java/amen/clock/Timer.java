package amen.clock;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Timer extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
    }

    public void btnClicked(View view){
        //creating EditText objects
        hoursET = (EditText) findViewById(R.id.hours);
        minutesET = (EditText) findViewById(R.id.minutes);
        secondsET = (EditText) findViewById(R.id.seconds);
        notificationMsgET = (EditText) findViewById(R.id.message);

        //converting EditText to a string
        hours = hoursET.getText().toString();
        minutes = minutesET.getText().toString();
        seconds = secondsET.getText().toString();

        //Converting Hours, minutes and secs to integers.
        userMessage = notificationMsgET.getText().toString();
        hoursInt = Integer.parseInt(hours);
        minutesInt = Integer.parseInt(minutes);
        secondsInt = Integer.parseInt(seconds);

        //Testing user input.
        Log.i("Hours: " + hoursInt, "hours" );
        Log.i("Minutes: " + minutesInt, "minutes");
        Log.i("Seconds: " + secondsInt, "seconds");
        Log.i("Message: ", userMessage);

        Intent countDown = new Intent(this, CountDown.class);
        countDown.putExtra("hours", hoursInt);
        countDown.putExtra("minutes", minutesInt);
        countDown.putExtra("seconds", secondsInt);
        countDown.putExtra("message", userMessage);
        startActivity(countDown);


    }
}

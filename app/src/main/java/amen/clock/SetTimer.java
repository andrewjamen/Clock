package amen.clock;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.Manifest;
import android.widget.TextView;



public class SetTimer extends Activity implements LocationListener {

    LocationManager locationManager;
    Location location;
    String mprovider;
    double latitude;
    double longitude;

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
        setContentView(R.layout.activity_set_timer);
    }

    public void btnClicked(View view){

        //Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
             location = locationManager.getLastKnownLocation(mprovider);

            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

        }

        //Testing the GPS Location
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.i("Latitude: "+ latitude, "degrees");

        locationManager.removeUpdates(this); //Stops searching for location


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


        //Saving data to send to another activity.
        Intent countDownTimerActivity = new Intent(this, TimerCountDown.class);
        Bundle extras = new Bundle();
        extras.putDouble("latitude", latitude);
        extras.putDouble("longitude", longitude);
        extras.putInt("Hour", hoursInt);
        extras.putInt("Minute", minutesInt);
        extras.putInt("Seconds", secondsInt);
        extras.putString("userMsg", userMessage);
        countDownTimerActivity.putExtras(extras);
        startActivity(countDownTimerActivity);
    }












    //Implements LocationListener methods::: IGNORE ::::

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}



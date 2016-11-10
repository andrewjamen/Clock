package amen.clock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.CountDownTimer;
import android.renderscript.RenderScript;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import static android.app.Notification.PRIORITY_HIGH;

public class CountDown extends AppCompatActivity implements LocationListener {



    //LOCATION
//    LocationManager locationManager;
//    Location location;
//    String mprovider;
//    double latitude;
//    double longitude;
    //LOCATION








    //Start notification
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    TextView time;
    int hours, minutes, seconds;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);













        //Location
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        Criteria criteria = new Criteria();
//        mprovider = locationManager.getBestProvider(criteria, false);
//        if (mprovider != null && !mprovider.equals("")) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            location = locationManager.getLastKnownLocation(mprovider);
//            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);
//        }
//        //Testing the GPS Location
//
//        latitude = location.getLatitude();
//        Log.i("latitude" + latitude, "");
//        longitude = location.getLongitude();
//        locationManager.removeUpdates(this); //Stops searching for location
        //Location
















        //notification
        notification = new  NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            hours = extras.getInt("hours");
            minutes = extras.getInt("minutes");
            seconds = extras.getInt("seconds");
            message = extras.getString("message");
        } else{
            Intent i = new Intent(this, Timer.class);
            startActivity(i);
        }

           /*
            For Testing:
           Log.e("hours " + hours, "");
            Log.e("mins " + minutes, "");
            Log.e("seconds " + seconds, "");
            Log.e("message  " + message, "");
           */

        long totalTime = ( (seconds*1000) + hours*3600000 + minutes*60000 );
        Log.i("Time: " + totalTime, "");

        time = (TextView) findViewById(R.id.timer);

        new CountDownTimer(totalTime, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(millisUntilFinished / 1000 + " seconds left");
            }

            public void onFinish() {
                time.setText("done!");
                //build the notification
                notification.setSmallIcon(R.drawable.clock);
                notification.setTicker("This is a ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Timers Up!");
                notification.setContentText(message);
                notification.setDefaults(Notification.DEFAULT_ALL);
                notification.setPriority(PRIORITY_HIGH);

                Intent takeHome = new Intent(CountDown.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(CountDown.this, 0, takeHome,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID, notification.build());
            }
        }.start();
    }

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


    /*
    Gets address in shipping address form.
     */
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("Current loc of address", "" + strReturnedAddress.toString());
            } else {
                Log.w("Current loc of address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Current loc address", "Cannot get Address!");
        }
        return strAdd;
    }


}

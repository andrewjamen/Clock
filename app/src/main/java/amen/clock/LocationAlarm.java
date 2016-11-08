package amen.clock;

import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Location;
import android.content.Context;

public class LocationAlarm extends AppCompatActivity {


    LocationManager locationMgr;

    Criteria criteria;

    Location location;

    /*      implement LocationListener
    Override the methods-
        onLocationChanged
        onStatusCHanged
        ProviderEnabled
        ProviderDisabled    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);


        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationMgr.getLastKnownLocation(/*provider*/);



        //  criteria = new Criteria();
        //  locaitonManager.requestLocationUpdates();
        // locationManager.addProximityAlert();
        //  locationManager.re



    }
}

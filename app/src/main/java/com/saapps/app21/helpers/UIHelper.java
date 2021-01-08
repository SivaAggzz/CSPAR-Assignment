package com.saapps.app21.helpers;

import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationRequest;

public class UIHelper {
    private final Context context;

    public UIHelper(Context context) {
        this.context = context;
    }

    public boolean isPlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);
        return ConnectionResult.SUCCESS == status;
    }


    public LocationRequest getLocationRequest() {
        //request new location every 300 seconds i.e. 5 mins
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(300000);
        locationRequest.setFastestInterval(300000);
        return locationRequest;
    }

}

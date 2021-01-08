package com.saapps.app21.helpers;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.saapps.app21.R;
import com.saapps.app21.models.LocationObj;

import java.util.List;

public class GoogleMapHelper {
    private GoogleMap googleMap;

    public GoogleMapHelper(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void defaultMapSettings() {
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setBuildingsEnabled(false);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(false);
    }


    public void addLocationsToMap(List<LocationObj> locationObjs) {
        for (LocationObj locationObj:locationObjs){
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(locationObj.getLat(),locationObj.getLongg()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_location))
            );
        }
    }
}

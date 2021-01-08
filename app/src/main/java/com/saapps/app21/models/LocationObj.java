package com.saapps.app21.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationObj {
    private double lat;
    private double longg;

    public LocationObj() {
    }

    public static LocationObj createLocationObjectFromJSON(JSONObject profileJSONObj) throws JSONException {
        LocationObj locationObj = new LocationObj();
        locationObj.setLat(profileJSONObj.getDouble("lat"));
        locationObj.setLongg(profileJSONObj.getDouble("longg"));
        return locationObj;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongg() {
        return longg;
    }

    public void setLongg(double longg) {
        this.longg = longg;
    }
}

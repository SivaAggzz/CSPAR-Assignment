package com.saapps.app21.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.saapps.app21.api.APIClient;
import com.saapps.app21.models.LocationObj;
import com.saapps.app21.models.ProfileObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeScreenViewModel extends ViewModel {
    private APIClient apiClient;

    private MutableLiveData<ArrayList<LocationObj>> locationMutableLiveData;
    private MutableLiveData<ArrayList<ProfileObj>> profilesMutableLiveData;


    public HomeScreenViewModel() {
        super();
        apiClient = new APIClient();
    }

    public MutableLiveData<ArrayList<LocationObj>> getLocationDataFromAPI() {
        ArrayList<LocationObj> locationObjs = new ArrayList<>();

        if (locationMutableLiveData == null) {
            locationMutableLiveData = new MutableLiveData<>();
        }

        apiClient.getUserLocations(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //getting user locations to plot on Map
                try {
                    JSONArray locationArray = response.getJSONArray("location");
                    for (int i = 0; i < locationArray.length(); i++) {
                        JSONObject locationJSONObj = locationArray.getJSONObject(i);
                        LocationObj locationObj = LocationObj.createLocationObjectFromJSON(locationJSONObj);
                        locationObjs.add(locationObj);
                    }
                    locationMutableLiveData.postValue(locationObjs);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return locationMutableLiveData;
    }

    public MutableLiveData<ArrayList<ProfileObj>> getProfileDataFromAPI() {
        ArrayList<ProfileObj> profileObjs = new ArrayList<>();
        if (profilesMutableLiveData == null) {
            profilesMutableLiveData = new MutableLiveData<>();
        }

        apiClient.getUserLocations(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //getting user profiles
                try {
                    JSONArray profileArray = response.getJSONArray("Success");
                    for (int i = 0; i < profileArray.length(); i++) {
                        JSONObject profileJSONObj = profileArray.getJSONObject(i);
                        ProfileObj profileObj=ProfileObj.createProfileFromJSON(profileJSONObj);
                        profileObjs.add(profileObj);
                    }
                    profilesMutableLiveData.postValue(profileObjs);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return profilesMutableLiveData;
    }


    //todo - isInternetConnected();
}

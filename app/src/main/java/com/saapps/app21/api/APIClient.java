package com.saapps.app21.api;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class APIClient {
    private AsyncHttpClient client;


    public APIClient() {
        this.client = new AsyncHttpClient();
    }


    public void getUserLocations(JsonHttpResponseHandler handler) {
        //used for accessing homepage
        String API_BASE_URL = "https://aryu.co.in/tracking/viewreport.php";
        client.get(API_BASE_URL, handler);
    }

}

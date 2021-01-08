package com.saapps.app21.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.tasks.Task;
import com.saapps.app21.R;
import com.saapps.app21.databinding.FragmentHomeBinding;
import com.saapps.app21.helpers.CommonMethods;
import com.saapps.app21.helpers.GoogleMapHelper;
import com.saapps.app21.helpers.UIHelper;
import com.saapps.app21.viewmodel.HomeScreenViewModel;

import java.util.Objects;

public class HomeFragment extends Fragment {
    private UIHelper uiHelper;
    private GoogleMapHelper googleMapHelper;

    private LatLng currentLatLng;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;

    private GoogleMap currentMapView;
    private HomeScreenViewModel homeScreenViewModel;


    public static final int FINE_LOCATION_REQ_CODE = 299;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    //private GoogleApiClient googleApiClient;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initializeMethods();
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == FINE_LOCATION_REQ_CODE) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeMapView();
            } else {
                CommonMethods.displayShortToast(getActivity(), "Permission Denied!");
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                getCurrentLocation();
            } else {
                LocationManager locationManager = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);
                assert locationManager != null;
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getCurrentLocation();
                } else {
                    askLocationViaPermissionDialog();
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        currentMapView.setMyLocationEnabled(true);
    }


    private void initializeMethods() {
        initializeUI();
        initializeViewModel();
        initializeMaps();
    }

    private void initializeUI() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
        uiHelper = new UIHelper(getActivity());
    }

    private void initializeViewModel() {
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
    }

    private void initializeMaps() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(googleMap -> {
            currentMapView = googleMap;
            setGoogleMapSettings();
            initializeMapView();
        });
    }

    private void setGoogleMapSettings() {
        googleMapHelper = new GoogleMapHelper(currentMapView);
        googleMapHelper.defaultMapSettings();
        currentMapView.setMapStyle(MapStyleOptions.loadRawResourceStyle(Objects.requireNonNull(getActivity()), R.raw.maps_style));

    }

    private void initializeMapView() {
        if (!uiHelper.isPlayServicesAvailable()) {
            CommonMethods.displayShortToast(getContext(), "Play services is not installed!");
            Objects.requireNonNull(getActivity()).finish();
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQ_CODE);
            return;
        }


        mLocationRequest = uiHelper.getLocationRequest();


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        SettingsClient client = LocationServices.getSettingsClient(Objects.requireNonNull(getActivity()));
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(locationSettingsResponse -> {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            getCurrentLocation();

        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            task.addOnFailureListener(e -> checkGps((ApiException) e));
        }

        homeScreenViewModel.getLocationDataFromAPI().observe(this, locationObjs -> googleMapHelper.addLocationsToMap(locationObjs));
    }


    private void checkGps(ApiException e) {

        int statusCode = e.getStatusCode();
        switch (statusCode) {
            case CommonStatusCodes.RESOLUTION_REQUIRED:
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    startIntentSenderForResult(resolvable.getResolution().getIntentSender(), MY_PERMISSIONS_REQUEST_LOCATION, null, 0, 0, 0, null);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Location settings are not satisfied. However, we have no way
                // to fix the settings so we won't show the dialog.
                break;
        }
    }


    public void bringMapToCurrentLocation() {
        try {
            if (currentMapView != null) {
                currentMapView.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 4), 300, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void askLocationViaPermissionDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Location Permission Needed")
                .setMessage("This app needs the Location permission, please accept to use location functionality")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    //Prompt the user once explanation has been shown
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                })
                .create()
                .show();
    }


    //callbacks
    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
            bringMapToCurrentLocation();
        }
    };


}
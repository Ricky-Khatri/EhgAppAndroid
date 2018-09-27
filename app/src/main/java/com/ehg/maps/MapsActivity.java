/*
 *  Created by Emaar Hospitality Group on 3/9/18 2:41 PM
 *  Copyright (C) 2018  All rights reserved.
 *  Last modified 3/9/18 2:40 PM
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ehg.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehg.R;
import com.ehg.home.BaseActivity;
import com.ehg.utilities.AppUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * This class will show google map in App.
 */
public class MapsActivity extends BaseActivity implements OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

  private GoogleMap map;
  private GoogleApiClient googleApiClient;
  private MarkerOptions markerOptions;

  private static final int MAP_ZOOM_LEVEL = 14;

  /**
   * Called when activity created.
   * @param savedInstanceState bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);

    initView();
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    Objects.requireNonNull(mapFragment).getMapAsync(this);
  }

  /**
   * Init's view component of this screen.
   */
  private void initView() {
    TextView textViewHeader = findViewById(R.id.textview_header_title);
    ImageView imageViewBack = findViewById(R.id.imageview_header_back);
    textViewHeader.setText(R.string.map_title);
    imageViewBack.setVisibility(View.VISIBLE);
    imageViewBack.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        AppUtil.finishActivityWithAnimation(MapsActivity.this);
      }
    });
  }

  /**
   * Manipulates the map once available. This callback is triggered when the map is ready to be
   * used. This is where we can add markers or lines, add listeners or move the camera. In this
   * case, If Google Play services is not installed on the device, the user will be prompted to
   * install it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  @Override
  public void onMapReady(GoogleMap googleMap) {
    map = googleMap;
    if (map != null) {
      map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
      map.getUiSettings().setZoomControlsEnabled(true);
      map.getUiSettings().setZoomGesturesEnabled(true);
      map.getUiSettings().setCompassEnabled(true);

      //Initialize Google Play Services
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
          buildGoogleApiClient();
          map.setMyLocationEnabled(true);
        }
      } else {
        buildGoogleApiClient();
        map.setMyLocationEnabled(true);
      }

      //Set MyLocationChangeListener
      map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

        /**
         * Called when current location changed.
         * @param location contains lat longs
         */
        @Override
        public void onMyLocationChange(Location location) {

          //Add current location marker on map
          LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
          if (markerOptions == null) {
            addMarkerOnMap(location, latLng);
          }
        }
      });
    }
  }

  /**
   * Called to add marker on map.
   */
  private void addMarkerOnMap(Location location, LatLng latLng) {
    markerOptions = new MarkerOptions();
    markerOptions.position(latLng);

    //Get address from lat longs
    Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
    List<Address> addresses;

    try {
      addresses = geocoder.getFromLocation(location.getLatitude(),
          location.getLongitude(), 1);

      if (addresses != null && addresses.size() > 0) {

        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String zip = addresses.get(0).getPostalCode();
        String country = addresses.get(0).getCountryName();
        markerOptions.title(city + ", " + state + ", " + zip + ", " + country);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    markerOptions
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
    map.addMarker(markerOptions);

    //move map camera
    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    //animate map camera with zoom level $MAP_ZOOM_LEVEL
    map.animateCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM_LEVEL));
  }

  /**
   * This method is used to initialize the Google Play Services.
   */
  protected synchronized void buildGoogleApiClient() {
    googleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(LocationServices.API)
        .build();
    googleApiClient.connect();
  }

  /**
   * This method will be invoked asynchronously when the connect request has successfully
   * completed.
   *
   * @param bundle Bundle of data provided to clients by Google Play services. May be null if no
   *        content is provided by the service.
   */
  @Override
  public void onConnected(@Nullable Bundle bundle) {
    LocationRequest locationRequest = new LocationRequest();
    locationRequest.setInterval(1000);
    locationRequest.setFastestInterval(1000);
    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
  }

  /**
   * Called when the client is temporarily in a disconnected state.
   *
   * @param constant The reason for the disconnection. Defined by constants CAUSE_*.
   */
  @Override
  public void onConnectionSuspended(int constant) {

  }

  /**
   * Called when there was an error connecting the client to the service.
   *
   * @param connectionResult A ConnectionResult that can be used for resolving the error, and
   *        deciding what sort of error occurred.
   *        To resolve the error, the resolution must be started from
   *        an activity with a non-negative requestCode passed to s
   *        tartResolutionForResult(Activity, int).
   *        Applications should implement onActivityResult in their
   *        Activity to call connect() again if the
   *        user has resolved the issue (resultCode is RESULT_OK).
   */
  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  /**
   * OnKeyDown callback will be called when phone back key pressed.
   *
   * @param keyCode keycode
   * @param event event
   * @return return boolean value
   */
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      AppUtil.finishActivityWithAnimation(this);
    }
    return super.onKeyDown(keyCode, event);
  }
}

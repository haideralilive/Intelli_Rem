package com.comsats.my_map;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

import static com.comsats.my_map.R.id.map;

public class MapsActivity extends FragmentActivity{

    GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    MarkerOptions markerOptions;
    LatLng latLng;
    Marker marker_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(map);

        // Getting a reference to the map
        googleMap = supportMapFragment.getMap();

                                                                           //current location getter

        // Enabling MyLocation Layer of Google Map
        googleMap.setMyLocationEnabled(true);


        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }


        //current location getter ends

        // Getting reference to btn_find of the layout activity_main
        Button btn_find = (Button) findViewById(R.id.search_button);

        // Defining button click event listener for the find button
        View.OnClickListener findClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting reference to EditText to get the user input location
                EditText etLocation = (EditText) findViewById(R.id.et_location);
                // Getting user input location
                String location = etLocation.getText().toString();
                if(location!=null && !location.equals("")){
                    new GeocoderTask().execute(location);
                    }
                }
            };

        //click on marker get value
       googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
           Intent i=null;
           @Override
           public boolean onMarkerClick(Marker marker) {

               double lati=marker.getPosition().latitude;
               double longi=marker.getPosition().longitude;

               i= new Intent(MapsActivity.this,addTask.class);


               Bundle b = new Bundle();
               b.putDouble("latitude", lati);
               b.putDouble("longitude", longi);
               i.putExtras(b);

               startActivity(i);

               return true;
           }
       });

        //end of click marker get value

        // Setting button click event listener for the find button
        btn_find.setOnClickListener(findClickListener);


        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        //on map clicker
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            Intent i=null;
            @Override
            public void onMapClick(LatLng point) {
                // Getting latitude of the current location
                double lati = point.latitude;


                // Getting longitude of the current location
                double longi = point.longitude;


                i= new Intent(MapsActivity.this,addTask.class);


                Bundle b = new Bundle();
                b.putDouble("latitude", lati);
                b.putDouble("longitude", longi);
                i.putExtras(b);

                startActivity(i);

            }
        });
// end on map clicker
    }

    //CURRENT LOCATION

    public void onLocationChanged(Location location) {

        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        // Setting latitude and longitude in the TextView tv_location


    }

    //current ends

    private void addMarker(LatLng latlng) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title(latlng.latitude + "," + latlng.longitude);
        googleMap.addMarker(markerOptions);
    }

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {
        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
                } catch (IOException e) {
                e.printStackTrace();
                }
            return addresses;
            }



                @Override
        protected void onPostExecute(List<Address> addresses) {

            if(addresses==null || addresses.size()==0){
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
                }

            // Clears all the existing markers on the map
            googleMap.clear();

            // Adding Markers on Google Map for each matching address
            for(int i=0;i<addresses.size();i++){

                Address address = (Address) addresses.get(i);

                // Creating an instance of GeoPoint, to display in Google Map
                latLng = new LatLng(address.getLatitude(), address.getLongitude());

                String addressText = String.format("%s, %s",address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",address.getCountryName());


                markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                //getting latlng

                //end of getting lat and long
                markerOptions.title(addressText);

                googleMap.addMarker(markerOptions);

                // Locate the first location
                if(i==0)
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                CameraUpdate center=
                        CameraUpdateFactory.newLatLng(new LatLng(address.getLatitude(),
                                address.getLongitude()));
                CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

                googleMap.moveCamera(center);
                googleMap.animateCamera(zoom);

                }
            }
       }

}


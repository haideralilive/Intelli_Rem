package com.comsats.my_map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class addTask extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //Intent mIntent = getIntent();

        Bundle b = getIntent().getExtras();
        double latitudeValue = b.getDouble("latitude");
        double logitudeValue = b.getDouble("longitude");

        String finallatitude = Double.toString(latitudeValue);
        String finallongitude = Double.toString(logitudeValue);


        TextView lati = (TextView) findViewById(R.id.latitudetext);
        lati.setText(finallatitude);
        TextView logi = (TextView) findViewById(R.id.longitudetext);
        logi.setText(finallongitude);

        //disable latitude and longitude text fields
        lati.setEnabled(false);
        logi.setEnabled(false);


    }


}

package com.comsats.my_map;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class addtasks extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtasks);


        Bundle b = getIntent().getExtras();
        double latitudeValue = b.getDouble("latitude");
        double logitudeValue = b.getDouble("longitude");

        String finallatitude = Double.toString(latitudeValue);
        String finallongitude = Double.toString(logitudeValue);


        TextView lati = (TextView) findViewById(R.id.latitudetext1);
        lati.setText(finallatitude);
        TextView logi = (TextView) findViewById(R.id.longitudetext1);
        logi.setText(finallongitude);

        //disable latitude and longitude text fields
        lati.setEnabled(false);
        logi.setEnabled(false);
    }

}

package com.comsats.my_map;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class home extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button switch_button=(Button)findViewById(R.id.map_button);
        switch_button.setOnClickListener(new View.OnClickListener() {
            Intent i=null;
            @Override
            public void onClick(View v) {
                i= new Intent(home.this,MapsActivity.class);
                startActivity(i);
                //home.this.getParent().finish();
            }
        });

        Button switch_button1=(Button)findViewById(R.id.signout_button);
        switch_button1.setOnClickListener(new View.OnClickListener() {
            Intent i=null;
            @Override
            public void onClick(View v) {
                i= new Intent(home.this,MainActivity.class);
                startActivity(i);
                //home.this.getParent().finish();
            }
        });
    }

}

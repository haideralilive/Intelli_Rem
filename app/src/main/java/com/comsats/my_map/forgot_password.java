package com.comsats.my_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class forgot_password extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button switch_button = (Button) findViewById(R.id.button);
        switch_button.setOnClickListener(new View.OnClickListener() {
            Intent i = null;

            @Override
            public void onClick(View v) {
                i = new Intent(forgot_password.this, MainActivity.class);
                startActivity(i);
                //home.this.getParent().finish();
            }
        });
    }


}

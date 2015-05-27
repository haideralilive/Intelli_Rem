package com.comsats.my_map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends ActionBarActivity {
    private static final String LOG_TAG = "NET_ERROR";
    Boolean Network ;
    private DialogInterface.OnClickListener listener;
    private Button log,forgot,reg,close ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log= (Button) findViewById(R.id.login);
        forgot= (Button) findViewById(R.id.button_for);
        reg= (Button) findViewById(R.id.SignUp);
        //check if Internet is working or not
        // Network=isNetworkAvailable(this);
        Network=Boolean.TRUE;//hasActiveInternetConnection(this);
        //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        if(Network != Boolean.TRUE)
        {
            //create the dailog box

        }
        else
        {
            //setting user interface
            setContentView(R.layout.activity_main);

        }

    };
    public void call_Home(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, home.class);
        // setContentView(R.layout.activity_activity_home);
        startActivity(intent);
    }
    public void call_Register(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, sign_up.class);
        //setContentView(R.layout.activity_activity_home);
        startActivity(intent);
    }   public void call_forgot(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, forgot_password.class);
        //  setContentView(R.layout.activity_activity_home);
        startActivity(intent);
    }
    public final void alertDialogBuilder(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Connectivity Error");
        alertDialogBuilder.setMessage("There is no Internet Relaunch the application when connected to a network");
        alertDialogBuilder.setCancelable(Boolean.FALSE);

        alertDialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //display error in a dailog box and end application
                // Intent intent = new Intent(Intent.ACTION_MAIN);
                //intent.addCategory(Intent.CATEGORY_HOME);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);

            }});
    };


    public  boolean hasActiveInternetConnection(Context context) {
        if (isNetworkAvailable(context)) {
            try {
                URL a = new URL("http://www.google.com");
                HttpURLConnection url=(HttpURLConnection) a.openConnection() ;
                url.setRequestProperty("User-Agent", "Test");
                url.setRequestProperty("Connection", "close");
                url.setConnectTimeout(1500);
                url.connect();
                return (url.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error checking internet connection", e);
            }
        } else {
            Log.d(LOG_TAG, "No network available!");
        }
        return false;
    }
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager check = (ConnectivityManager)
                this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();
        for (int i = 0; i < info.length; i++) {
            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                return  Boolean.TRUE;
            } else
                break ;
        }
        return  Boolean.FALSE;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

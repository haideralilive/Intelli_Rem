package com.comsats.my_map;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private static final String LOGIN_URL = "http://www.cubicsol.com/saad_webservice/login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    private EditText userf, passf;
    private Button mSubmit, mRegister, forgot;
    // Progress Dialog
    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ConnectionTester ct = new ConnectionTester(getApplicationContext());
        Boolean isInternetPresent = ct.isConnectingToInternet();
        forgot = (Button) findViewById(R.id.button_for);
        //setup buttons
        mSubmit = (Button) findViewById(R.id.login);
        mRegister = (Button) findViewById(R.id.register);
        if (isInternetPresent == Boolean.FALSE) {
            //create the dailog box
            alertDialogBuilder();
        } else {
            //setting user interface
            setContentView(R.layout.activity_main);

        }
    }

    public void call_Home(View v) {

        new AttemptLogin().execute();

    }

    public void call_Register(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, sign_up.class);
        //setContentView(R.layout.activity_activity_home);
        startActivity(intent);
    }

    public void call_forgot(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, forgot_password.class);
        //  setContentView(R.layout.activity_activity_home);
        startActivity(intent);
    }

    public final void alertDialogBuilder() {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.DBox_Text_NetError)
                .setTitle(R.string.DBox_Title_NetError);
        builder.setPositiveButton(R.string.DBox_Button_NetError, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        finish();
                    }
                }
        )
                .show();
// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();


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

    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            userf = (EditText) findViewById(R.id.username);
            passf = (EditText) findViewById(R.id.password_value);
            String username = userf.getText().toString();
            String password = passf.getText().toString();
            Log.v("un=", userf.getText().toString());
            Log.v("\npassword=", passf.getText().toString());

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    Intent i = new Intent(MainActivity.this, home.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                } else {
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

    }
}

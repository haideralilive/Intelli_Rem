package com.comsats.my_map;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class sign_up extends ActionBarActivity {
    private Button reg;
    private String name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        reg = (Button) findViewById(R.id.button_submit);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        /*name = "Saad";
        email = "kmsaaj@hotmail.com";
        password = "Kmsaaj123"; //given hardcoded for now


        Connection conn = null;
        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
       //     String connString = "jdbc:jtds:sqlserver://192.168.0.1:3306/intelli_rem;";
            String username = "root";
            String password = "";
            Log.w("Connection", "open");
            conn = DriverManager.getConnection(connString, username, password);
            //DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:3306/intelli_Rem;instance=SQLEXPRESS;user=root;password=");
            Log.w("Connection","open");
            Statement stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select * from test");

//Print the data to the console
            while(reset.next()){
                Log.w("Data:",reset.getString(3));
//              Log.w("Data",reset.getString(2));
            }
            conn.close();

        } catch (Exception e)
        {
            Log.e("Error connection", "" + Log.getStackTraceString(e));        }
    }
*/
    }

    public void send_data(View view) {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

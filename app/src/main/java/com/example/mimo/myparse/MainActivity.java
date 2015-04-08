package com.example.mimo.myparse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Task task = new Task();
        task.execute();


    }

    protected class Task extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection = null;
            String mdate = null;
            String murl = "http://opendata.epa.gov.tw/ws/Data/AQX/?$select=SiteName,County,PSI,MajorPollutant,Status,PM10,PM2.5,FPMI,PublishTime&$orderby=SiteName&$skip=0&$top=1000&format=json";

            try {
                url = new URL(murl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                mdate = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    JSONObject jsonObject = new JSONObject(mdate);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return mdate;
        }

        protected void onPostExecute(String result) {
            TextView tx = (TextView) findViewById(R.id.textView);
            tx.setText(result);
        }


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

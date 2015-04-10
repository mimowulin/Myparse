package com.example.mimo.myparse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


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
            String SSSS = null;
            ArrayList<JsonDate> jsonArr = null;
            String S1 = "";

            try {
                url = new URL(murl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                mdate = bufferedReader.readLine();
                bufferedReader.close();
                try {

                    JSONObject obj = new JSONArray(mdate).getJSONObject(0);
                    SSSS = obj.getString("PSI");

                    Gson gson = new Gson();
                    //遇到json是陣列開頭要用這語法
                    Type listType = new TypeToken<ArrayList<JsonDate>>() {
                    }.getType();
                    jsonArr = gson.fromJson(mdate, listType);

                    for (JsonDate x : jsonArr) {
                        String s = x.getCounty();
                        String s1 = x.getSiteName();
                        String s2 = x.getPM10();
                        S1 = S1 + "   " + s + s1 + s2;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return S1;
        }

        protected void onPostExecute(String rrrr) {

            TextView tx = (TextView) findViewById(R.id.textView);


            tx.setText(rrrr);
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

package com.example.mimo.myparse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ListView lv, lv1 ;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getListView().setEmptyView(findViewById(R.id.textView));
        Task task = new Task();
        task.execute();
        lv = (ListView)findViewById(R.id.listView);
        lv1 = (ListView)findViewById(R.id.listView2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("霾害資訊");
        toolbar.setSubtitle("空氣品質即時汙染指標(每小時更新)");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);



    }

    protected class Task extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection = null;
            String mdate = null;
            String murl = "http://opendata.epa.gov.tw/ws/Data/AQX/?$select=SiteName,County,PSI,MajorPollutant,Status,PM10,PM2.5,FPMI,PublishTime&$orderby=SiteName&$skip=0&$top=1000&format=json";
            String X= null;



            try {
                url = new URL(murl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                mdate = bufferedReader.readLine();
                bufferedReader.close();


            } catch (IOException e) {
                e.printStackTrace();
            }


            return mdate;
        }

        protected void onPostExecute(String rrrr) {
            ArrayList<String> X= null;
            lv.setAdapter(new Myadapter(MainActivity.this,rrrr));
            ArrayList<JsonDate> s = ArrayJson(rrrr);
          int d =s.size();
           


        }


    }


    public ArrayList<JsonDate> ArrayJson (String toto){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<JsonDate>>() {
        }.getType();
        ArrayList<JsonDate> jsonArr = gson.fromJson(toto, listType);
        return jsonArr;
    }

    public  ArrayList<String> CountyList (ArrayList<JsonDate> arrayjson){
        ArrayList<String> L= null;

        for(int i = 0 ; i<arrayjson.size() ; i++) {
            L.add(arrayjson.get(i).getCounty());
        }
            return L;

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

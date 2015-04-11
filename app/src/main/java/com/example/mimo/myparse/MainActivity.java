package com.example.mimo.myparse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ListView lv, lv1 ;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    String mdate = null;
    public static ArrayList<JsonDate> totojson  = new ArrayList<JsonDate>();
    Toolbar toolbar, toolbar1;
    private static final  String FILE_NAME = "PreferenceSampleFile";
    private boolean test = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getListView().setEmptyView(findViewById(R.id.textView));
        Task task = new Task();
        task.execute();
        lv = (ListView)findViewById(R.id.listView);
        lv1 = (ListView)findViewById(R.id.listView2);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar1 = (Toolbar)findViewById(R.id.toolbar1);
        toolbar.setTitle("空氣品質監測");
        toolbar.setSubtitle("空氣品質汙染指標(每小時更新)");

            toolbar1.setSubtitle("可看簡易資訊");
            toolbar1.setTitle("點選下方選項");
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Search:
                        Intent it = new Intent();
                        it.setClass(MainActivity.this,Search.class);
                        startActivity(it);
                }

                return true;
            }
        });




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    protected class Task extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection connection = null;

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
            List<HashMap<String, String>> XY = new ArrayList<HashMap<String, String>>();
            List<String> X= new ArrayList<String>();
            List<String> Y= new ArrayList<String>();
            lv.setAdapter(new Myadapter(MainActivity.this,rrrr));

            totojson = mjson(rrrr);
            X= mList(totojson, "County");
            Y = mList(totojson, "SiteName");
            for(int i = 0 ; i<totojson.size(); i++){
                HashMap<String,String> H = new HashMap<String,String>();
               H.put("SiteName", Y.get(i));
                H.put("County", X.get(i));
                XY.add(H);
            }

            ListView lv2 = (ListView)findViewById(R.id.listView2);
            lv2.setAdapter(new SimpleAdapter(MainActivity.this, XY,android.R.layout.simple_list_item_2, new String[]{"SiteName","County"}, new int[]{android.R.id.text1,android.R.id.text2}));
            lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onejson(position);
                    ParseObject parse = new ParseObject("PM2_5");
                    parse.add("bl", true);
                    parse.add("position",(int)position);
                    parse.saveInBackground();
                }
            });
            TextView test = (TextView)findViewById(R.id.test);
           String t = String.valueOf(size(totojson));
            test.setText(t);
        }
    }

        public void onejson(int position){
            JsonDate onejson = totojson.get(position);
            String i = onejson.getPM2_5();
            String j = onejson.getSiteName();
            String k = onejson.getStatus();
            toolbar1.setTitle("PM2.5: "+ i);
            toolbar1.setSubtitle("觀測站: " + j + " 狀態:" + k);
        }

    public ArrayList<JsonDate> mjson(String toto){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<JsonDate>>() {
        }.getType();
        ArrayList<JsonDate> jsonArr = gson.fromJson(toto, listType);
        return jsonArr;
    }

    public  List<String> mList (ArrayList<JsonDate> arrayjson, String cc ){
        List<String> L= new ArrayList<String>();
        switch (cc){
            case "County":
                for(int i = 0 ; i<arrayjson.size() ; i++) {
                    L.add(arrayjson.get(i).getCounty());
                }
                return L;
            case "SiteName":
                for(int i = 0 ; i<arrayjson.size() ; i++) {
                    L.add(arrayjson.get(i).getSiteName());
                }
                return L;
            default:
                return null;
        }
        }

    public int size (ArrayList<JsonDate> arrayjson){
        return arrayjson.size();
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

package com.lin.mimo.myparse;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;


public class Search extends ActionBarActivity {
    ArrayList<JsonDate> x = new ArrayList<JsonDate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        final ArrayList<JsonDate> x = MainActivity.totojson;
//        Spinner spinner =(Spinner)findViewById(R.id.spinner);
//
//        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mSiteName(x) ));
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ArrayList<JsonDate> mjson = new ArrayList<JsonDate>();
//                mjson.add(x.get(position));
//                ListView lv3 = (ListView)findViewById(R.id.listView3);
//                lv3.setAdapter(new Myadapter(Search.this,mjson));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(6);
        gridView.setAdapter(new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, mSiteName(x)));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<JsonDate> mjson = new ArrayList<JsonDate>();
                mjson.add(x.get(position));
                ListView lv3 = (ListView) findViewById(R.id.listView3);
                lv3.setAdapter(new Myadapter(Search.this, mjson));
            }
        });
    }

    public ArrayList<String> mSiteName(ArrayList<JsonDate> x) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < x.size(); i++) {
            arrayList.add(x.get(i).getSiteName());
        }
        return arrayList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

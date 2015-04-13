package com.lin.mimo.myparse;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by mimo on 2015/4/10.
 */
class Myadapter extends BaseAdapter {
    private String jsonstr;
    private ArrayList<JsonDate> jsonArr = null;
    private String S1 = null;
    private LayoutInflater myInflater;

    public Myadapter() {
    }

    public Myadapter(Context ctxt, String jsonstr) {
        myInflater = LayoutInflater.from(ctxt);
        this.jsonstr = jsonstr;
        Gson gson = new Gson();
        //遇到json是陣列開頭要用這語法
        Type listType = new TypeToken<ArrayList<JsonDate>>() {
        }.getType();
        jsonArr = gson.fromJson(jsonstr, listType);
    }

    public Myadapter(Context ctxt, ArrayList<JsonDate> jsonArr) {
        myInflater = LayoutInflater.from(ctxt);
        this.jsonArr = jsonArr;
    }

    public void jsondate() {

    }

    public String getS1() {
        return S1;
    }

    @Override
    public int getCount() {
        return jsonArr.size();
    }

    @Override
    public Object getItem(int position) {
        return jsonArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewTag viewTag;

        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.iten, null);
            viewTag = new ViewTag((TextView) convertView.findViewById(R.id.sitename), (TextView) convertView.findViewById(R.id.county), (TextView) convertView.findViewById(R.id.psi), (TextView) convertView.findViewById(R.id.majorpollutant),
                    (TextView) convertView.findViewById(R.id.status), (TextView) convertView.findViewById(R.id.pm10), (TextView) convertView.findViewById(R.id.pm2_5), (TextView) convertView.findViewById(R.id.publishtime));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ViewTag) convertView.getTag();
        }

        viewTag.siteName.setText(jsonArr.get(position).getSiteName());
        viewTag.county.setText(jsonArr.get(position).getCounty());
        viewTag.psi.setText(jsonArr.get(position).getPSI());
        viewTag.masorPollutant.setText(jsonArr.get(position).getMajorPollutant());
        if (jsonArr.get(position).getStatus().equals("普通")) {

            viewTag.status.setTextColor(Color.rgb(255, 255, 50));
        } else {
            viewTag.status.setTextColor(Color.rgb(183, 183, 183));
        }
        viewTag.status.setText(jsonArr.get(position).getStatus());
        viewTag.pm10.setText(jsonArr.get(position).getPM10());
        viewTag.pm2_5.setText(jsonArr.get(position).getPM2_5());
        viewTag.publisTime.setText(jsonArr.get(position).getPublishTime());

        return convertView;
    }

    class ViewTag {
        TextView siteName;
        TextView county;
        TextView psi;
        TextView masorPollutant;
        TextView status;
        TextView pm10;
        TextView pm2_5;
        TextView publisTime;

        ViewTag(TextView siteName, TextView county, TextView psi, TextView masorPollutant, TextView status, TextView pm10, TextView pm2_5, TextView publisTime) {
            this.siteName = siteName;
            this.county = county;
            this.psi = psi;
            this.masorPollutant = masorPollutant;
            this.status = status;
            this.pm10 = pm10;
            this.pm2_5 = pm2_5;
            this.publisTime = publisTime;
        }
    }

}





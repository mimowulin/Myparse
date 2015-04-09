package com.example.mimo.myparse;

import org.json.JSONObject;

/**
 * Created by mimo on 2015/4/8.
 */
public class JsonDate {

    private String SiteName;
    private String County;
    private String PSI;
    private String MajorPollutant;
    private String Status = "Status";
    private String PM10;
    private String PM2_5 = "PM2.5";
    private String FPMI;
    private String PublishTime;
    JSONObject jsonobject;


    public String getSiteName() {
        return SiteName;
    }

    public String getCounty() {
        return County;
    }

    public String getPSI() {
        return PSI;
    }

    public String getMajorPollutant() {
        return MajorPollutant;
    }

    public String getStatus() {
        return Status;
    }

    public String getPM10() {
        return PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public String getFPMI() {
        return FPMI;
    }

    public String getPublishTime() {
        return PublishTime;
    }


    public JsonDate() {
    }


}
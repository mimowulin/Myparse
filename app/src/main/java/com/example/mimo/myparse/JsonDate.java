package com.example.mimo.myparse;

import org.json.JSONObject;

/**
 * Created by mimo on 2015/4/8.
 */
public class JsonDate {
   private  String SITENAME = "Sitename";
   private  String COUNTY = "County";
   private  String PSI = "PSI";
   private  String MAJORPOLLUTANT = "MajorPollutant";
   private  String STATUS = "Status";
   private  String PM10 = "PM10";
   private  String PM2_5 = "PM2.5";
   private  String FPMI = "FPMI";
   private  String PUBLISHTIME = "PublishTime";
    JSONObject jsonobject;

    public JsonDate() {
    }
public JsonDate(JSONObject jsonobject){
    this.jsonobject = jsonobject;
}
    public void getJsonDate(int conn){

    }

    public void setSITENAME(String SITENAME) {
        this.SITENAME = SITENAME;
    }

    public void setCOUNTY(String COUNTY) {
        this.COUNTY = COUNTY;
    }

    public void setPSI(String PSI) {
        this.PSI = PSI;
    }

    public void setMAJORPOLLUTANT(String MAJORPOLLUTANT) {
        this.MAJORPOLLUTANT = MAJORPOLLUTANT;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public void setFPMI(String FPMI) {
        this.FPMI = FPMI;
    }

    public void setPUBLISHTIME(String PUBLISHTIME) {
        this.PUBLISHTIME = PUBLISHTIME;
    }

    public String getSITENAME() {
        return SITENAME;
    }

    public String getCOUNTY() {
        return COUNTY;
    }

    public String getPSI() {
        return PSI;
    }

    public String getMAJORPOLLUTANT() {
        return MAJORPOLLUTANT;
    }

    public String getSTATUS() {
        return STATUS;
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

    public String getPUBLISHTIME() {
        return PUBLISHTIME;
    }



}

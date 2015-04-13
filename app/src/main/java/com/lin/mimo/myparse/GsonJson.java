package com.lin.mimo.myparse;

import com.google.gson.annotations.SerializedName;


public class GsonJson {

    private static final String FIELD_O3 = "O3";
    private static final String FIELD_MAJOR_POLLUTANT = "MajorPollutant";
    private static final String FIELD_STATUS = "Status";
    private static final String FIELD_WIND_DIREC = "WindDirec";
    private static final String FIELD_WIND_SPEED = "WindSpeed";
    private static final String FIELD_FPMI = "FPMI";
    private static final String FIELD_SITE_NAME = "SiteName";
    @SerializedName("PM2.5")
    private static final String FIELD_PM2_5 = "PM2.5";
    private static final String FIELD_SO2 = "SO2";
    private static final String FIELD_PM10 = "PM10";
    private static final String FIELD_PSI = "PSI";
    private static final String FIELD_CO = "CO";
    private static final String FIELD_PUBLISH_TIME = "PublishTime";
    private static final String FIELD_COUNTY = "County";
    private static final String FIELD_NO2 = "NO2";


    @SerializedName(FIELD_O3)
    private int mO3;
    @SerializedName(FIELD_MAJOR_POLLUTANT)
    private String mMajorPollutant;
    @SerializedName(FIELD_STATUS)
    private String mStatus;
    @SerializedName(FIELD_WIND_DIREC)
    private int mWindDirec;
    @SerializedName(FIELD_WIND_SPEED)
    private double mWindSpeed;
    @SerializedName(FIELD_FPMI)
    private String mFPMI;
    @SerializedName(FIELD_SITE_NAME)
    private String mSiteName;
    @SerializedName(FIELD_PM2_5)
    private String mPM25;
    @SerializedName(FIELD_SO2)
    private int mSO2;
    @SerializedName(FIELD_PM10)
    private int mPM10;
    @SerializedName(FIELD_PSI)
    private int mPSI;
    @SerializedName(FIELD_CO)
    private double mCO;
    @SerializedName(FIELD_PUBLISH_TIME)
    private String mPublishTime;
    @SerializedName(FIELD_COUNTY)
    private String mCounty;
    @SerializedName(FIELD_NO2)
    private int mNO2;


    public GsonJson() {

    }

    public void setO3(int o3) {
        mO3 = o3;
    }

    public int getO3() {
        return mO3;
    }

    public void setMajorPollutant(String majorPollutant) {
        mMajorPollutant = majorPollutant;
    }

    public String getMajorPollutant() {
        return mMajorPollutant;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setWindDirec(int windDirec) {
        mWindDirec = windDirec;
    }

    public int getWindDirec() {
        return mWindDirec;
    }

    public void setWindSpeed(double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public double getWindSpeed() {
        return mWindSpeed;
    }

    public void setFPMI(String fPMI) {
        mFPMI = fPMI;
    }

    public String getFPMI() {
        return mFPMI;
    }

    public void setSiteName(String siteName) {
        mSiteName = siteName;
    }

    public String getSiteName() {
        return mSiteName;
    }

    public void setPM25(String pM25) {
        mPM25 = pM25;
    }

    public String getPM25() {
        return mPM25;
    }

    public void setSO2(int sO2) {
        mSO2 = sO2;
    }

    public int getSO2() {
        return mSO2;
    }

    public void setPM10(int pM10) {
        mPM10 = pM10;
    }

    public int getPM10() {
        return mPM10;
    }

    public void setPSI(int pSI) {
        mPSI = pSI;
    }

    public int getPSI() {
        return mPSI;
    }

    public void setCO(double cO) {
        mCO = cO;
    }

    public double getCO() {
        return mCO;
    }

    public void setPublishTime(String publishTime) {
        mPublishTime = publishTime;
    }

    public String getPublishTime() {
        return mPublishTime;
    }

    public void setCounty(String county) {
        mCounty = county;
    }

    public String getCounty() {
        return mCounty;
    }

    public void setNO2(int nO2) {
        mNO2 = nO2;
    }

    public int getNO2() {
        return mNO2;
    }


}
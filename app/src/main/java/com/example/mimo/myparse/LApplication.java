package com.example.mimo.myparse;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by mimo on 2015/3/29.
 */
public class LApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this,
                "bfzLl9jTEDhtUsqDQ2wYYXkqB9S5ak81F0d4Gyre"
                ,
                "MY0MO2oGrZ9l7T8cVo4k4qkQ0EaWirIUVGoRGehT"
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        ParseACL.setDefaultACL(defaultACL, true);
    }

}

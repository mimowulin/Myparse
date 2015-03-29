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
                "bfzLl9jTEDhtUsqDQ2wYYXkqB9S5ak81F0d4Gyre"
        );

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        ParseACL.setDefaultACL(defaultACL, true);
    }

}

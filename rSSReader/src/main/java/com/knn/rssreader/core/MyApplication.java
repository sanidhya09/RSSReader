package com.knn.rssreader.core;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

public class MyApplication extends Application
{


    private static final String SHARED_PREFERENCE_NAME = "iconnect_pay_pref";
    public SharedPreferences sharedPreferences;
    private Activity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        createSharedPreference();
    }

    private void createSharedPreference() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE);
    }

    public void setCurrentActivity(Activity activity)
    {
        this.currentActivity=activity;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }


}

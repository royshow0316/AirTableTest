package com.example.roy.airtabletest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by roy on 2018/1/10.
 */

public class StethoSqlite extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Start Stetho
        Stetho.initializeWithDefaults(this);
    }
}

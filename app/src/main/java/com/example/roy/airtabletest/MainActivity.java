package com.example.roy.airtabletest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 40;
    private static final int REQUEST_CAMERA = 41;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE }, REQUEST_EXTERNAL_STORAGE);
        } else {    //取得權限
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {     //未連線的話會是null
                if (networkInfo.isConnected()) {   //網路是否已連接
                    if (networkInfo.isAvailable()) { //目前網路是否可使用
                        NetworkInfo.State state = networkInfo.getState();   //目前連線狀態(DISCONNECTED, CONNECTING, CONNECTED)
                        new JsonImportOnline(MainActivity.this).execute();
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo != null) {     //未連線的話會是null
                        if (networkInfo.isConnected()) {   //網路是否已連接
                            if (networkInfo.isAvailable()) { //目前網路是否可使用
                                NetworkInfo.State state = networkInfo.getState();   //目前連線狀態(DISCONNECTED, CONNECTING, CONNECTED)
                                new JsonImportOnline(MainActivity.this).execute();
                            }
                        }
                    }
                } else {

                }
                return;
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
        }
    }
}

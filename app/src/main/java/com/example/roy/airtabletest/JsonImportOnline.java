package com.example.roy.airtabletest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by roy on 2018/1/9.
 */

public class JsonImportOnline extends AsyncTask<URL, Integer, String> {

    private final static String TAG = "JsonImportOnline";
    private Context mContext;
    private ProgressDialog mDialog;
    private ImportDataOnline importDataOnline = new ImportDataOnline(mContext);

    public JsonImportOnline(Context context) {
        this.mContext = context;
    }

    protected void onPreExecute() {
        mDialog = new ProgressDialog(mContext);
        mDialog.setTitle("");
        mDialog.setMessage("更新资料...");
        mDialog.setCancelable(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.show();
    }

    @Override
    protected String doInBackground(URL... urls) {

        String internalStoragePath = getExternalStorageDirectory().getPath() + "/Sample";
        File fileDir = new File(internalStoragePath + "/Help");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        try {
            importDataOnline.insertCommand(mContext);
            importDataOnline.insertAppList(mContext);
        } catch (IOException e) {
            Log.d("JsonImportOnline", e.toString());
        }
        return "ok";
    }

    protected void onPostExecute(String result) {
        if(result.equals("ok")){
            mDialog.dismiss();
        } else {
            mDialog.dismiss();
        }
    }
}

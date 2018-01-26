package com.example.roy.airtabletest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.roy.airtabletest.dao.AppListDao;
import com.example.roy.airtabletest.dao.CommandDao;
import com.example.roy.airtabletest.dao.HelpVersionDao;
import com.example.roy.airtabletest.response.RecordsResponse;
import com.example.roy.model.AppList;
import com.example.roy.model.BgId;
import com.example.roy.model.Command;
import com.example.roy.model.HelpVersion;
import com.example.roy.model.Records;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.Environment.getExternalStorageDirectory;
import static com.example.roy.utils.API.appListJson;
import static com.example.roy.utils.API.commandJson;
import static com.example.roy.utils.API.versionJson;

/**
 * Created by roy on 2018/1/9.
 */

public class ImportDataOnline {

    private Context mContext;
    private String mInternalStorageImagePath = getExternalStorageDirectory().getPath() + "/Sample/" + "/Help/";
    private OkHttpClient mClient = new OkHttpClient();

    public ImportDataOnline(Context context) {
        this.mContext = context;
    }

    public void insertCommand(Context context) throws IOException {
        Request request = new Request.Builder().url(commandJson).build();
        Response response = mClient.newCall(request).execute();
        if (response.message().equals("OK")) {
            RecordsResponse recordResponse = new Gson().fromJson(response.body().charStream(), RecordsResponse.class);
            CommandDao commandDao = new CommandDao(context);
            for (int i = 0; i < recordResponse.getRecords().size(); i++) {
                Records records = recordResponse.getRecords().get(i);
                String commandJson = new Gson().toJson(records.getFields());

                Command local = commandDao.getById(records.getId());
                Command command = new Gson().fromJson(commandJson, Command.class);
                if (command.getBgId() != null) {    //getImage
                    BgId bgId = command.getBgId().get(0);
                    Bitmap bitmap = getBitmapFormUrl(bgId.getUrl(), bgId.getFilename());
                    saveToIntervalStorage(bitmap, bgId.getFilename());
                }
                command.setCreatedTime(records.getCreatedTime());
                setCommand(command);
                if (local == null) {
                    command.setId(records.getId());
                    commandDao.insert(command);
                } else {
                    commandDao.update(command);
                }
            }
        }
    }

    public void insertAppList(Context context) throws IOException {
        Request request = new Request.Builder().url(appListJson).build();
        Response response = mClient.newCall(request).execute();
        if (response.message().equals("OK")) {
            RecordsResponse recordResponse = new Gson().fromJson(response.body().charStream(), RecordsResponse.class);
            AppListDao appListDao = new AppListDao(context);
            for (int i = 0; i < recordResponse.getRecords().size(); i++) {
                Records records = recordResponse.getRecords().get(i);
                String appListJson = new Gson().toJson(records.getFields());

                AppList local = appListDao.getById(records.getId());
                AppList appList = new Gson().fromJson(appListJson, AppList.class);
                appList.setCreatedTime(records.getCreatedTime());
                setAppList(appList);
                if (local == null) {
                    appList.setId(records.getId());
                    appListDao.insert(appList);
                } else {
                    appListDao.update(appList);
                }
            }
        }
    }

    private void setCommand(Command command) {
        if (command.getTitle().equals("null")) command.setTitle("");
        if (command.getTts().equals("null")) command.setTts("");
        if (command.getSubTitle().equals("null")) command.setSubTitle("");
        if (command.getHint().equals("null")) command.setHint("");
        if (command.getButtonStatus().equals("null")) command.setButtonStatus("");
    }

    private void setAppList(AppList appList) {
        if (appList.getTitle().equals("null")) appList.setTitle("");
        if (appList.getTts().equals("null")) appList.setTts("");
        if (appList.getSubTitle().equals("null")) appList.setSubTitle("");
        if (appList.getHint().equals("null")) appList.setHint("");
        if (appList.getButtonStatus().equals("null")) appList.setButtonStatus("");
    }

    private static Bitmap getBitmapFormUrl(String urlPath, String imageName) {
        try {
            imageName = URLEncoder.encode(imageName, "UTF-8");
            URL url = new URL(urlPath);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            Log.d("ImportDataOnline", e.toString());
            return null;
        }
    }

    private void saveToIntervalStorage(Bitmap bitmapImage, String imageName) {
        File mypath = new File(mInternalStorageImagePath, imageName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            Log.d("ImportDataOnline", e.toString());
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                Log.d("ImportDataOnline", e.toString());
            }
        }
    }
}

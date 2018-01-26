package com.example.roy.airtabletest.dao;

import android.content.Context;

import com.example.roy.model.AppList;

/**
 * Created by roy on 2018/1/11.
 */

public class AppListDao extends BaseDao<AppList, String> {

    public AppListDao(Context context) {
        super(context, AppList.class);
    }
}

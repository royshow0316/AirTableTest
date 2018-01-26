package com.example.roy.airtabletest.dao;

import android.content.Context;

import com.example.roy.model.HelpVersion;

/**
 * Created by roy on 2018/1/10.
 */

public class HelpVersionDao extends BaseDao<HelpVersion, String> {

    public HelpVersionDao(Context context) {
        super(context, HelpVersion.class);
    }
}

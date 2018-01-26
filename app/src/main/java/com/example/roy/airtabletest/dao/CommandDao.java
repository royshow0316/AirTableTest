package com.example.roy.airtabletest.dao;

import android.content.Context;

import com.example.roy.model.Command;

/**
 * Created by roy on 2018/1/9.
 */

public class CommandDao extends BaseDao<Command, String> {

    public CommandDao(Context context) {
        super(context, Command.class);
    }
}

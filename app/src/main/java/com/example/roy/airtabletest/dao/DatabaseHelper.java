package com.example.roy.airtabletest.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.roy.model.AppList;
import com.example.roy.model.Command;
import com.example.roy.model.HelpVersion;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by roy on 2018/1/9.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "HelpDb";
    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            switch (oldVersion) {
                case 1:
                    TableUtils.dropTable(connectionSource, HelpVersion.class, true);
                    TableUtils.dropTable(connectionSource, Command.class, true);
                    TableUtils.dropTable(connectionSource, AppList.class, true);
                    break;
                default:
                    //no update
                    break;
            }

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, HelpVersion.class);
            TableUtils.createTable(connectionSource, Command.class);
            TableUtils.createTable(connectionSource, AppList.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

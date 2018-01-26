package com.example.roy.utils;

/**
 * Created by roy on 2018/1/9.
 */

public class API {
    private static final String AirTableUrl = "你的airtable url";
    private static final String ApiKey = "?api_key=你的api key";

    //region GetJson
    public static final String versionJson = AirTableUrl + "HelpVersion" + ApiKey;
    public static final String commandJson = AirTableUrl + "Command" + ApiKey;
    public static final String appListJson = AirTableUrl + "AppList" + ApiKey;
    //endregion
}

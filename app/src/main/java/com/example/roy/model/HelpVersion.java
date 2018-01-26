package com.example.roy.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by roy on 2018/1/10.
 */

public class HelpVersion {

    public HelpVersion() {

    }

    @DatabaseField(id = true)
    @SerializedName("id")
    private String id;
    @DatabaseField
    @SerializedName("createdTime")
    private String createdTime;
    @DatabaseField
    @SerializedName("Status")
    private String status;
    @DatabaseField
    @SerializedName("VersionCode")
    private int versionCode;

    public HelpVersion(String id, String createdTime, String status, int versionCode) {
        this.id = id;
        this.createdTime = createdTime;
        this.status = status;
        this.versionCode = versionCode;
    }

    public String getId() {
        return id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getStatus() {
        return status;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}

package com.example.roy.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by roy on 2018/1/9.
 */

public class Records {

    public Records() {

    }

    @SerializedName("id")
    private String id;
    @SerializedName("fields")
    private Object fields;
    @SerializedName("createdTime")
    private String createdTime;

    public Records(String id, Object fields, String createdTime) {
        this.id = id;
        this.fields = fields;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public Object getFields() {
        return fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }
}

package com.example.roy.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by roy on 2018/1/15.
 */

public class BgId {

    public BgId() {

    }

    @DatabaseField(id = true)
    @SerializedName("id")
    private String id;
    @DatabaseField
    @SerializedName("url")
    private String url;
    @DatabaseField
    @SerializedName("filename")
    private String filename;
    @DatabaseField
    @SerializedName("size")
    private int size;
    @DatabaseField
    @SerializedName("type")
    private String type;
    @DatabaseField
    @SerializedName("thumbnails")
    private Object thumbnails;

    public BgId(String id, String url, String filename, int size, String type, Object thumbnails) {
        this.id = id;
        this.url = url;
        this.filename = filename;
        this.size = size;
        this.type = type;
        this.thumbnails = thumbnails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Object thumbnails) {
        this.thumbnails = thumbnails;
    }
}

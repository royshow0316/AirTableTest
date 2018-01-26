package com.example.roy.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.sql.Array;
import java.util.List;

/**
 * Created by roy on 2018/1/9.
 */

public class Command {

    public Command() {

    }

    @DatabaseField(id = true)
    @SerializedName("id")
    private String id;
    @DatabaseField
    @SerializedName("createdTime")
    private String createdTime;
    @DatabaseField
    @SerializedName("Title")
    private String title;
    @DatabaseField
    @SerializedName("Triggers")
    private String triggers;
    @DatabaseField
    @SerializedName("Level")
    private int level;
    @DatabaseField
    @SerializedName("TTS")
    private String tts;
    @DatabaseField
    @SerializedName("SubTitle")
    private String subTitle;
    @DatabaseField
    @SerializedName("Hint")
    private String hint;
    @DatabaseField
    @SerializedName("ButtonStatus")
    private String buttonStatus;
    @SerializedName("BgId")
    private List<BgId> bgId;

    public Command(String id, String createdTime, String title, String triggers, int level, String tts, String subTitle, String hint, String buttonStatus, List<BgId> bgId) {
        this.id = id;
        this.createdTime = createdTime;
        this.title = title;
        this.triggers = triggers;
        this.level = level;
        this.tts = tts;
        this.subTitle = subTitle;
        this.hint = hint;
        this.buttonStatus = buttonStatus;
        this.bgId = bgId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getButtonStatus() {
        return buttonStatus;
    }

    public void setButtonStatus(String buttonStatus) {
        this.buttonStatus = buttonStatus;
    }

    public List<BgId> getBgId() {
        return bgId;
    }

    public void setBgId(List<BgId> bgId) {
        this.bgId = bgId;
    }
}

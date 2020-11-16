package com.loysc.zzangco.kirikiri_snu.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-15.
 */

public class ScheduleVo implements Serializable {

    @SerializedName("id")
    private String id;

    @SerializedName("level")
    private String level;

    @SerializedName("type")
    private String type;

    @SerializedName("cycle")
    private String cycle;

    @SerializedName("annDate")
    private String annDate;

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getAnnDate() {
        return annDate;
    }

    public void setAnnDate(String annDate) {
        this.annDate = annDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

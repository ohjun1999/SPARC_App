package com.loysc.zzangco.kirikiri_snu.common;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-11-23.
 */

public class ScheduleItem implements Serializable {
    private String id;
    private String level;
    private String type;
    private String cycle;
    private String annDate;
    private String title;
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

package com.loysc.zzangco.kirikiri_snu.common;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-11-21.
 */

public class BoardItem implements Serializable {
    private String id;
    private String title;
    private String content;
    private String important;
    private String readOK;
    private String createday;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getReadOK() {
        return readOK;
    }

    public void setReadOK(String readOK) {
        this.readOK = readOK;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public String getCreateday() {
        return createday;
    }

    public void setCreateday(String createday) {
        this.createday = createday;
    }
}

package com.loysc.zzangco.kirikiri_snu.vo;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-15.
 */

public class BoardVo implements Serializable {
    private String id;
    private String title;
    private String content;
    private String important;
    private String createday;
    private String currentPage;
    private String totalCount;

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

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}

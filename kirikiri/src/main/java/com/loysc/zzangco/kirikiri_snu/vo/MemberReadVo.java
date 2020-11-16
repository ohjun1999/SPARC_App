package com.loysc.zzangco.kirikiri_snu.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-15.
 */

public class MemberReadVo implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("tableID")
    private String tableID;

    @SerializedName("memberID")
    private String memberID;

    @SerializedName("dataType")
    private String dataType;

    @SerializedName("readOK")
    private String readOK;

    @SerializedName("createDate")
    private String createDate;

    @SerializedName("updateDate")
    private String updateDate;

    @SerializedName("content")
    private String content;

    @SerializedName("action")
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getReadOK() {
        return readOK;
    }

    public void setReadOK(String readOK) {
        this.readOK = readOK;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

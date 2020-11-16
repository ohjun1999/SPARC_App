package com.loysc.zzangco.kirikiri_snu.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-14.
 */

public class MemberVo implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("sunmoon")
    private String sunmoon;

    @SerializedName("handphone")
    private String handphone;

    @SerializedName("email")
    private String email;

    @SerializedName("comname")
    private String comname;

    @SerializedName("dept")
    private String dept;

    @SerializedName("position")
    private String position;

    @SerializedName("comphone")
    private String comphone;

    @SerializedName("comaddress")
    private String comaddress;

    @SerializedName("homephone")
    private String homephone;

    @SerializedName("homeaddress")
    private String homeaddress;

    @SerializedName("fax")
    private String fax;

    @SerializedName("business")
    private String business;

    @SerializedName("businame")
    private String businame;

    @SerializedName("grannumber")
    private String grannumber;

    @SerializedName("totalPage")
    private String totalPage;

    @SerializedName("currentPage")
    private String currentPage;

    @SerializedName("searchWord")
    private String searchWord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSunmoon() {
        return sunmoon;
    }

    public void setSunmoon(String sunmoon) {
        this.sunmoon = sunmoon;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getComphone() {
        return comphone;
    }

    public void setComphone(String comphone) {
        this.comphone = comphone;
    }

    public String getComaddress() {
        return comaddress;
    }

    public void setComaddress(String comaddress) {
        this.comaddress = comaddress;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getBusiname() {
        return businame;
    }

    public void setBusiname(String businame) {
        this.businame = businame;
    }

    public String getGrannumber() {
        return grannumber;
    }

    public void setGrannumber(String grannumber) {
        this.grannumber = grannumber;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
}

package com.loysc.zzangco.kirikiri_snu.common;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-11-09.
 */

public class MemberViewItem implements Serializable{
    private int memberPicDrawable;
    private int flipType;

    private String id;
    private String memberName;
    private String birthday;
    private String sunmoon;
    private String handphone;
    private String email;

    private String comname;
    private String dept;
    private String position;
    private String comphone;
    private String comAddress;

    private String homephone;
    private String homeAddress;
    private String fax;
    private String business;
    private String businame;
    private String grannumber;
    private String fav;
    private String homePage;

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public int getMemberPicDrawable() {
        return memberPicDrawable;
    }

    public void setMemberPicDrawable(int memberPicDrawable) {
        this.memberPicDrawable = memberPicDrawable;
    }

    public int getFlipType() {
        return flipType;
    }

    public void setFlipType(int flipType) {
        this.flipType = flipType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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
}

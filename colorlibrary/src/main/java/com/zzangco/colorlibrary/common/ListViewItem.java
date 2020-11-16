package com.zzangco.colorlibrary.common;

import android.graphics.drawable.Drawable;

/**
 * Created by zzangco on 2017-01-10.
 */

public class ListViewItem {
    private int colorDrawable;
    private String tvColorName;
    private String tvColorCode;
    private String tvColorOrder;
    private String etStock;
    private String company;
    private String type;
    private int flipType;

    public int getFlipType() {
        return flipType;
    }

    public void setFlipType(int flipType) {
        this.flipType = flipType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColorDrawable() {
        return colorDrawable;
    }

    public void setColorDrawable(int colorDrawable) {
        this.colorDrawable = colorDrawable;
    }

    public String getEtStock() {
        return etStock;
    }

    public void setEtStock(String etStock) {
        this.etStock = etStock;
    }

    public String getTvColorCode() {
        return tvColorCode;
    }

    public void setTvColorCode(String tvColorCode) {
        this.tvColorCode = tvColorCode;
    }

    public String getTvColorName() {
        return tvColorName;
    }

    public void setTvColorName(String tvColorName) {
        this.tvColorName = tvColorName;
    }

    public String getTvColorOrder() {
        return tvColorOrder;
    }

    public void setTvColorOrder(String tvColorOrder) {
        this.tvColorOrder = tvColorOrder;
    }
}

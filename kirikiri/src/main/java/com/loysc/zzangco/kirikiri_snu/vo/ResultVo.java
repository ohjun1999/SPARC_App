package com.loysc.zzangco.kirikiri_snu.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-15.
 */

public class ResultVo implements Serializable {

    @SerializedName("result")
    private String result;

    @SerializedName("dataType")
    private String dataType;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

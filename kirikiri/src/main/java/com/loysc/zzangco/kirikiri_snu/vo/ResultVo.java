package com.loysc.zzangco.kirikiri_snu.vo;

import java.io.Serializable;

/**
 * Created by zzangco on 2017-12-15.
 */

public class ResultVo implements Serializable {
    private String result;
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

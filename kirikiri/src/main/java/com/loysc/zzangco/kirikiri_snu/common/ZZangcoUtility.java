package com.loysc.zzangco.kirikiri_snu.common;

/**
 * Created by zzangco on 2017-12-13.
 */

public class ZZangcoUtility {
    public static final String CHECKUSER_PARAM = "handphone"; //핸드폰 번호
    public static final String GET_INFO_OK_PARAM = "id";
    public static int randomRange(int n1,int n2){
        return  (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }
}

package com.loysc.zzangco.kirikiri_snu.common;

import java.util.Calendar;

/**
 * Created by zzangco on 2017-12-13.
 */

public class ZZangcoUtility {
    public static final String CHECKUSER_PARAM = "handphone"; //핸드폰 번호
    public static final String GET_INFO_OK_PARAM = "id";
    public static final String USER_TOKEN = "Token";
    public static int randomRange(int n1,int n2){
        return  (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }

    public static final int getCurrentYear(){
        Calendar currDate = Calendar.getInstance();

        return currDate.get(Calendar.YEAR);
    }
}

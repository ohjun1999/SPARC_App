package com.loysc.zzangco.kirikiri_snu.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import com.loysc.zzangco.kirikiri_snu.vo.MemberVo;

/**
 * Created by zzangco on 2017-11-09.
 */

public class MemberInfo {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_SUNMOON = "sunmoon";
    public static final String COLUMN_HANDPHONE = "handphone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_COMNAME = "comname";
    public static final String COLUMN_DEPT = "dept";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_COMPHONE = "comphone";
    public static final String COLUMN_COMADDRESS = "comaddress";
    public static final String COLUMN_HOMEPHONE = "homephone";
    public static final String COLUMN_HOMEADDRESS = "homeaddress";
    public static final String COLUMN_FAX = "fax";
    public static final String COLUMN_BUSINESS = "business";
    public static final String COLUMN_BUSINAME = "businame";
    public static final String COLUMN_GRANUMBER = "grannumber";
    public static final String COLUMN_FAVORITE = "fav";
    public static final String COLUMN_ORDER = "vieworder";
    public static final String COLUMN_HOMEPAGE = "homepage";

    public static final String TABLE_NAME_MEMBER = "member";
    public static final String TABLE_NAME_BUSINESS = "business";


    private DataBase dataBase;
    private SQLiteDatabase mDB;
    private final Context ctx;


    public MemberInfo(Context ctx) {
        this.ctx = ctx;
    }

    public MemberInfo openc() throws SQLException {
        dataBase = DataBase.getInstance(ctx);
        mDB = dataBase.getReadableDatabase();

        return this;
    }

    public void close(){
        dataBase.close();
    }

    private String makeWhere(String searchWord){
        String retrunVAl = "";

        retrunVAl = "1=1 and (" +
                COLUMN_NAME + " LIKE '%" + searchWord + "%' " +
                "or REPLACE(" + COLUMN_HANDPHONE + ",'-','') LIKE '%" + searchWord + "%' " +
                "or " + COLUMN_EMAIL + " LIKE '%" + searchWord + "%' " +
                "or " + COLUMN_COMNAME + " LIKE '%" + searchWord + "%' ";

        return retrunVAl;
    }

    public ArrayList<MemberViewItem> getMemberListSearch(String serachWord,String granumber,String fav,String business){

        Log.e("zzangco","Where [" + ChoSearchQuery.makeQuery(serachWord)+"]");


        ArrayList<MemberViewItem> returnVal = new ArrayList<MemberViewItem>();

        String[] columns = {COLUMN_ID,COLUMN_NAME,COLUMN_BIRTHDAY,COLUMN_SUNMOON,COLUMN_HANDPHONE,COLUMN_EMAIL,
                COLUMN_COMNAME,COLUMN_DEPT,COLUMN_POSITION,COLUMN_COMPHONE,COLUMN_COMADDRESS,
                COLUMN_HOMEPHONE,COLUMN_HOMEADDRESS,COLUMN_FAX,COLUMN_BUSINESS,COLUMN_BUSINAME,COLUMN_GRANUMBER,COLUMN_FAVORITE};

        String where = ChoSearchQuery.makeQuery(serachWord);

        if(null != granumber && !granumber.isEmpty() && !granumber.equals("00")){

            where += " and " +   COLUMN_GRANUMBER + "='" + granumber + "'";
        }

        if(null != fav && !fav.isEmpty() && fav.equals("T")){
            where += " and " +   COLUMN_FAVORITE + "='T'";
        }

        if(null != business && !business.isEmpty()){
            //where += " and " +   COLUMN_BUSINESS + "='" + business + "'";
            where += " and " +   COLUMN_BUSINAME + " LIKE '%" + business + "%'";
        }

        where += " and " + COLUMN_GRANUMBER + " != '111' ";

        Log.e("zzangco","Where [" + where+"]");
        Cursor result = null;
        if(granumber.equals("00")){
            result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_NAME);
        }else{
            result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_GRANUMBER +","+COLUMN_ORDER + ","+ COLUMN_NAME);
        }


        result.moveToFirst();

        int count = result.getCount();
        if(count > 0){
            MemberViewItem item = null;
            String memberPic = "";

            for(int i=0; i < count; i++){
                item = new MemberViewItem();

                item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
                item.setMemberName(result.getString(result.getColumnIndex(COLUMN_NAME)));
                item.setBirthday(result.getString(result.getColumnIndex(COLUMN_BIRTHDAY)));
                item.setSunmoon(result.getString(result.getColumnIndex(COLUMN_SUNMOON)));
                item.setHandphone(result.getString(result.getColumnIndex(COLUMN_HANDPHONE)));
                item.setEmail(result.getString(result.getColumnIndex(COLUMN_EMAIL)));
                item.setComname(result.getString(result.getColumnIndex(COLUMN_COMNAME)));
                item.setDept(result.getString(result.getColumnIndex(COLUMN_DEPT)));
                item.setPosition(result.getString(result.getColumnIndex(COLUMN_POSITION)));
                item.setComphone(result.getString(result.getColumnIndex(COLUMN_COMPHONE)));
                item.setComAddress(result.getString(result.getColumnIndex(COLUMN_COMADDRESS)));
                item.setHomephone(result.getString(result.getColumnIndex(COLUMN_HOMEPHONE)));
                item.setHomeAddress(result.getString(result.getColumnIndex(COLUMN_HOMEADDRESS)));
                item.setFax(result.getString(result.getColumnIndex(COLUMN_FAX)));
                item.setBusiness(result.getString(result.getColumnIndex(COLUMN_BUSINESS)));
                item.setBusiname(result.getString(result.getColumnIndex(COLUMN_BUSINAME)));
                item.setGrannumber(result.getString(result.getColumnIndex(COLUMN_GRANUMBER)));
                item.setFav(result.getString(result.getColumnIndex(COLUMN_FAVORITE)));
                memberPic = "s"+item.getId();
                item.setMemberPicDrawable(ctx.getResources().getIdentifier(memberPic,"drawable",ctx.getPackageName()));

                returnVal.add(item);

                result.moveToNext();
            }
        }
        result.close();
        return returnVal;
    }

    public ArrayList<MemberViewItem> getMemberList(String serachWord,String granumber,String fav,String business){
        ArrayList<MemberViewItem> returnVal = new ArrayList<MemberViewItem>();

        String[] columns = {COLUMN_ID,COLUMN_NAME,COLUMN_BIRTHDAY,COLUMN_SUNMOON,COLUMN_HANDPHONE,COLUMN_EMAIL,
                            COLUMN_COMNAME,COLUMN_DEPT,COLUMN_POSITION,COLUMN_COMPHONE,COLUMN_COMADDRESS,
                            COLUMN_HOMEPHONE,COLUMN_HOMEADDRESS,COLUMN_FAX,COLUMN_BUSINESS,COLUMN_BUSINAME,COLUMN_GRANUMBER,COLUMN_FAVORITE,COLUMN_HOMEPAGE};

        String where = makeWhere(serachWord) + ") ";

        if(null != granumber && !granumber.isEmpty() && !granumber.equals("00")){

            where += " and " +   COLUMN_GRANUMBER + "='" + granumber + "'";
        }

        if(null != fav && !fav.isEmpty() && fav.equals("T")){
            where += " and " +   COLUMN_FAVORITE + "='T'";
        }

        if(null != business && !business.isEmpty()){
            //where += " and " +   COLUMN_BUSINESS + "='" + business + "'";
            where += " and " +   COLUMN_BUSINAME + " LIKE '%" + business + "%'";
        }

        where += " and " + COLUMN_GRANUMBER + " != '111' ";

        Log.e("zzangco","Where [" + where+"]");
        Cursor result = null;
        if(granumber.equals("00")){
            result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_NAME);
        }else{
            result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_GRANUMBER +","+COLUMN_ORDER + ","+ COLUMN_NAME);
        }


        result.moveToFirst();

        int count = result.getCount();
        if(count > 0){
            MemberViewItem item = null;
            String memberPic = "";

            for(int i=0; i < count; i++){
                item = new MemberViewItem();

                item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
                item.setMemberName(result.getString(result.getColumnIndex(COLUMN_NAME)));
                item.setBirthday(result.getString(result.getColumnIndex(COLUMN_BIRTHDAY)));
                item.setSunmoon(result.getString(result.getColumnIndex(COLUMN_SUNMOON)));
                item.setHandphone(result.getString(result.getColumnIndex(COLUMN_HANDPHONE)));
                item.setEmail(result.getString(result.getColumnIndex(COLUMN_EMAIL)));
                item.setComname(result.getString(result.getColumnIndex(COLUMN_COMNAME)));
                item.setDept(result.getString(result.getColumnIndex(COLUMN_DEPT)));
                item.setPosition(result.getString(result.getColumnIndex(COLUMN_POSITION)));
                item.setComphone(result.getString(result.getColumnIndex(COLUMN_COMPHONE)));
                item.setComAddress(result.getString(result.getColumnIndex(COLUMN_COMADDRESS)));
                item.setHomephone(result.getString(result.getColumnIndex(COLUMN_HOMEPHONE)));
                item.setHomeAddress(result.getString(result.getColumnIndex(COLUMN_HOMEADDRESS)));
                item.setFax(result.getString(result.getColumnIndex(COLUMN_FAX)));
                item.setBusiness(result.getString(result.getColumnIndex(COLUMN_BUSINESS)));
                item.setBusiname(result.getString(result.getColumnIndex(COLUMN_BUSINAME)));
                item.setGrannumber(result.getString(result.getColumnIndex(COLUMN_GRANUMBER)));
                item.setFav(result.getString(result.getColumnIndex(COLUMN_FAVORITE)));
                item.setHomePage(result.getString(result.getColumnIndex(COLUMN_HOMEPAGE)));
                memberPic = "s"+item.getId();
                item.setMemberPicDrawable(ctx.getResources().getIdentifier(memberPic,"drawable",ctx.getPackageName()));

                returnVal.add(item);

                result.moveToNext();
            }
        }
        result.close();
        return returnVal;
    }

    public boolean addFavorite(MemberViewItem item){
        ContentValues args = new ContentValues();

        String paramkeys = COLUMN_ID + "=?";
        String[] whereValue = {item.getId()};
        args.put(COLUMN_FAVORITE,"T");

        return mDB.update(TABLE_NAME_MEMBER,args,paramkeys,whereValue) > 0;
    }

    public boolean checkPhoneNumber(String number){
        String[] columns = {COLUMN_ID};
        String where = "REPLACE(" + COLUMN_HANDPHONE +",'-','') ='" + number + "'";

        Cursor result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_ID);
        result.moveToFirst();

        int count = result.getCount();

        result.close();

        if(count > 0){
            return true;
        }else{
            return false;
        }

    }

    public MemberViewItem getMemberInfo(String id){
        String[] columns = {COLUMN_ID,COLUMN_NAME,COLUMN_BIRTHDAY,COLUMN_SUNMOON,COLUMN_HANDPHONE,COLUMN_EMAIL,
                COLUMN_COMNAME,COLUMN_DEPT,COLUMN_POSITION,COLUMN_COMPHONE,COLUMN_COMADDRESS,
                COLUMN_HOMEPHONE,COLUMN_HOMEADDRESS,COLUMN_FAX,COLUMN_BUSINESS,COLUMN_BUSINAME,COLUMN_GRANUMBER,COLUMN_FAVORITE};

        String where = COLUMN_ID + "='" + id + "'";

        Log.e("zzangco","where ["+ where + "]");

        Cursor result = mDB.query(TABLE_NAME_MEMBER,columns,where,null,null,null,COLUMN_ID);
        result.moveToFirst();

        Log.e("zzangco","result count ["+ result.getCount() + "]");

        String memberPic = "";
        MemberViewItem item = new MemberViewItem();

        item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
        item.setMemberName(result.getString(result.getColumnIndex(COLUMN_NAME)));
        item.setBirthday(result.getString(result.getColumnIndex(COLUMN_BIRTHDAY)));
        item.setSunmoon(result.getString(result.getColumnIndex(COLUMN_SUNMOON)));
        item.setHandphone(result.getString(result.getColumnIndex(COLUMN_HANDPHONE)));
        item.setEmail(result.getString(result.getColumnIndex(COLUMN_EMAIL)));
        item.setComname(result.getString(result.getColumnIndex(COLUMN_COMNAME)));
        item.setDept(result.getString(result.getColumnIndex(COLUMN_DEPT)));
        item.setPosition(result.getString(result.getColumnIndex(COLUMN_POSITION)));
        item.setComphone(result.getString(result.getColumnIndex(COLUMN_COMPHONE)));
        item.setComAddress(result.getString(result.getColumnIndex(COLUMN_COMADDRESS)));
        item.setHomephone(result.getString(result.getColumnIndex(COLUMN_HOMEPHONE)));
        item.setHomeAddress(result.getString(result.getColumnIndex(COLUMN_HOMEADDRESS)));
        item.setFax(result.getString(result.getColumnIndex(COLUMN_FAX)));
        item.setBusiness(result.getString(result.getColumnIndex(COLUMN_BUSINESS)));
        item.setBusiname(result.getString(result.getColumnIndex(COLUMN_BUSINAME)));
        item.setGrannumber(result.getString(result.getColumnIndex(COLUMN_GRANUMBER)));
        item.setFav(result.getString(result.getColumnIndex(COLUMN_FAVORITE)));
        memberPic = "s"+item.getId();
        item.setMemberPicDrawable(ctx.getResources().getIdentifier(memberPic,"drawable",ctx.getPackageName()));

        result.close();
        return item;

    }
    public boolean removeFavorite(MemberViewItem item){
        ContentValues args = new ContentValues();

        String paramkeys = COLUMN_ID + "=?";
        String[] whereValue = {item.getId()};
        args.put(COLUMN_FAVORITE,"F");

        return mDB.update(TABLE_NAME_MEMBER,args,paramkeys,whereValue) > 0;
    }

    public boolean updateMemberInfo(MemberVo memberVo){
        ContentValues args = new ContentValues();
        args.put(COLUMN_BIRTHDAY,memberVo.getBirthday());
        args.put(COLUMN_SUNMOON,memberVo.getSunmoon());
        args.put(COLUMN_HANDPHONE,memberVo.getHandphone());
        args.put(COLUMN_EMAIL,memberVo.getEmail());
        args.put(COLUMN_COMNAME,memberVo.getComname());
        args.put(COLUMN_DEPT,memberVo.getDept());
        args.put(COLUMN_POSITION,memberVo.getPosition());
        args.put(COLUMN_COMPHONE,memberVo.getComphone());

        args.put(COLUMN_COMADDRESS,memberVo.getComaddress());
        args.put(COLUMN_HOMEPHONE,memberVo.getHomephone());
        args.put(COLUMN_HOMEADDRESS,memberVo.getHomeaddress());
        args.put(COLUMN_FAX,memberVo.getFax());
        args.put(COLUMN_BUSINESS,memberVo.getBusiness());
        args.put(COLUMN_BUSINAME,memberVo.getBusiname());
        args.put(COLUMN_GRANUMBER,memberVo.getGrannumber());

        String paramkeys = COLUMN_ID + "=?";
        String[] whereValue = {memberVo.getId()};

        Log.e("zzangco",COLUMN_HANDPHONE+"="+memberVo.getHandphone());
        Log.e("zzangco",COLUMN_ID+"="+memberVo.getId());
        return mDB.update(TABLE_NAME_MEMBER,args,paramkeys,whereValue) > 0;

    }
}

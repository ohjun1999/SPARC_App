package com.loysc.zzangco.kirikiri_snu.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.vo.ScheduleVo;

/**
 * Created by zzangco on 2017-11-23.
 */

public class ScheduleInfo {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CYCLE = "cycle";
    public static final String COLUMN_ANN_DATE = "annDate";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";

    public static final String TABLE_NAME_SCHEDULE = "schedule";

    private DataBase dataBase;
    private SQLiteDatabase mDB;
    private final Context ctx;

    public ScheduleInfo(Context ctx) {
        this.ctx = ctx;
    }

    public ScheduleInfo openc() throws SQLException {
        dataBase = DataBase.getInstance(ctx);
        mDB = dataBase.getReadableDatabase();

        return this;
    }
    public void close(){
        dataBase.close();
    }

    public List<ScheduleItem> getScheduleList(){
        List<ScheduleItem> returnList = new ArrayList<ScheduleItem>();

        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_CONTENT,COLUMN_LEVEL,COLUMN_TYPE,COLUMN_CYCLE,COLUMN_ANN_DATE};

        Cursor result = mDB.query(TABLE_NAME_SCHEDULE,columns,null,null,null,null,COLUMN_ANN_DATE+" asc");
        result.moveToFirst();

        int count = result.getCount();
        if(count > 0){
            ScheduleItem item = null;

            for(int i=0; i < count; i++){
                item = new ScheduleItem();
                item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
                item.setLevel(result.getString(result.getColumnIndex(COLUMN_LEVEL)));
                item.setCycle(result.getString(result.getColumnIndex(COLUMN_CYCLE)));
                item.setAnnDate(result.getString(result.getColumnIndex(COLUMN_ANN_DATE)));
                item.setType(result.getString(result.getColumnIndex(COLUMN_TYPE)));
                item.setTitle(result.getString(result.getColumnIndex(COLUMN_TITLE)));
                item.setContent(result.getString(result.getColumnIndex(COLUMN_CONTENT)));

                returnList.add(item);

                result.moveToNext();
            }
        }
        result.close();
        return returnList;
    }

    public ScheduleItem getScheduleItem(String id){
        ScheduleItem item = null;

        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_CONTENT,COLUMN_LEVEL,COLUMN_TYPE,COLUMN_CYCLE,COLUMN_ANN_DATE};
        String where = COLUMN_ID + "='" + id +"'";

        Cursor result = mDB.query(TABLE_NAME_SCHEDULE,columns,where,null,null,null,COLUMN_ANN_DATE+" asc");
        result.moveToFirst();

        int count = result.getCount();
        if(count > 0){
            item = new ScheduleItem();
            item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
            item.setLevel(result.getString(result.getColumnIndex(COLUMN_LEVEL)));
            item.setCycle(result.getString(result.getColumnIndex(COLUMN_CYCLE)));
            item.setAnnDate(result.getString(result.getColumnIndex(COLUMN_ANN_DATE)));
            item.setType(result.getString(result.getColumnIndex(COLUMN_TYPE)));
            item.setTitle(result.getString(result.getColumnIndex(COLUMN_TITLE)));
            item.setContent(result.getString(result.getColumnIndex(COLUMN_CONTENT)));
        }
        result.close();
        return item;
    }

    public boolean addSchedule(ScheduleVo scheduleVo){
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID,scheduleVo.getId());
        args.put(COLUMN_TITLE,scheduleVo.getTitle());
        args.put(COLUMN_CONTENT,scheduleVo.getContent());
        args.put(COLUMN_LEVEL,scheduleVo.getLevel());
        args.put(COLUMN_TYPE,scheduleVo.getType());
        args.put(COLUMN_CYCLE,scheduleVo.getCycle());
        args.put(COLUMN_ANN_DATE,scheduleVo.getAnnDate().replaceAll("-",""));
        try {
            mDB.insert(TABLE_NAME_SCHEDULE,null,args);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean updateSchedule(ScheduleVo scheduleVo){
        ContentValues args = new ContentValues();

        args.put(COLUMN_TITLE,scheduleVo.getTitle());
        args.put(COLUMN_CONTENT,scheduleVo.getContent());
        args.put(COLUMN_LEVEL,scheduleVo.getLevel());
        args.put(COLUMN_TYPE,scheduleVo.getType());
        args.put(COLUMN_CYCLE,scheduleVo.getCycle());
        args.put(COLUMN_ANN_DATE,scheduleVo.getAnnDate());
        try {
            mDB.update(TABLE_NAME_SCHEDULE,args,COLUMN_ID + "='" + scheduleVo.getId()+ "'",null);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteSchedule(ScheduleVo scheduleVo){
        try {
            mDB.delete(TABLE_NAME_SCHEDULE,COLUMN_ID + "='" + scheduleVo.getId()+ "'",null);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}

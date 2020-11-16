package com.loysc.zzangco.kirikiri_snu.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CheckInfo {
    public static final String TABLE_NAME_CHECK = "permiCheck";
    public static final String COLUMN_IS_CHECK = "ischeck";

    private DataBase dataBase;
    private SQLiteDatabase mDB;
    private final Context ctx;

    public CheckInfo(Context ctx){
        this.ctx = ctx;
    }

    public CheckInfo openc() throws SQLException {
        dataBase = DataBase.getInstance(ctx);
        mDB = dataBase.getReadableDatabase();

        return this;
    }

    public void close(){
        dataBase.close();
    }

    public String getCheck(){
        String returnVal = "N";
        String[] columns = {COLUMN_IS_CHECK};
        Cursor result = null;
        result = mDB.query(TABLE_NAME_CHECK,columns,null,null,null,null,null);

        result.moveToFirst();

        returnVal = result.getString(result.getColumnIndex(COLUMN_IS_CHECK));

        result.close();
        return returnVal;
    }

    public boolean setCheck(String ischeck){
        ContentValues args = new ContentValues();
        args.put(COLUMN_IS_CHECK,ischeck);
        return mDB.update(TABLE_NAME_CHECK,args,null,null) > 0;
    }
}

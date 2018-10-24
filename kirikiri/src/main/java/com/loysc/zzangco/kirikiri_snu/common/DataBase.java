package com.loysc.zzangco.kirikiri_snu.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by zzangco on 2017-11-07.
 */

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static DataBase instance;
    public static String dbName = "olck.db";
    private static int dbVersion = 2;

    private Context mContext;

    public static DataBase getInstance(Context context){
        if(instance == null){
            instance = new DataBase(context,dbName,null,dbVersion);
        }
        Log.e("zzangco","DataBase");
        return instance;
    }

    private DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void getDataBase(){
        db = getWritableDatabase();
    }
}

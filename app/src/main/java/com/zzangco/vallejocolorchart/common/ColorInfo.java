package com.zzangco.vallejocolorchart.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.zzangco.vallejocolorchart.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzangco on 2017-01-12.
 */

public class ColorInfo {
    private DataBase database;
    private SQLiteDatabase mDB;
    private final Context ctx;

    public  static final String TB_COLORINFO    = "colorinfo";
    public static final String COL_COMPANY      = "company";
    public static final String COL_TYPE         = "type";
    public static final String COL_CODE         = "code";
    public static final String COL_SUBCODE      = "subcode";
    public static final String COL_COLORNAME    = "colorname";
    public static final String COL_HANCOLORNAME = "hancolorname";
    public static final String COL_ETC             = "etc";
    public static final String COL_COUNT        = "count";

    public static final String VALLEJO = "vallejo";
    public static final String MODELCOLOR = "ModelColor";
    public static final String MODELAIR = "ModelAir";
    public static final String GAMECOLOR = "GameColor";
    public static final String GAMEAIR = "GameAir";
    public static final String WASH = "ModelWash";
    public static final String PANZER = "Panzer";

    public static final String TAMIYA = "tamiya";
    public static final String X = "X";
    public static final String XF = "XF";

    public static final String MODELMASTER = "modelmaster";
    public static final String AFV = "AFV";
    public static final String BASICCOLOR = "BasicColor";
    public static final String FSCOLOR = "FSColor";
    public static final String MARINECOLOR = "MarineColor";
    public static final String WWII = "WWII";

    public ColorInfo(Context ctx) {
        this.ctx = ctx;
    }

    public ColorInfo openc() throws SQLException{
        database = DataBase.getInstance(ctx);
        mDB = database.getReadableDatabase();

        return this;
    }

    public void close(){
        database.close();
    }

    public ArrayList<ListViewItem> getColorList(String company,String type,String colorName){
        ArrayList<ListViewItem> returnVal = new ArrayList<ListViewItem>();

        String[] columns = {COL_COMPANY,COL_TYPE,COL_CODE,COL_SUBCODE,COL_COLORNAME,COL_HANCOLORNAME,COL_ETC,COL_COUNT};
        String paramKey = COL_COMPANY + " LIKE '%" + company + "%'";

        if(null != type && !"All".equals(type)){

            paramKey += " AND " + COL_TYPE + " = '" + type + "'";

        }
        if(null != colorName){
            paramKey += " AND (" + COL_COLORNAME + " LIKE '%" + colorName + "%'";
            paramKey += " OR " + COL_HANCOLORNAME + " LIKE '%" + colorName + "%'";
            paramKey += " OR " + COL_CODE + " LIKE '%" + colorName + "%'";
            paramKey += " OR " + COL_SUBCODE + " LIKE '%" + colorName + "%' )";
        }

        Log.e("zzangco","paramKey=[" + paramKey + "]");
        Cursor result = mDB.query(TB_COLORINFO,columns,paramKey,null,null,null,COL_CODE);

        result.moveToFirst();

        int count = result.getCount();

        if(count > 0){
            ListViewItem item = null;
            int ImageID = 0;
            String imgName = "";
            /*String preWord = "";

            if(company.equals(VALLEJO)){
                preWord = "vm";

                if(type.equals(MODELCOLOR)){
                    preWord += "c";
                }else if(type.equals(MODELAIR)){
                    preWord += "a";
                }else if(type.equals(GAMECOLOR)){
                    preWord += "g";
                }else if(type.equals(GAMEAIR)){
                    preWord += "ga";
                }else if(type.equals(WASH)){
                    preWord += "w";
                }else if(type.equals(PANZER)){
                    preWord += "p";
                }
            }else if(company.equals(TAMIYA)){
                if(type.equals(X)){
                    preWord += "X";
                }else if(type.equals(XF)){
                    preWord += "XF";
                }
            }*/


            for(int i=0; i < count; i++){
                item = new ListViewItem();
                item.setTvColorCode(result.getString(result.getColumnIndex(COL_SUBCODE)));
                item.setTvColorName(result.getString(result.getColumnIndex(COL_COLORNAME)));
                item.setTvColorOrder(result.getString(result.getColumnIndex(COL_CODE)));
                item.setEtStock(result.getString(result.getColumnIndex(COL_COUNT)));
                item.setCompany(result.getString(result.getColumnIndex(COL_COMPANY)));
                item.setType(result.getString(result.getColumnIndex(COL_TYPE)));

                imgName = makePrefix(item.getCompany(),item.getType()) + result.getString(result.getColumnIndex(COL_CODE));

                ImageID = ctx.getResources().getIdentifier(imgName,"drawable",ctx.getPackageName());

                Log.e("zzangco","ImageID=[" + imgName + "]");

                item.setColorDrawable(ctx.getDrawable(ImageID));

                returnVal.add(item);
                result.moveToNext();
            }
        }

        return returnVal;
    }

    private String makePrefix(String company,String type){
        String preWord = "";
        if(company.equals(VALLEJO)){
            preWord = "vm";

            if(type.equals(MODELCOLOR)){
                preWord += "c";
            }else if(type.equals(MODELAIR)){
                preWord += "a";
            }else if(type.equals(GAMECOLOR)){
                preWord += "g";
            }else if(type.equals(GAMEAIR)){
                preWord += "ga0";
            }else if(type.equals(WASH)){
                preWord += "w5";
            }else if(type.equals(PANZER)){
                preWord += "p3";
            }
        }else if(company.equals(TAMIYA)){
            if(type.equals(X)){
                preWord += "x";
            }else if(type.equals(XF)){
                preWord += "xf";
            }
        }else if(company.equals(MODELMASTER)){
            /*if(type.equals(AFV)){
                preWord += "X";
            }else if(type.equals(BASICCOLOR)){
                preWord += "XF";
            }else if(type.equals(FSCOLOR)){
                preWord += "XF";
            }else if(type.equals(MARINECOLOR)){
                preWord += "XF";
            }else if(type.equals(WWII)){
                preWord += "XF";
            }*/
            preWord += "mmac";
        }

        return preWord;
    }

    public boolean setCount(ListViewItem item){
        ContentValues args = new ContentValues();
        String paramkeys = COL_COMPANY + "='" + item.getCompany() + "' "
                          + "AND " + COL_TYPE + "='" + item.getType() + "' "
                          + "AND " + COL_CODE + "='" + item.getTvColorOrder() + "' ";
        args.put(COL_COUNT,item.getEtStock());
        return mDB.update(TB_COLORINFO,args,paramkeys,null) > 0;
    }

}

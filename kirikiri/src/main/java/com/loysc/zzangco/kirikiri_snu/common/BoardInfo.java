package com.loysc.zzangco.kirikiri_snu.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.vo.BoardVo;

/**
 * Created by zzangco on 2017-11-21.
 */

public class BoardInfo {
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_IMPORTANT = "important";
    public static final String COLUMN_CREATEDAY = "createday";
    public static final String COLUMN_READ_OK = "readok";
    public static final String COLUMN_TAG = "tag";

    public static final String TABLE_NAME_BOARD = "borad";

    private DataBase dataBase;
    private SQLiteDatabase mDB;
    private final Context ctx;

    public BoardInfo(Context ctx) {
        this.ctx = ctx;
    }

    public BoardInfo openc() throws SQLException {
        dataBase = DataBase.getInstance(ctx);
        mDB = dataBase.getReadableDatabase();

        return this;
    }

    public void close(){
        dataBase.close();
    }

    public List<BoardItem> getBoardList(String readOK){
        List<BoardItem> returnList = new ArrayList<BoardItem>();

        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_CONTENT,COLUMN_IMPORTANT,COLUMN_READ_OK,COLUMN_CREATEDAY,COLUMN_TAG};
        String where = "1=1";

        if(null != readOK && "T".equals(readOK)){
            where += " and " + COLUMN_READ_OK + " is null or " + COLUMN_READ_OK + " not in('Y')";
        }else if(null != readOK && "F".equals(readOK)){
            where += " and " + COLUMN_READ_OK + " ='Y'";
        }else if(null != readOK && "M".equals(readOK)){
            where += " and " + COLUMN_TAG + " ='OLC 산악회'";
        }else if(null != readOK && "C".equals(readOK)){
            where += " and " + COLUMN_TAG + " ='OLC 씨네'";
        }else if(null != readOK && "O".equals(readOK)){
            where += " and " + COLUMN_TAG + " ='OLC 오페라'";
        }else if(null != readOK && "J".equals(readOK)){
            where += " and " + COLUMN_TAG + " ='OLP 저널'";
        }

        Cursor result = mDB.query(TABLE_NAME_BOARD,columns,where,null,null,null,COLUMN_IMPORTANT + " desc," + COLUMN_CREATEDAY + " desc");

        result.moveToFirst();

        int count = result.getCount();
        Log.e("zzaangco","BoardInfo count :" + count);
        if(count > 0){
            BoardItem item = null;

            for(int i=0; i < count; i++){
                item = new BoardItem();
                item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
                item.setTitle(result.getString(result.getColumnIndex(COLUMN_TITLE)));
                item.setContent(result.getString(result.getColumnIndex(COLUMN_CONTENT)));
                item.setImportant(result.getString(result.getColumnIndex(COLUMN_IMPORTANT)));
                item.setReadOK(result.getString(result.getColumnIndex(COLUMN_READ_OK)));
                item.setCreateday(result.getString(result.getColumnIndex(COLUMN_CREATEDAY)));
                item.setTag(result.getString(result.getColumnIndex(COLUMN_TAG)));
                returnList.add(item);

                result.moveToNext();
            }
        }
        result.close();
        return returnList;
    }

    public List<String> getTag(){
        List<String> returnList = new ArrayList<String>();
        String[] columns = {COLUMN_TAG};
        Cursor result = mDB.query(TABLE_NAME_BOARD,columns,"tag IS NOT NULL",null,COLUMN_TAG,null,null);

        result.moveToFirst();
        int count = result.getCount();

        if(count > 0){
            for(int i=0; i < count; i++){
                returnList.add(result.getString(result.getColumnIndex(COLUMN_TAG)));
                result.moveToNext();
            }
        }

        result.close();
        return returnList;
    }

    public BoardItem getBoardItem(String id){
        String[] columns = {COLUMN_ID,COLUMN_TITLE,COLUMN_CONTENT,COLUMN_IMPORTANT,COLUMN_READ_OK,COLUMN_CREATEDAY};
        String where = COLUMN_ID + "='" + id + "'";
        //COLUMN_IMPORTANT + " desc," + COLUMN_CREATEDAY + " desc"
        Cursor result = mDB.query(TABLE_NAME_BOARD,columns,where,null,null,null,COLUMN_IMPORTANT + " desc");

        result.moveToFirst();

        BoardItem item = null;
        item = new BoardItem();
        item.setId(result.getString(result.getColumnIndex(COLUMN_ID)));
        item.setTitle(result.getString(result.getColumnIndex(COLUMN_TITLE)));
        item.setContent(result.getString(result.getColumnIndex(COLUMN_CONTENT)));
        item.setImportant(result.getString(result.getColumnIndex(COLUMN_IMPORTANT)));
        item.setReadOK(result.getString(result.getColumnIndex(COLUMN_READ_OK)));
        item.setCreateday(result.getString(result.getColumnIndex(COLUMN_CREATEDAY)));

        result.close();
        return item;
    }

    public boolean addBoard(BoardItem item){
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID,item.getId());
        args.put(COLUMN_TITLE,item.getTitle());
        args.put(COLUMN_CONTENT,item.getContent());
        args.put(COLUMN_IMPORTANT,item.getImportant());
        args.put(COLUMN_CREATEDAY,item.getCreateday());
        args.put(COLUMN_READ_OK,"N");

        try {
            mDB.insert(TABLE_NAME_BOARD,null,args);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean addBoard(BoardVo boardVo){
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID,boardVo.getId());
        args.put(COLUMN_TITLE,boardVo.getTitle());
        args.put(COLUMN_CONTENT,boardVo.getContent());
        args.put(COLUMN_IMPORTANT,boardVo.getImportant());
        args.put(COLUMN_CREATEDAY,boardVo.getCreateday().replaceAll("-",""));
        args.put(COLUMN_READ_OK,"N");

        if(boardVo.getTitle().indexOf("[") > -1){
            args.put(COLUMN_TAG,boardVo.getTitle().substring(boardVo.getTitle().indexOf("[")+1, boardVo.getTitle().indexOf("]")));
        }

        try {
            mDB.insert(TABLE_NAME_BOARD,null,args);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean updateBoard(BoardVo boardVo){
        ContentValues args = new ContentValues();

        args.put(COLUMN_TITLE,boardVo.getTitle());
        args.put(COLUMN_CONTENT,boardVo.getContent());
        args.put(COLUMN_IMPORTANT,boardVo.getImportant());
        args.put(COLUMN_CREATEDAY,boardVo.getCreateday());
        args.put(COLUMN_READ_OK,"N");
        Log.e("zzangco","수정 공지사항="+boardVo.getId());
        try {
            mDB.update(TABLE_NAME_BOARD,args,COLUMN_ID + "='" + boardVo.getId()+"'",null);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean deleteBoard(BoardVo boardVo){
        try {
            mDB.delete(TABLE_NAME_BOARD,COLUMN_ID + "='" + boardVo.getId()+"'",null);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean readBoard(BoardItem item){
        ContentValues args = new ContentValues();
        args.put(COLUMN_READ_OK,"Y");

        String paramkeys = COLUMN_ID + "=?";
        String[] whereValue = {item.getId()};
        return mDB.update(TABLE_NAME_BOARD,args,paramkeys,whereValue) > 0;
    }
}

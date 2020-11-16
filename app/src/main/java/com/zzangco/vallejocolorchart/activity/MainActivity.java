package com.zzangco.vallejocolorchart.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zzangco.vallejocolorchart.R;
import com.zzangco.vallejocolorchart.common.DataBase;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TimerTask mTask;
    private Timer mTimer;

    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("zzangco","isCheckDB() = " + isCheckDB());

        startView();

    }

    private void startView(){
        if(!isCheckDB()){
            copyDB();
        }

        mTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                finish();
            }
        };

        mTimer = new Timer();

        mTimer.schedule(mTask,5000);
    }
    private boolean isCheckDB(){
        String filePath = "/data/data/" + getApplication().getPackageName() + "/databases/" + DataBase.dbName;
        File file = new File(filePath);

        return file.exists();
    }

    private void copyDB(){
        AssetManager assetManager = getApplication().getAssets();

        String folderPath = "/data/data/" + getApplication().getPackageName() + "/databases/";
        String filePath = folderPath + DataBase.dbName;

        File folder = new File(folderPath);
        File file = new File(filePath);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        Log.e("zzangco","copyDB folderPath = " + filePath);
        try {
            InputStream is = assetManager.open(DataBase.dbName);
            BufferedInputStream bis = new BufferedInputStream(is);


            if(!folder.exists()){
                folder.mkdirs();
                Log.e("zzangco","copyDB folder = exists:false ");
            }

            if(file.exists()){
                file.delete();
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            int read = -1;
            byte[] buffer = new byte[1024];

            while( (read = bis.read(buffer,0,1024)) != -1 ){
                bos.write(buffer,0,read);
            }
            bos.flush();

            bos.close();;
            fos.close();
            bis.close();
            is.close();
        } catch (Exception e) {
            Log.e("zzangco","error = " + e.getMessage());
        }
    }
    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }
}

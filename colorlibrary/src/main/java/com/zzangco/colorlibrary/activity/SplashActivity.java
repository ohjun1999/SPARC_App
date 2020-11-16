package com.zzangco.colorlibrary.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zzangco.colorlibrary.R;
import com.zzangco.colorlibrary.common.DataBase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startView();

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Intent intent = new Intent(getApplicationContext(),ColorListActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0,3000);



    }

    private void startView(){
        if(!isCheckDB()){
            copyDB();
        }
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
}

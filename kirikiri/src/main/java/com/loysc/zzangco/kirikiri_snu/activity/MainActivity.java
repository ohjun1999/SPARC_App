package com.loysc.zzangco.kirikiri_snu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.loysc.zzangco.kirikiri_snu.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;

import com.loysc.zzangco.kirikiri_snu.common.BackPressCloseHandler;
import com.loysc.zzangco.kirikiri_snu.common.DataBase;
import com.loysc.zzangco.kirikiri_snu.common.ZZangcoUtility;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnection;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnectionNoHandler;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnectionThread;

public class MainActivity extends AppCompatActivity implements HttpConnectionThread {

    private ImageView imgOLCA,imgAlarm,imgSchedule,imgOLCF,imgOLCS,imgBand;
    private TextView tvHome,tvReload,tvConnect,tvEnd;
    private ViewFlipper vfSlider;
    private ImageView imgBanner1,imgBanner2,imgBanner3,imgBanner4,imgBanner5;

    private HttpConnection httpConnection;
    private HttpConnectionNoHandler httpConnectionNoHandler;

    ProgressDialog asyncDialog;

    public static MainActivity instance;

    private Animation slide_in_left,slide_out_right;
    private Animation slide_out_left,slide_in_right;

    private BackPressCloseHandler backPressCloseHandler;

    private String myNumber = null;

    public static final String USER_PHONE_NUM = "userPhoneNum";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myNumber = getIntent().getStringExtra(USER_PHONE_NUM);
        instance = this;

        imgOLCA     = (ImageView)findViewById(R.id.imgOLCA);
        imgAlarm    = (ImageView)findViewById(R.id.imgAlarm);
        imgSchedule = (ImageView)findViewById(R.id.imgSchedule);
        imgOLCF     = (ImageView)findViewById(R.id.imgOLCF);
        imgOLCS     = (ImageView)findViewById(R.id.imgOLCS);
        imgBand     = (ImageView)findViewById(R.id.imgBand);

        tvHome      = (TextView)findViewById(R.id.tvHome);
        tvReload    = (TextView)findViewById(R.id.tvReload);
        tvConnect   = (TextView)findViewById(R.id.tvConnect);
        tvEnd       = (TextView)findViewById(R.id.tvEnd);

        vfSlider = (ViewFlipper)findViewById(R.id.vfSlider);
        imgBanner1 = (ImageView)findViewById(R.id.imgBanner1);
        imgBanner2 = (ImageView)findViewById(R.id.imgBanner2);
        imgBanner3 = (ImageView)findViewById(R.id.imgBanner3);
        imgBanner4 = (ImageView)findViewById(R.id.imgBanner4);
        imgBanner5 = (ImageView)findViewById(R.id.imgBanner5);
        //imgBanner6 = (ImageView)findViewById(R.id.imgBanner6);
        TextView tvPerRule = findViewById(R.id.tvPerRule);
        tvPerRule.setOnClickListener(this::onPerRule);

        slide_in_left = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left );
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right );

        slide_out_left = AnimationUtils.loadAnimation(this,R.anim.ani_translate_l );
        slide_in_right = AnimationUtils.loadAnimation(this,R.anim.ani_translate_r );

        imgOLCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog = ProgressDialog.show(MainActivity.this,"","잠시만 기다려 주세요.",true);
                Intent intent = new Intent(getApplicationContext(),MemberListActivity.class);
                startActivity(intent);
            }
        });

        imgAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);
                startActivity(intent);
                asyncDialog = ProgressDialog.show(MainActivity.this,"","잠시만 기다려 주세요.",true);
            }
        });
        imgSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncDialog = ProgressDialog.show(MainActivity.this,"","잠시만 기다려 주세요.",true);
                Intent intent = new Intent(getApplicationContext(),ScheduleActivity.class);
                startActivity(intent);

            }
        });
        imgOLCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OlcActivity.class);
                startActivity(intent);
                asyncDialog = ProgressDialog.show(MainActivity.this,"","잠시만 기다려 주세요.",true);
            }
        });
        imgOLCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OlpActivity.class);
                startActivity(intent);
                asyncDialog = ProgressDialog.show(MainActivity.this,"","잠시만 기다려 주세요.",true);
            }
        });

        imgBand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // backupDB();
                try {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.nhn.android.band");
                    startActivity(intent);
                }catch (Exception e){
                    String url = "market://details?id=" + "com.nhn.android.band";
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });


        imgBanner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.sftc_url)));
                startActivity(intent);
            }
        });
        imgBanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.storant_url)));
                startActivity(intent);
            }
        });
        imgBanner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.orient_url)));
                startActivity(intent);
            }
        });
        imgBanner4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.inettv_url)));
                startActivity(intent);
            }
        });
        imgBanner5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gjec_url)));
                startActivity(intent);
            }
        });


        vfSlider.setInAnimation(slide_in_right);
        vfSlider.setOutAnimation(slide_out_left);

        backPressCloseHandler = new BackPressCloseHandler(this);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);
                sendUserToken(newToken);
            }
        });
    }


    private void backupDB(){
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + "com.loysc.zzangco.olc_mobile" + "//databases//" + DataBase.dbName;
                String backupDBPath = DataBase.dbName;
                File currentDB = new File(data, currentDBPath);
                // File backupDB = new File("//storage//emulated//legacy//", backupDBPath);
                File backupDB = new File("//storage//self//primary//", backupDBPath);

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

                try {
                    int i = changePermissons(backupDB,0777);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Backup Successful!",Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            //e.getStackTrace();
            //Toast.makeText(getApplicationContext(), "Backup Failed!", Toast.LENGTH_SHORT).show();
        }
    }
    private void sendUserToken(String token){

        String url = getString(R.string.mainUrl);
        url += getString(R.string.addUserToken);
        url += "?" + ZZangcoUtility.CHECKUSER_PARAM + "=" + myNumber;
        url += "&" + ZZangcoUtility.USER_TOKEN + "=" + token;
        //url = "https://www.daum.net/";
        Log.e("zzangco","user Check ["+url+"]");
        httpConnection = new HttpConnection(this, url,HttpConnection.URLTYPE.SEND);
        httpConnection.start();
    }
    public int changePermissons(File path, int mode) throws Exception {
        Class<?> fileUtils = Class.forName("android.os.FileUtils");
        Method setPermissions = fileUtils.getMethod("setPermissions",
                String.class, int.class, int.class, int.class);

        return (Integer) setPermissions.invoke(null, path.getAbsolutePath(),
                mode, -1, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int firstViewInt = ZZangcoUtility.randomRange(0,2);

        vfSlider.setDisplayedChild(firstViewInt);
        vfSlider.startFlipping();

        vfSlider.setFlipInterval(2500);
    }

    @Override
    public void onBackPressed() {
        Log.e("zzangco","닫기");

        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vfSlider.stopFlipping();
    }
    private void onPerRule(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://immyth.egloos.com/7503625"));
        startActivity(intent);
    }
    @Override
    public void afterThread(String result) {

    }
}

package com.loysc.zzangco.kirikiri_snu.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.ActivityResult;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.google.gson.Gson;
import com.loysc.zzangco.kirikiri_snu.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.common.BoardInfo;
import com.loysc.zzangco.kirikiri_snu.common.CheckInfo;
import com.loysc.zzangco.kirikiri_snu.common.DataBase;
import com.loysc.zzangco.kirikiri_snu.common.MemberInfo;
import com.loysc.zzangco.kirikiri_snu.common.ScheduleInfo;
import com.loysc.zzangco.kirikiri_snu.common.ZZangcoUtility;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnection;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnectionNoHandler;
import com.loysc.zzangco.kirikiri_snu.connect.HttpConnectionThread;
import com.loysc.zzangco.kirikiri_snu.vo.BoardVo;
import com.loysc.zzangco.kirikiri_snu.vo.MemberReadVo;
import com.loysc.zzangco.kirikiri_snu.vo.MemberVo;
import com.loysc.zzangco.kirikiri_snu.vo.ResultVo;
import com.loysc.zzangco.kirikiri_snu.vo.ScheduleVo;

import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;
import static com.google.android.play.core.install.model.UpdateAvailability.UPDATE_AVAILABLE;
import static com.loysc.zzangco.kirikiri_snu.activity.MainActivity.USER_PHONE_NUM;

public class SplashActivity extends AppCompatActivity implements HttpConnectionThread {

    private AppUpdateManager appUpdateManager;

    private static final int MY_REQUEST_CODE = 17326;

    private ImageView imgSplash;

    private Animation animationTL;

    private Pair<View, String> pair1;

    private MemberInfo memberInfo;

    private BoardInfo boardInfo;

    private CheckInfo checkinfo;

    private ScheduleInfo scheduleInfo;

    private HttpConnection httpConnection;
    private NonLeakHandler handler = new NonLeakHandler(this);

    String myNumber = null;

    String userConfirm = null;

    private static final int MY_PERMISSIONS_CALL_PHONE = 0;
    public static int USER_CONFIRM = 7;
    public static String IS_OK = "isOk";

    private static final class NonLeakHandler extends Handler {
        private final WeakReference<SplashActivity> ref;

        NonLeakHandler(SplashActivity act) {
            ref = new WeakReference<>(act);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity act = ref.get();
            if (act != null) {
                Intent intent = new Intent(act.getApplicationContext(), MainActivity.class);
                intent.putExtra(USER_PHONE_NUM, act.myNumber);
                //startActivity(intent,option.toBundle());
                act.startActivity(intent);
                act.finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkUpdate();
        startWork();
        imgSplash = findViewById(R.id.imgSplash);

        animationTL = AnimationUtils.loadAnimation(this, R.anim.ani_translate_r);

        imgSplash.setVisibility(View.VISIBLE);
        imgSplash.startAnimation(animationTL);

        //Glide.with(this).load(R.drawable.kirikiri).into(imgSplash);


        pair1 = Pair.create(findViewById(R.id.imgSplash), "mainImg");

        getCheckDatastore();
        checkinfo.openc();
        userConfirm = checkinfo.getCheck();
        checkinfo.close();


        if (userConfirm.equals("Y")) {
            goProgress();
        } else {
            Intent intent = new Intent(getApplicationContext(), InAppInfoActivity.class);
            startActivityForResult(intent, USER_CONFIRM);
        }
    }

    private void checkUpdate() {
        InstallStateUpdatedListener listner = new InstallStateUpdatedListener() {
            @Override
            public void onStateUpdate(InstallState installState) {
                if (installState.installStatus() == InstallStatus.DOWNLOADED) {
                    Log.d("InstallDownloded", "InstallStatus sucsses");
                    notifyUser();
                }
            }
        };

        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(listner);

        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(IMMEDIATE)) {
                    requestUpdate(appUpdateInfo);
                    Log.d("UpdateAvailable", "update is there ");
                } else if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    Log.d("Update", "3");
                    notifyUser();
                } else {
                    Toast.makeText(SplashActivity.this, "No Update Available", Toast.LENGTH_SHORT).show();
                    Log.e("?????? ??????????????? ????????????.", "update is not there");
                }

//            }
        });

    }

    private void requestUpdate(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(appUpdateInfo, IMMEDIATE, this, MY_REQUEST_CODE);
            onResume();
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       
        if (resultCode == RESULT_OK) {
            if (requestCode == USER_CONFIRM) {
                boolean returnValue = data.getBooleanExtra(IS_OK, false);
                if (returnValue) {
                    getCheckDatastore();
                    checkinfo.openc();
                    checkinfo.setCheck("Y");
                    checkinfo.close();

                    permissonCheckFu();
                } else {
                    getCheckDatastore();
                    checkinfo.openc();
                    checkinfo.setCheck("N");
                    checkinfo.close();

                    finish();
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            appUpdateManager.unregisterListener((InstallStateUpdatedListener) this);
        } catch (RuntimeException e) {


        }
    }

    private void notifyUser() {

        Snackbar snackbar =
                Snackbar.make(findViewById(R.id.root),
                        "An update has just been downloaded.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("RESTART", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(
                        appUpdateInfo -> {

                            if (appUpdateInfo.updateAvailability()
                                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                                // If an in-app update is already running, resume the update.
                                try {
                                    appUpdateManager.startUpdateFlowForResult(
                                            appUpdateInfo,
                                            IMMEDIATE,
                                            this,
                                            MY_REQUEST_CODE);
                                } catch (IntentSender.SendIntentException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
    }
   /* private void resume() {
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
           *//* @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {*//*
                if (appUpdateInfo.updateAvailability()*//*appUpdateInfo.installStatus()*//* == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                    // If an in-app update is already running, resume the update.
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            IMMEDIATE,
                            this,
                            MY_REQUEST_CODE);
                }
        });
            *//* == InstallStatus.DOWNLOADED) {
                    notifyUser();
                }
//            }
        });*//*
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_CALL_PHONE) {
            goProgress();
        }
    }

    @SuppressLint("HardwareIds")
    private void goProgress() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager mgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (mgr != null) {
//                myNumber = mgr.getLine1Number();
//                myNumber = myNumber.replace("+82", "0");
//                myNumber = myNumber.replace("-", "");
//                myNumber = myNumber.trim();
            }


        }

        if (checkPhoneNumber(myNumber)) {
            //handler.sendEmptyMessageDelayed(0,3000);
            String url = getString(R.string.mainUrl);
            url += getString(R.string.getInfo);
            url += "?" + ZZangcoUtility.CHECKUSER_PARAM + "=" + myNumber;

            Log.e("zzangco", "testURL=[" + url + "]");
            httpConnection = new HttpConnection(this, url, HttpConnection.URLTYPE.SEND);
            httpConnection.start();
            Log.e("zzangco", "???????????? ??????");
        } else {
            // handler.sendEmptyMessageDelayed(0,3000);
            Log.e("zzangco", "phoneNumber [" + myNumber + "]");
            Log.e("zzangco", "????????? ?????????");

            String url = getString(R.string.mainUrl);
            url += getString(R.string.checkUser);
            url += "?" + ZZangcoUtility.CHECKUSER_PARAM + "=" + myNumber;
            //url = "https://www.daum.net/";
            Log.e("zzangco", "user Check [" + url + "]");
            httpConnection = new HttpConnection(this, url, HttpConnection.URLTYPE.SEND);
            httpConnection.start();

        }
    }

    private void permissonCheckFu() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_CALL_PHONE);
            }
        }


    }

    private void startWork() {
        if (!isCheckDB()) {
            copyDB();
        }
    }

    private boolean checkPhoneNumber(String number) {
        getDatastore();
        memberInfo.openc();


        return memberInfo.checkPhoneNumber(number);


        //return true;
    }

    private boolean updateMemberInfo(MemberVo memberVo) {
        getDatastore();
        memberInfo.openc();

        return memberInfo.updateMemberInfo(memberVo);
    }

    private MemberInfo getDatastore() {
        if (null == memberInfo) {
            memberInfo = new MemberInfo(getApplication());
        }

        return memberInfo;
    }

    private BoardInfo getBoardDataStore() {
        if (null == boardInfo) {
            boardInfo = new BoardInfo(getApplication());
        }

        return boardInfo;
    }

    private void getCheckDatastore() {
        if (null == checkinfo) {
            checkinfo = new CheckInfo(getApplication());
        }
    }

    private boolean addBoard(BoardVo boardVo) {
        getBoardDataStore();
        boardInfo.openc();

        return boardInfo.addBoard(boardVo);

    }

    private boolean updateBoard(BoardVo boardVo) {
        getBoardDataStore();
        boardInfo.openc();

        return boardInfo.updateBoard(boardVo);

    }

    private boolean deleteBoard(BoardVo boardVo) {
        getBoardDataStore();
        boardInfo.openc();

        return boardInfo.deleteBoard(boardVo);

    }

    private boolean addSchedule(ScheduleVo scheduleVo) {
        getScheduleDataStore();
        scheduleInfo.openc();

        return scheduleInfo.addSchedule(scheduleVo);

    }

    private boolean updateSchedule(ScheduleVo scheduleVo) {
        getScheduleDataStore();
        scheduleInfo.openc();

        return scheduleInfo.updateSchedule(scheduleVo);

    }

    private boolean deleteSchedule(ScheduleVo deleteSchedule) {
        getScheduleDataStore();
        scheduleInfo.openc();

        return scheduleInfo.deleteSchedule(deleteSchedule);

    }

    private ScheduleInfo getScheduleDataStore() {
        if (null == scheduleInfo) {
            scheduleInfo = new ScheduleInfo(getApplication());
        }
        return scheduleInfo;
    }

    private boolean isCheckDB() {
        String filePath = "/data/data/" + getApplication().getPackageName() + "/databases/" + DataBase.dbName;
        File file = new File(filePath);

        return file.exists();
    }

    private void copyDB() {
        AssetManager assetManager = getApplication().getAssets();

        String folderPath = "/data/data/" + getApplication().getPackageName() + "/databases/";
        String filePath = folderPath + DataBase.dbName;

        File folder = new File(folderPath);
        File file = new File(filePath);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        Log.e("zzangco", "copyDB folderPath = " + filePath);
        try {
            InputStream is = assetManager.open(DataBase.dbName);
            BufferedInputStream bis = new BufferedInputStream(is);


            if (!folder.exists()) {
                folder.mkdirs();
                Log.e("zzangco", "copyDB folder = exists:false ");
            }

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            int read = -1;
            byte[] buffer = new byte[1024];

            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            bos.flush();

            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (Exception e) {
            Log.e("zzangco", "error = " + e.getMessage());
        }
    }

    @Override
    public void afterThread(String result) {
        try {
            JSONObject jo = new JSONObject(result);

            if (jo.getString("result").equals("Y")) {
                String url = getString(R.string.mainUrl);
                url += getString(R.string.getInfo);
//                url += "?" + ZZangcoUtility.CHECKUSER_PARAM + "=" + myNumber;

                httpConnection = new HttpConnection(this, url, HttpConnection.URLTYPE.SEND);
                httpConnection.start();
            } else if (jo.getString("result").equals("N")) {
                handler = null;
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                //startActivity(intent,option.toBundle());
                intent.putExtra(PopupActivity.TITLE, "SPARC ?????? ??????");
                intent.putExtra(PopupActivity.CONTENT, "SPARC ????????? ?????? ?????? ????????????. \n SPARC ?????????(02-880-6251)?????? ?????? ??????????????????.");
                startActivity(intent);
                finish();
            } else {
                ResultVo resutlVo = new Gson().fromJson(result, ResultVo.class);
                MemberReadVo[] array = new Gson().fromJson(resutlVo.getResult(), MemberReadVo[].class);
                List<MemberReadVo> list = Arrays.asList(array);

                Log.e("zzangco", "count:" + list.size());
                updateInfo(list);
            }
            //String urlType = jo.getString("UrlType");


        } catch (Exception e) {
            Log.e("zzangco", "error:" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != handler) {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    }

    private void updateInfo(List<MemberReadVo> list) {
        for (MemberReadVo memberReadVo : list) {
            boolean returnVal = false;

            Log.e("zzangco", "memberReadVo id:" + memberReadVo.getId());
            Log.e("zzangco", "memberReadVo DataType:" + memberReadVo.getDataType());
            Log.e("zzangco", "memberReadVo Content:" + memberReadVo.getContent());

            if (memberReadVo.getDataType().equals("00")) { //????????????
                MemberVo memberVo = new Gson().fromJson(memberReadVo.getContent(), MemberVo.class);
                returnVal = updateMemberInfo(memberVo);
            } else if (memberReadVo.getDataType().equals("01")) {//????????????
                BoardVo boardVo = new Gson().fromJson(memberReadVo.getContent(), BoardVo.class);
                if (memberReadVo.getAction().equals("00")) {//??????
                    returnVal = addBoard(boardVo);
                } else if (memberReadVo.getAction().equals("01")) {//??????
                    returnVal = updateBoard(boardVo);
                } else if (memberReadVo.getAction().equals("02")) {//??????
                    returnVal = deleteBoard(boardVo);
                }

            } else if (memberReadVo.getDataType().equals("02")) {//??????
                ScheduleVo scheduleVo = new Gson().fromJson(memberReadVo.getContent(), ScheduleVo.class);

                if (memberReadVo.getAction().equals("00")) {//??????
                    returnVal = addSchedule(scheduleVo);
                } else if (memberReadVo.getAction().equals("01")) {//??????
                    returnVal = updateSchedule(scheduleVo);
                } else if (memberReadVo.getAction().equals("02")) {//??????
                    returnVal = deleteSchedule(scheduleVo);
                }

            }

            if (returnVal) {
                String url = getString(R.string.mainUrl);
                url += getString(R.string.getInfoOK);
                url += "?" + ZZangcoUtility.GET_INFO_OK_PARAM + "=" + memberReadVo.getId();
                Log.e("zzangco", "url=" + url);
                httpConnection = new HttpConnection(this, url, HttpConnection.URLTYPE.SEND);
                httpConnection.start();
            }
        }
    }
}

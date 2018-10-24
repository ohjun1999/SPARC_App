package com.loysc.zzangco.kirikiri_snu.connect;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by zzangco on 2017-11-21.
 */

public class HttpConnectionNoHandler extends Thread {
    private HttpConnectionThread mainActivity;
    private String addr;
    private String result;
    private String inputLine;
    private String param;
    private ProgressDialog progress;
    private HttpConnection.URLTYPE urlType;

    public HttpConnectionNoHandler(HttpConnectionThread main, String add, HttpConnection.URLTYPE urlType) {
        this.mainActivity = main;
        this.addr = add;
        this.result = null;
        this.inputLine = null;
        this.urlType = urlType;
    }

    public HttpConnectionNoHandler(HttpConnectionThread main,String add,ProgressDialog mProgress) {
        this.mainActivity = main;
        this.addr = add;
        this.result = null;
        this.inputLine = null;
        this.progress = mProgress;
    }

    public HttpConnectionNoHandler(HttpConnectionThread main,String add,String param) {
        this.mainActivity = main;
        this.addr = add;
        this.result = null;
        this.inputLine = null;
        this.param = param;
    }

    public HttpConnectionNoHandler(HttpConnectionThread main,String add,String param,ProgressDialog mProgress) {
        this.mainActivity = main;
        this.addr = add;
        this.result = null;
        this.inputLine = null;
        this.param = param;
        this.progress = mProgress;
    }

    public boolean getNetworkState(){
        Activity tmp =  (Activity) mainActivity;
        ConnectivityManager cm = (ConnectivityManager) tmp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(!wifi.isConnected() && !mobile.isConnected())
        {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        StringBuilder html = new StringBuilder();
        JSONObject jo = new JSONObject();
        OutputStreamWriter wr = null;
        boolean severOK = false;

        if(getNetworkState()){
            try {
                URL url = new URL(addr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                jo.put("Connection", "N");
                result = jo.toString();

                if(conn != null){
                    conn.setConnectTimeout(20000);
                    conn.setUseCaches(false);
                    conn.setReadTimeout(20000);

                    if(null == param || "".equals(param)){
                        conn.setRequestMethod("GET");
                    }else{
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        wr = new OutputStreamWriter(conn.getOutputStream());

                        wr.write(param);
                        wr.flush();
                    }

                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
                        StringBuilder sb = new StringBuilder();
                        while( (inputLine = br.readLine()) != null ){
                            sb.append(inputLine);
                        }
                        Log.e("zzangco","ㅇㅇㅇㅇ원 받은 값["+sb.toString()+"]");
                        result = sb.toString();


                        br.close();
                        severOK = true;
                    }
                    conn.disconnect();

                    if(null != param && !("".equals(param))){
                        wr.close();
                    }
                }
            } catch (MalformedURLException e) {
                Log.e("KHT", e.getMessage());
                System.out.println("MalformedURLException occured");
            } catch (JSONException e) {
                Log.e("KHT", e.getMessage());
                System.out.println(e.getMessage());
            } catch (ProtocolException e) {
                Log.e("KHT", e.getMessage());
                System.out.println("ProtocolException occured");
            } catch (UnsupportedEncodingException e) {
                Log.e("KHT", e.getMessage());
                System.out.println("UnsupportedEncodingException occured");
            } catch (IOException e) {
                Log.e("KHT", e.getMessage());
                System.out.println("IOException occured");
            } finally{
                Message msg = Message.obtain();
                Log.e("zzangco","원 받은 값["+result+"]");
                try {

                    //JSONObject returnJo = XML.toJSONObject(result);
                   /* JSONObject returnJo = new JSONObject(result);
                    returnJo.put("netCS", true);
                    returnJo.put("serverCS", severOK);
                    returnJo.put("UrlType", urlType);*/

                    msg.obj = result;
                    //msg.obj = returnJo.toString();



                    mainActivity.afterThread((String)msg.obj);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.e("KHT", e.getMessage());
                    System.out.println("JSONException occured");
                }


            }
        }else{
            Activity tmp =  (Activity) mainActivity;
            JSONObject joe = new JSONObject();
            try {
                joe.put("netCS", false);
                joe.put("serverCS", severOK);
                joe.put("UrlType", urlType);
                Message msg = Message.obtain();
                msg.obj = joe.toString();
                mainActivity.afterThread((String)msg.obj);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                System.out.println("JSONException occured");
            }
        }
    }
}

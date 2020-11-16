package com.zzangco.audioapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class AudioAppActivity extends AppCompatActivity {

    private static MediaPlayer mediaPlayer;

    private Button playButton;
    private Button stopButton;
    private Button goTest;

    private static String audioFilePath;

    private TimerTask mTask;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_app);

        playButton = (Button)findViewById(R.id.playButton);
        stopButton = (Button)findViewById(R.id.stopButton);
        goTest  = (Button)findViewById(R.id.goTest);

        //mediaPlayer = MediaPlayer.create(this,R.raw.han_main);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer.start();

            }
        });

        goTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Test2Activity.class);
                startActivity(intent);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(AudioAppActivity.this,R.raw.han_main);
            }
        });
    }

    @Override
    protected void onPause() {
        mediaPlayer.stop();
        mTimer.cancel();
        mTimer = null;
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(this,R.raw.han_main);
       
        if(mTimer != null){
            mTimer = null;
        }

        mTask = new TimerTask() {
            @Override
            public void run() {
                Log.e("zzangco","재생완료");
                mediaPlayer.start();
            }
        };

        mTimer = new Timer();
        mTimer.schedule(mTask,1000,10000);
    }


}

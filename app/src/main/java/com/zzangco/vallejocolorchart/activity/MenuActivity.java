package com.zzangco.vallejocolorchart.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.zzangco.vallejocolorchart.R;
import com.zzangco.vallejocolorchart.common.ColorInfo;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MenuActivity extends AppCompatActivity {
    private ImageButton imgBtVallejo;
    private ImageButton imgBtTamiya;
    private ImageButton imgBtTestors;
    private ImageButton imgBtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        imgBtTamiya     = (ImageButton)findViewById(R.id.imgBtTamiya);
        imgBtTestors    = (ImageButton)findViewById(R.id.imgBtTestors);
        imgBtVallejo    = (ImageButton)findViewById(R.id.imgBtVallejo);

        imgBtTest       = (ImageButton)findViewById(R.id.imgBtTest);

        imgBtVallejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzangco","Vallejo");
                Intent intent = new Intent(getApplicationContext(),VallejoActivity.class);
                intent.putExtra(ColorInfo.COL_COMPANY, ColorInfo.VALLEJO);
                startActivity(intent);
            }
        });

        imgBtTestors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzangco","Testors");
                Intent intent = new Intent(getApplicationContext(),VallejoActivity.class);
                intent.putExtra(ColorInfo.COL_COMPANY, ColorInfo.MODELMASTER);
                startActivity(intent);
            }
        });

        imgBtTamiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzangco","Tamiya");
                Intent intent = new Intent(getApplicationContext(),VallejoActivity.class);
                intent.putExtra(ColorInfo.COL_COMPANY, ColorInfo.TAMIYA);
                startActivity(intent);
            }
        });

        imgBtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("zzangco","Test");
                Intent intent = new Intent(getApplicationContext(),TotalColorListActivity.class);
                intent.putExtra(ColorInfo.COL_COMPANY, ColorInfo.TAMIYA);
                startActivity(intent);
            }
        });
    }


}

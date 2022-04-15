package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loysc.zzangco.kirikiri_snu.R;

public class ActivityPasswordPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_page);
    }

    public void inPutPassword(View view) {

        Intent intent = new Intent(this,SplashActivity.class);
        startActivity(intent);
    }
}

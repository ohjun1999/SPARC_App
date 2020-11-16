package com.loysc.zzangco.kirikiri_snu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.loysc.zzangco.kirikiri_snu.R;

public class InAppInfoActivity extends AppCompatActivity {
    private Button btConfirm,btClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_info);

        btConfirm = (Button)findViewById(R.id.btConfirm);
        btClose = (Button)findViewById(R.id.btClose);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(SplashActivity.IS_OK,true);
                setResult(RESULT_OK,data);

                finish();
            }
        });

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("isOK",false);
                setResult(RESULT_OK,data);

                finish();
            }
        });
    }
}

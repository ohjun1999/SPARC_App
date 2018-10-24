package com.loysc.zzangco.kirikiri_snu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

public class PopupActivity extends Activity {

    private TextView tvTitile;
    private TextView tvContent;
    private Button btOk;

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        tvTitile = (TextView)findViewById(R.id.tvTitile);
        tvContent = (TextView)findViewById(R.id.tvContent);

        tvTitile.setText(getIntent().getStringExtra(TITLE));
        tvContent.setText(getIntent().getStringExtra(CONTENT));

        btOk = (Button)findViewById(R.id.btOk);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

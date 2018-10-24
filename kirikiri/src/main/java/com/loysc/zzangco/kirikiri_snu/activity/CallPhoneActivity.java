package com.loysc.zzangco.kirikiri_snu.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class CallPhoneActivity extends Activity {

    public static final String MEMBERITEMS = "item";
    private MemberViewItem item;

    private TextView tvHandPhone,tvComPhone,tvHomePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        Intent intent = getIntent();
        item = (MemberViewItem) intent.getSerializableExtra(MEMBERITEMS);

        tvHandPhone = (TextView)findViewById(R.id.tvHandPhone);
        tvComPhone = (TextView)findViewById(R.id.tvComPhone);
        tvHomePhone = (TextView)findViewById(R.id.tvHomePhone);

        tvHandPhone.setText(item.getHandphone());
        tvComPhone.setText(item.getComphone());
        tvHomePhone.setText(item.getHomephone());

        tvHandPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != item.getHandphone() && !item.getHandphone().isEmpty()) {
                    callPhone(item.getHandphone());
                }
            }
        });
        tvComPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != item.getComphone() && !item.getComphone().isEmpty()) {
                    callPhone(item.getComphone());
                }
            }
        });
        tvHomePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != item.getHomephone() && !item.getHomephone().isEmpty()) {
                    callPhone(item.getHomephone());
                }
            }
        });
    }

    private void callPhone(String phoneNumber){
        //phoneNumber = "010-3488-0388";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
        startActivity(intent);
        finish();
    }
}

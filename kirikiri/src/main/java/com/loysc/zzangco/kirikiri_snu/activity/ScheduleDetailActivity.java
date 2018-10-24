package com.loysc.zzangco.kirikiri_snu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

import com.loysc.zzangco.kirikiri_snu.common.ScheduleItem;

public class ScheduleDetailActivity extends Activity {
    private TextView tvTitle,tvAnnDate,tvContent,tvType;
    private ScheduleItem item;

    public static final String SCHEDULE_ITEM = "item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        Intent intent = getIntent();
        item = (ScheduleItem)intent.getSerializableExtra(SCHEDULE_ITEM);

        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvAnnDate = (TextView)findViewById(R.id.tvAnnDate);
        tvContent = (TextView)findViewById(R.id.tvContent);
        tvType = (TextView)findViewById(R.id.tvType);

        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getContent());

        tvAnnDate.setText(makeYYYYMMDD(item.getAnnDate()));

        if(item.getType().equals("00")){
            tvType.setText("모임");
        }else if(item.getType().equals("01")){
            tvType.setText("결혼");
        }else if(item.getType().equals("02")){
            tvType.setText("부고");
        }else if(item.getType().equals("03")){
            tvType.setText("기타");
        }

    }

   private String makeYYYYMMDD(String yyyymmdd){
        return yyyymmdd.substring(0, 4) + "년 " + yyyymmdd.substring(4, 6) + "월 " + yyyymmdd.substring(6, 8) + "일";
    }
}

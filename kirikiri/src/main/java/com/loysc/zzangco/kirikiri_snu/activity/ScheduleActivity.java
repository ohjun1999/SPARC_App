package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.common.ScheduleInfo;
import com.loysc.zzangco.kirikiri_snu.common.ScheduleItem;

public class ScheduleActivity extends AppCompatActivity {
    //private MaterialCalendarView calendarView;
    private CalendarView calendarView;
    //private android.widget.CalendarView calendarView2;

    private ArrayList<ScheduleItem> scheduleList = new ArrayList<ScheduleItem>();
    private List<EventDay> events = new ArrayList<>();
    private ScheduleInfo scheduleInfo;
    HashMap<String,String> scheduleMap = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //calendarView2 = (android.widget.CalendarView)findViewById(R.id.calendarView2);

        calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                try{
                    String id = scheduleMap.get(eventDay.getCalendar().getTime().toString());
                    Intent intent = new Intent(getApplicationContext(),ScheduleDetailActivity.class);
                    ScheduleItem item = getItem(id);

                    item.setAnnDate(makeYYYYMMDD(eventDay.getCalendar()));
                    Log.e("zzangco","sdfsfs = " + item.getAnnDate());
                    intent.putExtra(ScheduleDetailActivity.SCHEDULE_ITEM, item);

                    startActivity(intent);
                }catch (Exception e){

                }

            }
        });
      /* try{
           ViewGroup vg = (ViewGroup)calendarView2.getChildAt(0);
           View child = vg.getChildAt(0);

           if(child instanceof TextView){
               ((TextView)child).setTextColor(Color.RED);
               Log.e("zzangco","Sche ["+((TextView)child).getText() + "]");
           }
       }catch (Exception e){
           Log.e("zzangco","Sche Error");
       }*/

        getSupportActionBar().setTitle("일정");

        getScheduleList();
        settingSchedule();
        MainActivity.instance.asyncDialog.dismiss();
    }

    private String makeYYYYMMDD(Calendar tmpCal){
        String yyyymmdd = "";
        yyyymmdd = String.valueOf(tmpCal.get(Calendar.YEAR));

        if(tmpCal.get(Calendar.MONTH) + 1 < 10){
            yyyymmdd += "0" + Integer.valueOf(tmpCal.get(Calendar.MONTH) + 1);
        }else{
            yyyymmdd += Integer.valueOf(tmpCal.get(Calendar.MONTH) + 1);
        }

        if(tmpCal.get(Calendar.DAY_OF_MONTH) < 10){
            yyyymmdd += "0" + Integer.valueOf(tmpCal.get(Calendar.DAY_OF_MONTH));
        }else{
            yyyymmdd += Integer.valueOf(tmpCal.get(Calendar.DAY_OF_MONTH));
        }


        return yyyymmdd;
    }

    private void settingSchedule(){
        Calendar settingDay = null;
        List<Integer> yyyymmdd = null;
        for(ScheduleItem item : scheduleList){
            settingDay = Calendar.getInstance();
            yyyymmdd = getYMD(item.getAnnDate());

            settingDay.set(yyyymmdd.get(0),yyyymmdd.get(1),yyyymmdd.get(2));
            Log.e("zzangco","Sche item :" + item.getCycle());

            setEvent(settingDay,item);

            if(null != item.getCycle()){
                Calendar settingDay2 = null;
                if(item.getCycle().equals("01")){
                    for(int i = 0; i < 50; i++){
                        settingDay2 = Calendar.getInstance();
                        settingDay2.set(settingDay.get(Calendar.YEAR),settingDay.get(Calendar.MONTH),settingDay.get(Calendar.DAY_OF_MONTH));

                        setEvent(settingDay2,item);
                        settingDay.add(Calendar.DATE,7);
                    }

                }else if(item.getCycle().equals("02")){

                    for(int i = 0; i < 36; i++){
                        settingDay2 = Calendar.getInstance();
                        settingDay2.set(settingDay.get(Calendar.YEAR),settingDay.get(Calendar.MONTH),settingDay.get(Calendar.DAY_OF_MONTH));

                        setEvent(settingDay2,item);
                        settingDay.add(Calendar.MONTH,1);
                    }
                }
            }
        }

        calendarView.setEvents(events);
    }

    private void setEvent(Calendar settingDay,ScheduleItem item){
        int imgResource = 0;
        EventDay eventDay = null;

        if(item.getType().equals("00")){
            imgResource = R.drawable.important;
        }else if(item.getType().equals("01")){
            imgResource = R.drawable.sample_icon_2;
        }else if(item.getType().equals("02")){
            imgResource = R.drawable.rip;
        }else if(item.getType().equals("03")){
            imgResource = R.drawable.sample_icon_3;
        }
        eventDay = new EventDay(settingDay,imgResource);

        events.add(eventDay);

        scheduleMap.put(eventDay.getCalendar().getTime().toString(),item.getId());
    }

    private void getScheduleList(){
        scheduleList.clear();
        getDatastore();

        scheduleInfo.openc();
        List<ScheduleItem> list = scheduleInfo.getScheduleList();

        for(ScheduleItem item : list){
            scheduleList.add(item);
        }
    }

    private ScheduleItem getItem(String id){
        getDatastore();
        scheduleInfo.openc();
        return scheduleInfo.getScheduleItem(id);
    }

    private ScheduleInfo getDatastore(){
        if(null == scheduleInfo){
            scheduleInfo = new ScheduleInfo(this);
        }

        return scheduleInfo;
    }

    public List<Integer> getYMD(String ymd){
        List<Integer> yyyymmdd = new ArrayList<Integer>();

        yyyymmdd.add(Integer.valueOf(ymd.substring(0, 4)));
        yyyymmdd.add(Integer.valueOf(ymd.substring(4, 6))-1);
        yyyymmdd.add(Integer.valueOf(ymd.substring(6, 8)));

        return yyyymmdd;
    }
}

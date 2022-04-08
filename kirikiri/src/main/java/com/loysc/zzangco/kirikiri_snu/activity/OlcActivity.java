package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;

import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class OlcActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MemberViewItem> items = new ArrayList<MemberViewItem>();

    //private MemberAdapter adapter;
    private String granNumber = "00";
    private LinearLayout llOlcIntro;
    private LinearLayout llOlcChart;
    private LinearLayout llOlcHistory;
    private LinearLayout llOlcLaw;
    private LinearLayout llOlcStaff;


    private TextView olcOfficePhone,olcOfficeEmail;

    private ViewFlipper vfSlider;
    private ImageView imgBanner1,imgBanner2,imgBanner3,imgBanner4,imgBanner5;

    private Animation slide_out_left,slide_in_right;
    private Animation slide_in_left,slide_out_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olc_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //adapter = new MemberAdapter(this,items);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        llOlcIntro = (LinearLayout)findViewById(R.id.llOlcIntro);
        llOlcChart = (LinearLayout)findViewById(R.id.llOlcChart);
        llOlcHistory = (LinearLayout)findViewById(R.id.llOlcHistory);
        llOlcLaw = (LinearLayout)findViewById(R.id.llOlcLaw);
        llOlcStaff = (LinearLayout)findViewById(R.id.llOlcStaff);


        olcOfficePhone = (TextView)findViewById(R.id.olcOfficePhone);

        olcOfficePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });

        olcOfficeEmail = (TextView)findViewById(R.id.olcOfficeEmail);

        olcOfficeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        getSupportActionBar().setTitle("동창회 임원단");
        MainActivity.instance.asyncDialog.dismiss();

        vfSlider = (ViewFlipper)findViewById(R.id.vfSlider);
        imgBanner1 = (ImageView)findViewById(R.id.imgBanner1);
        imgBanner2 = (ImageView)findViewById(R.id.imgBanner2);
        imgBanner3 = (ImageView)findViewById(R.id.imgBanner3);
        imgBanner4 = (ImageView)findViewById(R.id.imgBanner4);
        imgBanner5 = (ImageView)findViewById(R.id.imgBanner5);


        imgBanner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.sftc_url)));
                startActivity(intent);
            }
        });
        imgBanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.storant_url)));
                startActivity(intent);
            }
        });
        imgBanner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.orient_url)));
                startActivity(intent);
            }
        });
        imgBanner4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.inettv_url)));
                startActivity(intent);
            }
        });
        imgBanner5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gjec_url)));
                startActivity(intent);
            }
        });


        slide_in_left = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left );
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right );

        slide_out_left = AnimationUtils.loadAnimation(this,R.anim.ani_translate_l );
        slide_in_right = AnimationUtils.loadAnimation(this,R.anim.ani_translate_r );

        vfSlider.setInAnimation(slide_in_right);
        vfSlider.setOutAnimation(slide_out_left);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //int firstViewInt = ZZangcoUtility.randomRange(0,2);

        vfSlider.setDisplayedChild(0);
        vfSlider.startFlipping();

        vfSlider.setFlipInterval(2500);
    }

    private void callPhone(){
        //phoneNumber = "02-828-3962";
        String olc_office_phone = getString(R.string.olp_office_phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olc_office_phone));
        startActivity(intent);

    }
    private void callPhone2(){
        //phoneNumber = "02-705-8017";
        String olp_office_phone = getString(R.string.olp_office_phone2);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olp_office_phone));
        startActivity(intent);

    }

    private void sendEmail(){
        //EmailAddress = "olc@sogang.ac.kr";
        String olc_office_email = getString(R.string.olc_office_email);

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        String[] address = {olc_office_email};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        startActivity(email);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.olc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.olc) {

            llOlcIntro.setVisibility(View.VISIBLE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcStaff.setVisibility(View.GONE);

            toolbar.setTitle("동창회 임원단");
        } else if (id == R.id.olcChart) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.VISIBLE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcStaff.setVisibility(View.GONE);

            toolbar.setTitle("동창회 운영위원회");
        /*} else if (id == R.id.olcHistory) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.VISIBLE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcOffice.setVisibility(View.GONE);

            toolbar.setTitle("OLC 연혁");*/
        } else if (id == R.id.olcLaw) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.VISIBLE);
            llOlcStaff.setVisibility(View.GONE);

            toolbar.setTitle("회칙소개");
        } /*else if (id == R.id.olcStaff) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcStaff.setVisibility(View.VISIBLE);

            toolbar.setTitle("임원진 소개");
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

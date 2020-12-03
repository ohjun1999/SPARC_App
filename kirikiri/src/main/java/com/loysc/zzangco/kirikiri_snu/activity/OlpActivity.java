package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;

import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class OlpActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MemberViewItem> items = new ArrayList<MemberViewItem>();

    //private MemberAdapter adapter;
    private String granNumber = "00";

    private LinearLayout llOlpIntro;
    private LinearLayout llOlpCur;
    private LinearLayout llOlpOffice;
    private LinearLayout llOlpRec;
    private WebView webViewOlpMap;

    private TextView olpOfficePhone,olpOfficeEmail,olpHomepage;
    private TextView olpOfficePhoneA,olpOfficePhoneA2,olpOfficeEmailA,olpHomepageA;
    private TextView tvManager1,tvManager2;

    private ViewFlipper vfSlider;
    private ImageView imgBanner1,imgBanner2,imgBanner3,imgBanner4,imgBanner5;

    private Animation slide_out_left,slide_in_right;
    private Animation slide_in_left,slide_out_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olp_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //adapter = new MemberAdapter(this,items);

        layoutManager = new LinearLayoutManager(this);


        setSupportActionBar(toolbar);

        llOlpIntro = (LinearLayout)findViewById(R.id.llOlpIntro);
        llOlpCur = (LinearLayout)findViewById(R.id.llOlpCur);
        llOlpOffice = (LinearLayout)findViewById(R.id.llOlpOffice);
        llOlpRec = (LinearLayout)findViewById(R.id.llOlpRec);

        webViewOlpMap = (WebView)findViewById(R.id.webViewOlpMap);
        webViewOlpMap.loadUrl("file:///android_asset/olpMap.html");
        webViewOlpMap.getSettings().setJavaScriptEnabled(true);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        llOlpIntro.setVisibility(View.VISIBLE);
        llOlpCur.setVisibility(View.GONE);
        llOlpOffice.setVisibility(View.GONE);

        olpOfficePhone = (TextView)findViewById(R.id.olpOfficePhone);
        tvManager1 = (TextView)findViewById(R.id.tvManager1);
        tvManager2 = (TextView)findViewById(R.id.tvManager2);

        tvManager1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String olp_office_phone = getString(R.string.olp_manger1);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olp_office_phone));
                startActivity(intent);
            }
        });

        tvManager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String olp_office_phone = getString(R.string.olp_manger2);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olp_office_phone));
                startActivity(intent);
            }
        });

        olpOfficePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });

        olpOfficePhoneA = (TextView)findViewById(R.id.olpOfficePhoneA);

        olpOfficePhoneA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });

        olpOfficePhoneA2 = (TextView)findViewById(R.id.olpOfficePhoneA2);
        olpOfficePhoneA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone2();
            }
        });

        olpOfficeEmail = (TextView)findViewById(R.id.olpOfficeEmail);

        olpOfficeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        olpHomepage = (TextView)findViewById(R.id.olpHomepage);
        olpHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(Intent.ACTION_VIEW);
                Uri u = Uri.parse(getString(R.string.olp_office_homepage));
                homepage.setData(u);
                startActivity(homepage);
            }
        });
        olpHomepageA = (TextView)findViewById(R.id.olpHomepageA);
        olpHomepageA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(Intent.ACTION_VIEW);
                Uri u = Uri.parse(getString(R.string.olp_office_homepage));
                homepage.setData(u);
                startActivity(homepage);
            }
        });

        getSupportActionBar().setTitle("SPARC란?");
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

    private void callPhone(){
        //phoneNumber = "02-705-8017";
        String olp_office_phone = getString(R.string.olp_office_phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olp_office_phone));
        startActivity(intent);

    }

    private void callPhone2(){
        //phoneNumber = "02-705-8017";
        String olp_office_phone = getString(R.string.olp_office_phone2);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olp_office_phone));
        startActivity(intent);

    }

    private void sendEmail(){
        //EmailAddress = "solp@sogang.ac.kr";
        String olp_office_email = getString(R.string.olp_office_email);

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        String[] address = {olp_office_email};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        startActivity(email);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //int firstViewInt = ZZangcoUtility.randomRange(0,2);

        vfSlider.setDisplayedChild(0);
        vfSlider.startFlipping();

        vfSlider.setFlipInterval(2500);
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
        getMenuInflater().inflate(R.menu.olp, menu);
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

        if (id == R.id.olp) {

            llOlpIntro.setVisibility(View.VISIBLE);
            llOlpCur.setVisibility(View.GONE);
            llOlpOffice.setVisibility(View.GONE);
            llOlpRec.setVisibility(View.GONE);
            toolbar.setTitle("SPARC란?");
        } else if (id == R.id.olpCur) {

            llOlpIntro.setVisibility(View.GONE);
            llOlpCur.setVisibility(View.VISIBLE);
            llOlpOffice.setVisibility(View.GONE);
            llOlpRec.setVisibility(View.GONE);

            toolbar.setTitle("과정운영개요");
        } else if (id == R.id.olpRec) {

            llOlpIntro.setVisibility(View.GONE);
            llOlpCur.setVisibility(View.GONE);
            llOlpOffice.setVisibility(View.GONE);
            llOlpRec.setVisibility(View.VISIBLE);
            toolbar.setTitle("입학안내");
        } else if (id == R.id.olpOffice) {

            llOlpIntro.setVisibility(View.GONE);
            llOlpCur.setVisibility(View.GONE);
            llOlpOffice.setVisibility(View.VISIBLE);
            llOlpRec.setVisibility(View.GONE);
            toolbar.setTitle("행정실 소개");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

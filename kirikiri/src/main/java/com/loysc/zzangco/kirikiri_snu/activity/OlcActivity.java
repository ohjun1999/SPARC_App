package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;

import com.loysc.zzangco.kirikiri_snu.common.MemberAdapter;
import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class OlcActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MemberViewItem> items = new ArrayList<MemberViewItem>();

    private MemberAdapter adapter;
    private String granNumber = "00";
    private LinearLayout llOlcIntro;
    private LinearLayout llOlcChart;
    private LinearLayout llOlcHistory;
    private LinearLayout llOlcLaw;
    private LinearLayout llOlcOffice;
    private WebView webViewMap;

    private TextView olcOfficePhone,olcOfficeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olc_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        adapter = new MemberAdapter(this,items);
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
        llOlcOffice = (LinearLayout)findViewById(R.id.llOlcOffice);
        webViewMap = (WebView)findViewById(R.id.webViewMap);
        webViewMap.loadUrl("file:///android_asset/olcMap.html");
        webViewMap.getSettings().setJavaScriptEnabled(true);

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

        getSupportActionBar().setTitle("OLC란?");
        MainActivity.instance.asyncDialog.dismiss();
    }

    private void callPhone(){
        //phoneNumber = "02-828-3962";
        String olc_office_phone = getString(R.string.olc_office_phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+olc_office_phone));
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
            llOlcOffice.setVisibility(View.GONE);

            toolbar.setTitle("OLC란?");
        } else if (id == R.id.olcChart) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.VISIBLE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcOffice.setVisibility(View.GONE);

            toolbar.setTitle("조직도");
        } else if (id == R.id.olcHistory) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.VISIBLE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcOffice.setVisibility(View.GONE);

            toolbar.setTitle("OLC 연혁");
        } else if (id == R.id.olcLaw) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.VISIBLE);
            llOlcOffice.setVisibility(View.GONE);

            toolbar.setTitle("회칙");
        } else if (id == R.id.olcOffice) {

            llOlcIntro.setVisibility(View.GONE);
            llOlcChart.setVisibility(View.GONE);
            llOlcHistory.setVisibility(View.GONE);
            llOlcLaw.setVisibility(View.GONE);
            llOlcOffice.setVisibility(View.VISIBLE);

            toolbar.setTitle("회칙");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

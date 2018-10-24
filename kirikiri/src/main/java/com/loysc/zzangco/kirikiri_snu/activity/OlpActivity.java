package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

public class OlpActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MemberViewItem> items = new ArrayList<MemberViewItem>();

    private MemberAdapter adapter;
    private String granNumber = "00";

    private LinearLayout llOlpIntro;
    private LinearLayout llOlpCur;
    private LinearLayout llOlpOffice;
    private WebView webViewOlpMap;

    private TextView olpOfficePhone,olpOfficeEmail,olpHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olp_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        adapter = new MemberAdapter(this,items);

        layoutManager = new LinearLayoutManager(this);


        setSupportActionBar(toolbar);

        llOlpIntro = (LinearLayout)findViewById(R.id.llOlpIntro);
        llOlpCur = (LinearLayout)findViewById(R.id.llOlpCur);
        llOlpOffice = (LinearLayout)findViewById(R.id.llOlpOffice);

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

        olpOfficePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
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

        getSupportActionBar().setTitle("OLP란?");
        MainActivity.instance.asyncDialog.dismiss();
    }

    private void callPhone(){
        //phoneNumber = "02-705-8017";
        String olp_office_phone = getString(R.string.olp_office_phone);
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

            toolbar.setTitle("OLP란?");
        } else if (id == R.id.olpCur) {

            llOlpIntro.setVisibility(View.GONE);
            llOlpCur.setVisibility(View.VISIBLE);
            llOlpOffice.setVisibility(View.GONE);

            toolbar.setTitle("커리큘럼");
        } else if (id == R.id.olpOffice) {

            llOlpIntro.setVisibility(View.GONE);
            llOlpCur.setVisibility(View.GONE);
            llOlpOffice.setVisibility(View.VISIBLE);

            toolbar.setTitle("OLP 사무국 안내");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.loysc.zzangco.kirikiri_snu.activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.loysc.zzangco.kirikiri_snu.R;

public class OlcManualActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private TextView tvManualContect;
    private ImageView imgManual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olc_manual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("메뉴얼");

        tvManualContect = (TextView)findViewById(R.id.tvManualContect);
        imgManual = (ImageView)findViewById(R.id.imgManual);

        tvManualContect.setText(Html.fromHtml(getString(R.string.manual_main)));
        imgManual.setImageResource(R.drawable.manual_main);
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
        getMenuInflater().inflate(R.menu.olc_manual, menu);
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

        if (id == R.id.main) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_main)));
            imgManual.setImageResource(R.drawable.manual_main);
        } else if (id == R.id.member) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_olcda)));
            imgManual.setImageResource(R.drawable.dalist);
        } else if (id == R.id.board) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_borad)));
            imgManual.setImageResource(R.drawable.manual_board_list);
        } else if (id == R.id.schadual) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_schadual)));
            imgManual.setImageResource(R.drawable.manual_schedual);
        } else if (id == R.id.olc) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_olc)));
            imgManual.setImageResource(R.drawable.manual_olc);
        } else if (id == R.id.olp) {
            tvManualContect.setText(Html.fromHtml(getString(R.string.manual_olp)));
            imgManual.setImageResource(R.drawable.manual_olp);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

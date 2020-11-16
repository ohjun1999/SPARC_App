package com.zzangco.colorlibrary.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zzangco.colorlibrary.R;
import com.zzangco.colorlibrary.common.ColorAdapter;
import com.zzangco.colorlibrary.common.ColorInfo;
import com.zzangco.colorlibrary.common.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class ColorListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private ImageView imvTitleIcon;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ColorAdapter adapter;

    private ColorInfo colorInfo;

    private ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();

    String company = "";
    String type = "";
    String searchWord = "";
    boolean iHave = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        //addListItem(ColorInfo.VALLEJO,ColorInfo.MODELCOLOR,null);
        setSupportActionBar(toolbar);
        recyclerView.setHasFixedSize(true);
        adapter = new ColorAdapter(this,items);

        //layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        imvTitleIcon = (ImageView)nav_header_view.findViewById(R.id.imvTitleIcon);

        addListItem(company,"All",null,iHave);

        getSupportActionBar().setTitle("Vallejo & Tamiya & Testors");
    }

    private void addListItem(String company,String type,String searchWord,boolean iHave){
        items.clear();
        getDatastore();
        colorInfo.openc();

        Log.e("zzangco","검색 :" + company);

        List<ListViewItem> list = colorInfo.getColorList(company, type, searchWord,iHave);

        for(ListViewItem item: list) {
            items.add(item);
        }

        adapter.notifyDataSetChanged();

        recyclerView.scrollToPosition(0);
    }

    public ColorInfo getDatastore() {
        if(colorInfo == null){
            colorInfo = new ColorInfo(getApplicationContext());
        }
        return colorInfo;
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
        getMenuInflater().inflate(R.menu.color_list, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_color));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.e("zzangco","검색 :" + s);
                searchWord = s;
                addListItem(company,type,s,iHave);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.e("zzangco","변경 :" + s);
                searchWord = s;
                addListItem(company,type,s,iHave);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.all_color) {
            iHave = false;
            addListItem(company,type,searchWord,iHave);
            return true;
        }else if (id == R.id.storage_color) {
            iHave = true;
            addListItem(company,type,searchWord,iHave);
            return true;
        }else if(id == R.id.writer_about){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("About");
            builder.setMessage(Html.fromHtml("<p>MyColor 0.9.0</p><br><p>제작자 : zzangco</p><br><p>연락처 : <a href=\"mailto:zzangco@gmail.com\">zzangco@gmail.com</a></p>"));

            builder.setNeutralButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.vallejo_all) {
            // Handle the camera action
            toolbar.setTitle("Vallejo All");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
           // imvTitleIcon.setImageDrawable(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = "All";
        } else if (id == R.id.model_color){
            toolbar.setTitle("Model Color");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.MODELCOLOR;
        } else if (id == R.id.model_air){
            toolbar.setTitle("Model Air");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.MODELAIR;
        } else if (id == R.id.game_color){
            toolbar.setTitle("Game Color");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.GAMECOLOR;
        } else if (id == R.id.game_air){
            toolbar.setTitle("Game Air");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.GAMEAIR;
        } else if (id == R.id.model_wash){
            toolbar.setTitle("Model Wash");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.WASH;
        } else if (id == R.id.panzer_color){
            toolbar.setTitle("Panzer Color");
            imvTitleIcon.setImageResource(R.drawable.vallejo);
            company = ColorInfo.VALLEJO;
            type = ColorInfo.PANZER;
        } else if (id == R.id.tamiya_all) {
            toolbar.setTitle("Tamiya All");
            imvTitleIcon.setImageResource(R.drawable.tamiya);
            company = ColorInfo.TAMIYA;
            type = "All";
        } else if (id == R.id.tamiya_x) {
            toolbar.setTitle("Tamiya X");
            imvTitleIcon.setImageResource(R.drawable.tamiya);
            company = ColorInfo.TAMIYA;
            type = ColorInfo.X;
        } else if (id == R.id.tamiya_xf) {
            toolbar.setTitle("Tamiya XF");
            imvTitleIcon.setImageResource(R.drawable.tamiya);
            company = ColorInfo.TAMIYA;
            type = ColorInfo.XF;
        } else if (id == R.id.testors_all) {
            toolbar.setTitle("Testors All");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = "All";
        } else if (id == R.id.basic_color) {
            toolbar.setTitle("Testors Basic Color");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = ColorInfo.BASICCOLOR;
        } else if (id == R.id.fscolor) {
            toolbar.setTitle("Testors FS Color");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = ColorInfo.FSCOLOR;
        } else if (id == R.id.afv) {
            toolbar.setTitle("Testors AFV");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = ColorInfo.AFV;
        } else if (id == R.id.marine_color) {
            toolbar.setTitle("Testors Marine Color");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = ColorInfo.MARINECOLOR;
        } else if (id == R.id.wwii) {
            toolbar.setTitle("Testors WWII Color");
            imvTitleIcon.setImageResource(R.drawable.testors);
            company = ColorInfo.MODELMASTER;
            type = ColorInfo.WWII;
        } else if (id == R.id.all_about) {
            toolbar.setTitle("Vallejo & Tamiya & Testors");
            imvTitleIcon.setImageResource(R.drawable.model_ac_color_title);
            company = "";
            type = "All";
        }
        //toolbar.setTitle(txtTitle.getText());
        addListItem(company,type,null,iHave);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        int positon = data.getIntExtra(DetialInfoActivity.POSITION,0);
        String stock = data.getStringExtra(DetialInfoActivity.STOCK);

        ListViewItem item = items.get(positon);
        item.setEtStock(stock);

        if(Integer.parseInt(stock) == 0){

            if(item.getFlipType() > 0){
                item.setFlipType(0);
                items.set(positon,item);

                if(iHave){
                    Log.e("zzangco","아이템 삭제");
                    adapter.remove(positon);
                }else{
                    Log.e("zzangco","아이템 변경");
                    adapter.notifyItemChanged(positon);
                }
            }
        }else{
            adapter.notifyItemChanged(positon);
        }
    }
}

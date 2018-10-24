package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.common.MemberAdapter;
import com.loysc.zzangco.kirikiri_snu.common.MemberInfo;
import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class MemberListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private RecyclerView rcMemberList;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MemberViewItem> items = new ArrayList<MemberViewItem>();

    private MemberInfo memberInfo;
    private MemberAdapter adapter;
    private String searchWord = "";
    private String favorite = "F";
    private String granNumber = "00";
    private String business = null;

    private SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rcMemberList = (RecyclerView)findViewById(R.id.rcMemberList);
        rcMemberList.setHasFixedSize(true);
        adapter = new MemberAdapter(this,items);

        layoutManager = new LinearLayoutManager(this);
        rcMemberList.setLayoutManager(layoutManager);
        rcMemberList.setAdapter(adapter);


        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav_header_view = navigationView.getHeaderView(0);

        addListItem("","00","F",business);
        getSupportActionBar().setTitle("전체");

        MainActivity.instance.asyncDialog.dismiss();
    }



    private void addListItem(String searchWord,String granNumber,String fav,String business){
        items.clear();
        getDatastore();

        memberInfo.openc();
        List<MemberViewItem> list = memberInfo.getMemberList(searchWord,granNumber,fav,business);

        for(MemberViewItem item : list){
            items.add(item);
        }
        adapter.notifyDataSetChanged();
        rcMemberList.scrollToPosition(0);

    }

    private MemberInfo getDatastore(){
        if(null == memberInfo){
            memberInfo = new MemberInfo(getApplication());
        }

        return memberInfo;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int positon = data.getIntExtra(DetailInfoActivity.POSITION,0);

        if(favorite.equals("T")){
            adapter.remove(positon);
        }else{
            memberInfo = getDatastore();
            MemberViewItem item = memberInfo.getMemberInfo(items.get(positon).getId());

            items.set(positon,item);

            adapter.notifyItemChanged(positon);

        }

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
        getMenuInflater().inflate(R.menu.member_list, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        searchView = (SearchView)searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_member));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchWord = query;
                addListItem(searchWord,granNumber,favorite,business);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchWord = newText;
                addListItem(searchWord,granNumber,favorite,business);
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
        if (id == R.id.action_settings) {
            favorite = "T";
            toolbar.setTitle("즐겨찾기");
            addListItem(searchWord,granNumber,favorite,business);
            return true;
        }else if(id == R.id.action_all_vew) {
            favorite = "F";
            toolbar.setTitle("전체");
            addListItem(searchWord,granNumber,favorite,business);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        business = null;
        if (id == R.id.first_member) {
            addListItem(searchWord,"01",favorite,business);
            toolbar.setTitle("1기");
        } else if (id == R.id.second_member) {
            addListItem(searchWord,"02",favorite,business);
            toolbar.setTitle("2기");
        } else if (id == R.id.third_member) {
            addListItem(searchWord,"03",favorite,business);
            toolbar.setTitle("3기");
        } else if (id == R.id.fourth_member) {
            addListItem(searchWord,"04",favorite,business);
            toolbar.setTitle("4기");
        } else if (id == R.id.fifth_member) {
            addListItem(searchWord,"05",favorite,business);
            toolbar.setTitle("5기");
        } else if (id == R.id.sixth_member) {
            addListItem(searchWord,"06",favorite,business);
            toolbar.setTitle("6기");
        } else if (id == R.id.seventh_member) {
            addListItem(searchWord,"07",favorite,business);
            toolbar.setTitle("7기");
        } else if (id == R.id.eighth_member) {
            addListItem(searchWord,"08",favorite,business);
            toolbar.setTitle("8기");
        } else if (id == R.id.ninth_member) {
            addListItem(searchWord,"09",favorite,business);
            toolbar.setTitle("9기");
        } else if (id == R.id.tenth_member) {
            addListItem(searchWord, "10", favorite,business);
            toolbar.setTitle("10기");
        } else if (id == R.id.eleventh_member) {
            addListItem(searchWord,"11",favorite,business);
            toolbar.setTitle("11기");
        } else if (id == R.id.twelfth_member) {
            addListItem(searchWord,"12",favorite,business);
            toolbar.setTitle("12기");
        } else if (id == R.id.thirteenth_member) {
            addListItem(searchWord,"13",favorite,business);
            toolbar.setTitle("13기");
        } else if (id == R.id.fourteenth_member) {
            addListItem(searchWord,"14",favorite,business);
            toolbar.setTitle("14기");
        } else if (id == R.id.fifteenth_member) {
            addListItem(searchWord,"15",favorite,business);
            toolbar.setTitle("15기");
        } else if (id == R.id.sixteenth_member) {
            addListItem(searchWord,"16",favorite,business);
            toolbar.setTitle("16기");
        } else if (id == R.id.seventeenth_member) {
            addListItem(searchWord,"17",favorite,business);
            toolbar.setTitle("17기");
        } else if (id == R.id.eighteenth_member) {
            addListItem(searchWord,"18",favorite,business);
            toolbar.setTitle("18기");
        } else if (id == R.id.nineteenth_member) {
            addListItem(searchWord,"19",favorite,business);
            toolbar.setTitle("19기");
        } else if (id == R.id.twentieth_member) {
            addListItem(searchWord,"20",favorite,business);
            toolbar.setTitle("20기");
        } else if (id == R.id.twentiethFirst_member) {
            addListItem(searchWord,"21",favorite,business);
            toolbar.setTitle("21기");
        } else if (id == R.id.professor_member) {
            addListItem(searchWord,"99",favorite,business);
            toolbar.setTitle("교수진");
        }else if(id == R.id.all_member) {
            business = null;
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("전체");
        }else if(id == R.id.enterprise){
            business = "1";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("기업");
        }else if(id == R.id.brodcast){
            business = "2";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("방송");
        }else if(id == R.id.promote){
            business = "3";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("홍보");
        }else if(id == R.id.law){
            business = "4";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("법조");
        }else if(id == R.id.goverment){
            business = "5";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("공기업 관공서");
        }else if(id == R.id.financial){
            business = "6";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("금융");
        }else if(id == R.id.school){
            business = "7";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("교육");
        }else if(id == R.id.account){
            business = "8";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("행정 회계");
        }else if(id == R.id.hospital){
            business = "9";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("의료");
        }else if(id == R.id.ngo){
            business = "10";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("단체");
        }else if(id == R.id.etc){
            business = "11";
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle("기타");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

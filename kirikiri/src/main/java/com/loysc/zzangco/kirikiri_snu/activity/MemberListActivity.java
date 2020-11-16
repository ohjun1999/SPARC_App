package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
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

    private TextView tvgi,tvni,tvdi,tvri,tvma,tvba,tvsa,tva,tja,tcha,tka,tta,tpa,tha;

    private ViewFlipper vfSlider;
    private ImageView imgBanner1,imgBanner2,imgBanner3,imgBanner5,imgBanner7,imgBanner8,imgBanner9;

    private Animation slide_out_left,slide_in_right;
    private Animation slide_in_left,slide_out_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rcMemberList = (RecyclerView)findViewById(R.id.rcMemberList);

        vfSlider = (ViewFlipper)findViewById(R.id.vfSlider);
        imgBanner1 = (ImageView)findViewById(R.id.imgBanner1);
        imgBanner2 = (ImageView)findViewById(R.id.imgBanner2);
        imgBanner3 = (ImageView)findViewById(R.id.imgBanner3);
        imgBanner5 = (ImageView)findViewById(R.id.imgBanner5);
        imgBanner7 = (ImageView)findViewById(R.id.imgBanner7);
        imgBanner8 = (ImageView)findViewById(R.id.imgBanner8);
        imgBanner9 = (ImageView)findViewById(R.id.imgBanner9);

        tvgi = (TextView)findViewById(R.id.tvgi);
        tvni = (TextView)findViewById(R.id.tvni);
        tvdi = (TextView)findViewById(R.id.tvdi);
        tvri = (TextView)findViewById(R.id.tvri);
        tvma = (TextView)findViewById(R.id.tvma);
        tvba = (TextView)findViewById(R.id.tvba);
        tvsa = (TextView)findViewById(R.id.tvsa);

        tva = (TextView)findViewById(R.id.tva);
        tja = (TextView)findViewById(R.id.tja);
        tcha = (TextView)findViewById(R.id.tcha);
        tka = (TextView)findViewById(R.id.tka);
        tta = (TextView)findViewById(R.id.tta);
        tpa = (TextView)findViewById(R.id.tpa);
        tha = (TextView)findViewById(R.id.tha);

        tvgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㄱ");
            }
        });
        tvni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㄴ");
            }
        });
        tvdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㄷ");
            }
        });
        tvri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㄹ");
            }
        });
        tvma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅁ");
            }
        });
        tvba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅂ");
            }
        });
        tvsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅅ");
            }
        });
        tva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅇ");
            }
        });
        tja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅈ");
            }
        });
        tcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅊ");
            }
        });
        tka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅋ");
            }
        });
        tta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅌ");
            }
        });
        tpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅍ");
            }
        });
        tha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListItemSearchPan("ㅎ");
            }
        });


        imgBanner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.gjec_url)));
                startActivity(intent);
            }
        });
        imgBanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.anysho_url)));
                startActivity(intent);
            }
        });
        imgBanner3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.inettv_url)));
                startActivity(intent);
            }
        });

        imgBanner5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.ysk_url)));
                startActivity(intent);
            }
        });
        imgBanner7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.vhc_url)));
                startActivity(intent);
            }
        });
        imgBanner8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.modoo_url)));
                startActivity(intent);
            }
        });
        imgBanner9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.softmill_url)));
                startActivity(intent);
            }
        });



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

        slide_in_left = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left );
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right );

        slide_out_left = AnimationUtils.loadAnimation(this,R.anim.ani_translate_l );
        slide_in_right = AnimationUtils.loadAnimation(this,R.anim.ani_translate_r );

        vfSlider.setInAnimation(slide_in_right);
        vfSlider.setOutAnimation(slide_out_left);
    }



    private void addListItem(String searchWord,String granNumber,String fav,String business){
        items.clear();
        getDatastore();

        this.granNumber = granNumber;

        memberInfo.openc();
        List<MemberViewItem> list = memberInfo.getMemberList(searchWord,granNumber,fav,business);


        for(MemberViewItem item : list){
            items.add(item);
        }
        adapter.notifyDataSetChanged();
        rcMemberList.scrollToPosition(0);
    }

    private void addListItemSearchPan(String searchWord){
        items.clear();
        getDatastore();
        //Log.e("zzangco","여기 값이 뭔데 안돼는거야.....granNumber="+granNumber);
        memberInfo.openc();
        List<MemberViewItem> list = memberInfo.getMemberListSearch(searchWord,granNumber,favorite,business);

        memberInfo.getMemberListSearch(searchWord,granNumber,favorite,business);
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
        // automatically handle clicks on the Home/Up button, so longwww
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
        } else if (id == R.id.twentyFirst_member) {
            addListItem(searchWord,"21",favorite,business);
            toolbar.setTitle("21기");
        } else if (id == R.id.twentySecond_member) {
            addListItem(searchWord,"22",favorite,business);
            toolbar.setTitle("22기");
        } else if (id == R.id.twentyThird_member) {
            addListItem(searchWord,"23",favorite,business);
            toolbar.setTitle("23기");
        } else if (id == R.id.twentyFourth_member) {
            addListItem(searchWord,"24",favorite,business);
            toolbar.setTitle("24기");
        } else if (id == R.id.twentyFifth_member) {
            addListItem(searchWord,"25",favorite,business);
            toolbar.setTitle("25기");
        } else if (id == R.id.twentySixth_member) {
            addListItem(searchWord,"26",favorite,business);
            toolbar.setTitle("26기");
        } else if (id == R.id.twentySeventh_member) {
            addListItem(searchWord,"27",favorite,business);
            toolbar.setTitle("27기");
        } else if (id == R.id.twentyEighth_member) {
            addListItem(searchWord,"28",favorite,business);
            toolbar.setTitle("28기");
        } else if (id == R.id.twentyNinth_member) {
            addListItem(searchWord,"29",favorite,business);
            toolbar.setTitle("29기");
        } else if (id == R.id.thirtieth_member) {
            addListItem(searchWord,"30",favorite,business);
            toolbar.setTitle("30기");
        } else if (id == R.id.thirtyFirst_member) {
            addListItem(searchWord,"31",favorite,business);
            toolbar.setTitle("31기");
        } else if (id == R.id.thirtySecond_member) {
            addListItem(searchWord,"32",favorite,business);
            toolbar.setTitle("32기");
        } else if (id == R.id.thirtyThird_member) {
            addListItem(searchWord,"33",favorite,business);
            toolbar.setTitle("33기");
        } else if (id == R.id.thirtyFifth_member) {
            addListItem(searchWord,"34",favorite,business);
            toolbar.setTitle("34기");
        } else if (id == R.id.thirtyFifth_member1) {
            addListItem(searchWord,"35",favorite,business);
            toolbar.setTitle("35기");
        } else if (id == R.id.thirtyFifth_member2) {
            addListItem(searchWord,"36",favorite,business);
            toolbar.setTitle("36기");
        } else if (id == R.id.thirtyFifth_member3) {
            addListItem(searchWord,"37",favorite,business);
            toolbar.setTitle("37기");
        } else if (id == R.id.professor_member) {
            addListItem(searchWord, "99", favorite, business);
            toolbar.setTitle("교수진");
        }else if(id == R.id.all_member) {
            business = null;
            addListItem(searchWord, "00", favorite, business);
            toolbar.setTitle("전체");
        }else{
            business = item.getTitle().toString();
            addListItem(searchWord,"00",favorite,business);
            toolbar.setTitle(item.getTitle());
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        //int firstViewInt = ZZangcoUtility.randomRange(0,2);

        vfSlider.setDisplayedChild(0);
        vfSlider.startFlipping();

        vfSlider.setFlipInterval(2500);
    }
}

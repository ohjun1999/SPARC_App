package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.common.BoardAdapter;
import com.loysc.zzangco.kirikiri_snu.common.BoardInfo;
import com.loysc.zzangco.kirikiri_snu.common.BoardItem;

public class BoardActivity extends AppCompatActivity {

    private RecyclerView rcAlarList;
    private ArrayList<BoardItem> items = new ArrayList<BoardItem>();
    private BoardInfo boardInfo;
    private BoardAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String noReadView = "A";
    private CardView olc_manual;

    private ViewFlipper vfSlider;
    private ImageView imgBanner1,imgBanner2,imgBanner3,imgBanner4,imgBanner5;

    private Animation slide_out_left,slide_in_right;
    private Animation slide_in_left,slide_out_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcAlarList = (RecyclerView)findViewById(R.id.rcAlarList);
        rcAlarList.setHasFixedSize(true);
        adapter = new BoardAdapter(this,items);

        layoutManager = new LinearLayoutManager(this);
        rcAlarList.setLayoutManager(layoutManager);
        rcAlarList.setAdapter(adapter);

        olc_manual = (CardView)findViewById(R.id.olc_manual);

        vfSlider = (ViewFlipper)findViewById(R.id.vfSlider);
        imgBanner1 = (ImageView)findViewById(R.id.imgBanner1);
        imgBanner2 = (ImageView)findViewById(R.id.imgBanner2);
        imgBanner3 = (ImageView)findViewById(R.id.imgBanner3);
        imgBanner4 = (ImageView)findViewById(R.id.imgBanner4);
        imgBanner5 = (ImageView)findViewById(R.id.imgBanner5);

        olc_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OlcManualActivity.class);
                startActivity(intent);
            }
        });


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


        getSupportActionBar().setTitle("알림");
        addItems();
        MainActivity.instance.asyncDialog.dismiss();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int positon = data.getIntExtra(DetailInfoActivity.POSITION,0);
        getDatastore();
        BoardItem item = boardInfo.getBoardItem(items.get(positon).getId());

        items.set(positon,item);
        adapter.notifyItemChanged(positon);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.board,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_no_read) {
            noReadView = "T";
        }else if(id == R.id.action_all_vew) {
            noReadView = "A";
        }else if(id == R.id.action_read_ok) {
            noReadView = "F";
        }else if(id == R.id.action_mountain) {
            noReadView = "M";
        }else if(id == R.id.action_cine) {
            noReadView = "C";
        }else if(id == R.id.action_opera) {
            noReadView = "O";
        }else if(id == R.id.action_journal) {
            noReadView = "J";
        }
        addItems();
        Log.e("zzangco","BoardActivity readOK"+ noReadView);
        return super.onOptionsItemSelected(item);
    }

    private void addItems(){
        items.clear();
        getDatastore();

        boardInfo.openc();

        List<BoardItem> list = boardInfo.getBoardList(noReadView);

        for(BoardItem item : list){
            items.add(item);
        }

        adapter.notifyDataSetChanged();
        rcAlarList.scrollToPosition(0);

    }

    public BoardInfo getDatastore(){
        if(null == boardInfo){
            boardInfo = new BoardInfo(getApplication());
        }

        return boardInfo;
    }
}

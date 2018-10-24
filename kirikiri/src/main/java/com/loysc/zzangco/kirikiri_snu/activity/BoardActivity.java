package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.loysc.zzangco.kirikiri_snu.R;

import java.util.ArrayList;
import java.util.List;

import com.loysc.zzangco.kirikiri_snu.common.BoardAdapter;
import com.loysc.zzangco.kirikiri_snu.common.BoardInfo;
import com.loysc.zzangco.kirikiri_snu.common.BoardItem;

public class BoardActivity extends AppCompatActivity  {

    private RecyclerView rcAlarList;
    private ArrayList<BoardItem> items = new ArrayList<BoardItem>();
    private BoardInfo boardInfo;
    private BoardAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String noReadView = "A";
    private CardView olc_manual;

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

        olc_manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OlcManualActivity.class);
                startActivity(intent);
            }
        });


        getSupportActionBar().setTitle("알림");
        addItems();
        MainActivity.instance.asyncDialog.dismiss();
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

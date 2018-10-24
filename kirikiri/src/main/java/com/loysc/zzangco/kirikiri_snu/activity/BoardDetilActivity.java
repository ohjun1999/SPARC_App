package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

import com.loysc.zzangco.kirikiri_snu.common.BoardInfo;
import com.loysc.zzangco.kirikiri_snu.common.BoardItem;

public class BoardDetilActivity extends AppCompatActivity {
    private ImageView imgViewMak;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvImportant;
    private CardView board_card;
    private WebView wvContent;

    private BoardInfo boardInfo;
    private BoardItem item;
    private int position;

    public static final String POSITION = "positon";
    public static final String ITEM = "item";
    public static final String IMG = "img";
    public static final String CARD ="boardcardview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        item = (BoardItem)intent.getSerializableExtra(ITEM);
        position = intent.getIntExtra(POSITION,0);

        imgViewMak = (ImageView)findViewById(R.id.imgViewMak);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvContent = (TextView)findViewById(R.id.tvContent);
        tvImportant = (TextView)findViewById(R.id.tvImportant);
        board_card = (CardView)findViewById(R.id.board_card);
        wvContent = (WebView)findViewById(R.id.wvContent);

        if(null != item.getReadOK() && "Y".equals(item.getReadOK())){
            imgViewMak.setImageResource(R.drawable.btn_circle_pressed);
        }else{
            imgViewMak.setImageResource(R.drawable.btn_circle_disable_focused);
        }
        if(item.getImportant().equals("Y")){
            imgViewMak.setImageResource(R.drawable.important);
            tvImportant.setText("중요");
        }else{
            tvImportant.setText("");
        }

        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.setHorizontalScrollBarEnabled(false);
        wvContent.setVerticalScrollBarEnabled(true);
        wvContent.setBackgroundColor(0);
        wvContent.loadData(item.getContent(), "text/html; charset=UTF-8", null);

        tvTitle.setText(item.getTitle());
        tvContent.setText(Html.fromHtml(item.getContent()));

        //tvContent.setTextSize(12);
        getSupportActionBar().setTitle(item.getTitle() + " 상세 내용");

        getDatastore();
        boardInfo.openc();
        if(boardInfo.readBoard(item)){
            Log.e("zzangco","readOK");
        }else{
            Log.e("zzangco","readNO");
        }

    }

    @Override
    public void onBackPressed() {
        setReturnValue(position);
        super.onBackPressed();
    }

    private void setReturnValue(int positon){
        Intent intent = new Intent();
        intent.putExtra(POSITION,positon);
        setResult(0,intent);
    }

    public BoardInfo getDatastore(){
        if(null == boardInfo){
            boardInfo = new BoardInfo(getApplication());
        }

        return boardInfo;
    }
}

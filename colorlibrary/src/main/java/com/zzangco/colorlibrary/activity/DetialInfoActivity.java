package com.zzangco.colorlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzangco.colorlibrary.R;
import com.zzangco.colorlibrary.common.ColorInfo;
import com.zzangco.colorlibrary.common.ListViewItem;

public class DetialInfoActivity extends AppCompatActivity {
    private ImageView imageView2;
    private TextView tvColorName,tvColorCode,tvColorOrder,tvStock;

    public static final String IMG = "img";
    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String ORDER = "order";
    public static final String STOCK = "stock";
    public static final String COMPANY = "company";
    public static final String TYPE = "type";
    public static final String POSITION = "positon";

    private ColorInfo colorInfo;

    private String mConpany;
    private String mType;
    private int positon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        int img = intent.getIntExtra(IMG,0);
        String name = intent.getStringExtra(NAME);
        String code = intent.getStringExtra(CODE);
        String order = intent.getStringExtra(ORDER);
        String stock = intent.getStringExtra(STOCK);

        mConpany = intent.getStringExtra(COMPANY);
        mType = intent.getStringExtra(TYPE);
        positon = intent.getIntExtra(POSITION,0);

        tvColorName = (TextView)findViewById(R.id.tvColorName);
        tvColorCode = (TextView)findViewById(R.id.tvColorCode);
        tvColorOrder = (TextView)findViewById(R.id.tvColorOrder);
        tvStock = (TextView)findViewById(R.id.tvStock);
        imageView2 = (ImageView)findViewById(R.id.imageView2);

        tvColorName.setText(name);
        tvColorCode.setText(code);
        tvColorOrder.setText(order);
        tvStock.setText(stock);
        imageView2.setImageResource(img);



        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,tvColorName.getText() +"의 보유수를 줄였습니다.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                reduce(Integer.parseInt(tvStock.getText().toString()));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Log.e("zzangco","뒤로가기");

            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setReturnValue(positon);
        super.onBackPressed();
    }


    private void setReturnValue(int positon){
        Log.e("zzangco","dddd");
        Intent intent = new Intent();
        intent.putExtra(POSITION,positon);
        intent.putExtra(STOCK,tvStock.getText().toString());
        setResult(0,intent);

    }

    private void reduce(int stock){

        if(stock > 0){
            stock--;
        }

        ListViewItem reduceItem = new ListViewItem();
        reduceItem.setCompany(mConpany);
        reduceItem.setType(mType);
        reduceItem.setTvColorOrder(tvColorOrder.getText().toString());
        reduceItem.setEtStock(String.valueOf(stock));

        tvStock.setText(String.valueOf(stock));
        getDatastore();
        colorInfo.openc();
        colorInfo.setCount(reduceItem);
    }

    public ColorInfo getDatastore() {
        if(colorInfo == null){
            colorInfo = new ColorInfo(getApplicationContext());
        }
        return colorInfo;
    }

}

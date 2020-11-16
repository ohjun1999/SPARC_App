package com.zzangco.vallejocolorchart.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.zzangco.vallejocolorchart.R;
import com.zzangco.vallejocolorchart.common.ColorInfo;
import com.zzangco.vallejocolorchart.common.ColorListAdapter;
import com.zzangco.vallejocolorchart.common.ListViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class VallejoActivity extends AppCompatActivity {

    private ListView liColorList;
    private ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();
    private ColorListAdapter adapter;
    private EditText etFindWord;
    private Button btFind;
    private Spinner spType;
    private ColorInfo colorInfo;
    private String mType;
    private String mSearchWord;
    private String company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vallejo);
        String[] types = null;
        company = getIntent().getStringExtra(ColorInfo.COL_COMPANY);

        spType      = (Spinner)findViewById(R.id.spType);
        etFindWord  = (EditText)findViewById(R.id.etFindWord);
        btFind      = (Button)findViewById(R.id.btFind);
        liColorList = (ListView)findViewById(R.id.liColorList);

        adapter = new ColorListAdapter(this,R.layout.listview_item,items);
        liColorList.setAdapter(adapter);

        if (company.equals(ColorInfo.VALLEJO)) {
            types = getResources().getStringArray(R.array.vallejo);
        }else if (company.equals(ColorInfo.TAMIYA)) {
            types = getResources().getStringArray(R.array.tamiya);
        }else if (company.equals(ColorInfo.MODELMASTER)) {
            types = getResources().getStringArray(R.array.modelmaster);
        }

        ArrayAdapter<String> spAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,types);
        spType.setAdapter(spAdapter);

        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchWord = etFindWord.getText().toString();

                addListItem(ColorInfo.VALLEJO,mType,mSearchWord.trim());


            }
        });

        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType = (String)parent.getItemAtPosition(position);
                mSearchWord = etFindWord.getText().toString();

                addListItem(company,mType,mSearchWord.trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //addListItem(ColorInfo.VALLEJO,ColorInfo.MODELCOLOR,null);
    }

    private void addListItem(String company,String type,String searchWord){
        items.clear();
        getDatastore();
        colorInfo.openc();
        Log.e("zzangco","com=["  + company + "]");
        Log.e("zzangco","type=["  + type + "]");
        Log.e("zzangco","searchWord=["  + searchWord + "]");

        List<ListViewItem> list = colorInfo.getColorList(company,type,searchWord);
        //List<ListViewItem> list = colorInfo.getColorList(ColorInfo.VALLEJO,ColorInfo.MODELCOLOR,null);

        for(ListViewItem item: list) {
            items.add(item);
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etFindWord.getWindowToken(), 0);


        adapter.notifyDataSetChanged();
    }

    public ColorInfo getDatastore() {
        if(colorInfo == null){
            colorInfo = new ColorInfo(getApplicationContext());
        }
        return colorInfo;
    }
}

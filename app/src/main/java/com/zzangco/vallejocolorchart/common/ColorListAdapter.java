package com.zzangco.vallejocolorchart.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zzangco.vallejocolorchart.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzangco on 2017-01-11.
 */

public class ColorListAdapter extends ArrayAdapter<ListViewItem> {

    private ArrayList<ListViewItem> items;
    private Context ctx;
    private int resource;
    private ColorInfo colorInfo;

    public ColorListAdapter(Context context, int resource, ArrayList<ListViewItem> items) {
        super(context, resource, items);
        this.ctx = context;
        this.resource = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater li = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(this.resource,null);
        }

        ListViewItem item = items.get(position);

        if(item != null){
            ImageView imgVColor     = (ImageView)v.findViewById(R.id.imgVColor);
            TextView tvColorName    = (TextView)v.findViewById(R.id.tvColorName);
            TextView tvColorCode    = (TextView)v.findViewById(R.id.tvColorCode);
            TextView tvColorOrder   = (TextView)v.findViewById(R.id.tvColorOrder);
            TextView tvStock        = (TextView)v.findViewById(R.id.tvStock);
            ImageButton imgBMiner   = (ImageButton)v.findViewById(R.id.imgBMiner);
            ImageButton imgBPlus    = (ImageButton)v.findViewById(R.id.imgBPlus);

            imgVColor.setTag(position);
            tvColorName.setTag(position);
            tvColorCode.setTag(position);
            tvColorOrder.setTag(position);
            tvStock.setTag(position);
            imgBMiner.setTag(tvStock);
            imgBPlus.setTag(tvStock);

            if(imgVColor != null){
                imgVColor.setImageDrawable(item.getColorDrawable());
            }
            if(tvColorName != null){
                tvColorName.setText(item.getTvColorName());
            }
            if(tvColorCode != null){
                tvColorCode.setText(item.getTvColorCode());
            }
            if(tvColorOrder != null){
                tvColorOrder.setText(item.getTvColorOrder());
            }
            if(tvStock != null){
                tvStock.setText(item.getEtStock());
            }
            if(imgBMiner != null){
                imgBMiner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tvStock = (TextView)v.getTag();

                        int stock = Integer.parseInt(tvStock.getText().toString());
                        if(stock > 0){
                            stock--;
                            tvStock.setText(String.valueOf(stock));

                            reduce((int)tvStock.getTag(),stock);
                        }
                    }
                });
            }

            if(imgBPlus != null){
                imgBPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tvStock = (TextView)v.getTag();

                        int stock = Integer.parseInt(tvStock.getText().toString());
                        stock++;
                        tvStock.setText(String.valueOf(stock));

                        increaseStock((int)tvStock.getTag(),stock);


                    }
                });
            }
        }

        return v;
    }

    private void increaseStock(int position,int stock){
        ListViewItem increaseItem = items.get(position);
        increaseItem.setEtStock(String.valueOf(stock));
        items.set(position,increaseItem);

        getDatastore();
        colorInfo.openc();
        colorInfo.setCount(increaseItem);
    }

    private void reduce(int position,int stock){
        ListViewItem reduceItem = items.get(position);
        reduceItem.setEtStock(String.valueOf(stock));
        items.set(position,reduceItem);

        getDatastore();
        colorInfo.openc();
        colorInfo.setCount(reduceItem);
    }

    public ColorInfo getDatastore() {
        if(colorInfo == null){
            colorInfo = new ColorInfo(ctx.getApplicationContext());
        }
        return colorInfo;
    }
}

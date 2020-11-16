package com.zzangco.colorlibrary.common;

import android.content.Context;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zzangco.colorlibrary.R;
import com.zzangco.colorlibrary.activity.ColorListActivity;
import com.zzangco.colorlibrary.activity.DetialInfoActivity;

import java.util.ArrayList;

/**
 * Created by zzangco on 2017-02-01.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorViewHolder> {

    private Context mCon;
    private ArrayList<ListViewItem> mItems;
    private int lastPosition  = -1;
    private ColorInfo colorInfo;

    private Pair<View,String> pair1;
    private Pair<View,String> pair2;

    public ColorAdapter(Context mCon, ArrayList<ListViewItem> mItems) {
        this.mCon = mCon;
        this.mItems = mItems;

    }

    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;

        if(viewType == 0){
            v = inflater.inflate(R.layout.item_cardview,parent,false);
        }else{
            v = inflater.inflate(R.layout.back_item_cardview,parent,false);
        }

        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        ColorViewHolder holder = new ColorViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ColorViewHolder holder, final int position) {
        holder.imgVColor.setImageResource(mItems.get(position).getColorDrawable());
        holder.tvStock.setText(mItems.get(position).getEtStock());
        holder.tvColorOrder.setText(mItems.get(position).getTvColorOrder());
        holder.tvColorCode.setText(mItems.get(position).getTvColorCode());
        holder.tvColorName.setText(mItems.get(position).getTvColorName());

        /*holder.imgVColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItems.get(position).setFlipType(1);
                notifyItemChanged(position);
            }
        });*/




        holder.color_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stock = 0;
                stock = Integer.parseInt(mItems.get(position).getEtStock());
                Log.e("zzangco","position=" + position);
                increaseStock(position,stock + 1);
                mItems.get(position).setFlipType(1);

                notifyItemChanged(position);

            }
        });

        holder.color_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.e("zzangco","onLongClick");
                openDetali(view,position);
                return true;
            }
        });

        setAnimation(holder.color_card,position);
    }

    private void openDetali(View view,int position){
        Intent intent = new Intent(mCon,DetialInfoActivity.class);

        intent.putExtra(DetialInfoActivity.IMG,mItems.get(position).getColorDrawable());
        intent.putExtra(DetialInfoActivity.STOCK,mItems.get(position).getEtStock());
        intent.putExtra(DetialInfoActivity.ORDER,mItems.get(position).getTvColorOrder());
        intent.putExtra(DetialInfoActivity.CODE,mItems.get(position).getTvColorCode());
        intent.putExtra(DetialInfoActivity.NAME,mItems.get(position).getTvColorName());
        intent.putExtra(DetialInfoActivity.COMPANY,mItems.get(position).getCompany());
        intent.putExtra(DetialInfoActivity.TYPE,mItems.get(position).getType());
        intent.putExtra(DetialInfoActivity.POSITION,position);

        pair1 = Pair.create(view.findViewById(R.id.imageView2),"img");
        pair2 = Pair.create(view.findViewById(R.id.color_info),"info");

        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation((ColorListActivity)mCon,pair1,pair2);
        //mCon.startActivity(intent,option.toBundle());
        ((ColorListActivity) mCon).startActivityForResult(intent,0,option.toBundle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getFlipType();
    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mCon,android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);

            lastPosition = position;
        }
    }

    private void increaseStock(int position,int stock){
        ListViewItem increaseItem = mItems.get(position);
        increaseItem.setEtStock(String.valueOf(stock));
        mItems.set(position,increaseItem);

        getDatastore();
        colorInfo.openc();
        colorInfo.setCount(increaseItem);
    }

    public ColorInfo getDatastore() {
        if(colorInfo == null){
            colorInfo = new ColorInfo(mCon.getApplicationContext());
        }
        return colorInfo;
    }

    public void remove(int positon){
        mItems.remove(positon);
        notifyItemRemoved(positon);
        notifyItemRangeChanged(positon,getItemCount());
    }
}

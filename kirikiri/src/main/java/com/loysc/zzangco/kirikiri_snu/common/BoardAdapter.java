package com.loysc.zzangco.kirikiri_snu.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.loysc.zzangco.kirikiri_snu.R;
import com.loysc.zzangco.kirikiri_snu.activity.BoardActivity;
import com.loysc.zzangco.kirikiri_snu.activity.BoardDetilActivity;

import java.util.ArrayList;

/**
 * Created by zzangco on 2017-11-21.
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder> {
    private Context mCom;
    private ArrayList<BoardItem> mItems;
    private int lastPosition  = -1;

    private Pair<View,String> pair1;
    private Pair<View,String> pair2;
    private Pair<View,String> pair3;

    public BoardAdapter(Context mCom, ArrayList<BoardItem> mItems) {
        this.mCom = mCom;
        this.mItems = mItems;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;

        v =  inflater.inflate(R.layout.item_board_cardview,parent,false);

        BoardViewHolder holder = new BoardViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, final int position) {
        BoardItem item = mItems.get(position);

        Log.e("zzangco","BoardAdapter id :" + item.getId() + " readOk="+item.getReadOK());

        if(null != item.getReadOK() && "Y".equals(item.getReadOK())){
            holder.imgViewMak.setImageResource(R.drawable.btn_circle_pressed);
        }else{
            holder.imgViewMak.setImageResource(R.drawable.btn_circle_disable_focused);
        }
        if(null != item.getImportant() && item.getImportant().equals("Y")){
            holder.imgViewMak.setImageResource(R.drawable.important);
            holder.tvImportant.setText("중요");
        }else{
            holder.tvImportant.setText("");
        }
        holder.tvTitle.setText(item.getTitle());
        holder.tvContent.setText(Html.fromHtml(item.getContent()));

        holder.board_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetali(v,position);
            }
        });

        setAnimation(holder.board_card,position);
    }

    private void setAnimation(View viewToAnimate, int position){
        if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mCom,android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);

            lastPosition = position;
        }
    }

    @SuppressLint("RestrictedApi")
    private void openDetali(View view, int position){
        Intent intent = new Intent(mCom, BoardDetilActivity.class);
        intent.putExtra(BoardDetilActivity.POSITION,position);
        intent.putExtra(BoardDetilActivity.ITEM,mItems.get(position));

        pair1 = Pair.create(view.findViewById(R.id.board_card),BoardDetilActivity.CARD);

        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation((BoardActivity)mCom,pair1);
        ((BoardActivity) mCom).startActivityForResult(intent,0,option.toBundle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

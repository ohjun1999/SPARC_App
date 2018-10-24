package com.loysc.zzangco.kirikiri_snu.common;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loysc.zzangco.kirikiri_snu.R;

/**
 * Created by zzangco on 2017-11-21.
 */

public class BoardViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgViewMak;
    public TextView tvTitle;
    public TextView tvContent;
    public TextView tvImportant;
    public CardView board_card;

    public BoardViewHolder(View itemView) {
        super(itemView);
        board_card = (CardView)itemView.findViewById(R.id.board_card);
        imgViewMak = (ImageView)itemView.findViewById(R.id.imgViewMak);
        tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
        tvContent = (TextView)itemView.findViewById(R.id.tvContent);
        tvImportant = (TextView)itemView.findViewById(R.id.tvImportant);
    }
}

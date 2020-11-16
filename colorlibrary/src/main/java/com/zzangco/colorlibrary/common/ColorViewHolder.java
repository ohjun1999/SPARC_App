package com.zzangco.colorlibrary.common;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzangco.colorlibrary.R;

/**
 * Created by zzangco on 2017-02-01.
 */

public class ColorViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgVColor;
    public TextView tvColorName;
    public TextView tvColorCode;
    public TextView tvColorOrder;
    public TextView tvStock;
    public CardView color_card;

    public ColorViewHolder(View itemView) {
        super(itemView);
        color_card = (CardView)itemView.findViewById(R.id.color_card);
        imgVColor = (ImageView)itemView.findViewById(R.id.imageView2);
        tvColorName = (TextView)itemView.findViewById(R.id.tvColorName);
        tvColorCode = (TextView)itemView.findViewById(R.id.tvColorCode);
        tvColorOrder = (TextView)itemView.findViewById(R.id.tvColorOrder);
        tvStock = (TextView)itemView.findViewById(R.id.tvStock);
    }
}

package com.loysc.zzangco.kirikiri_snu.common;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.loysc.zzangco.kirikiri_snu.R;

/**
 * Created by zzangco on 2017-11-09.
 */

public class MemberViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgMemberPic;
    public TextView tvMemberName;
    public TextView tvBirthday;
    public TextView tvHandphone;
    public TextView tvComname;
    public TextView tvEmail;
    public ImageView imgHandPhone;
    public ImageView imgEmail;
    public TextView tvDept;
    public TextView tvPosition;
    public TextView tvBusinessName;
    public TextView tvHomepage;
    public TableRow trHomepage;

    public CardView member_card;

    public MemberViewHolder(View itemView) {
        super(itemView);

        member_card = (CardView)itemView.findViewById(R.id.member_card);
        imgMemberPic = (ImageView)itemView.findViewById(R.id.imgMemberPic);
        tvMemberName = (TextView)itemView.findViewById(R.id.tvMemberName);
        tvBirthday = (TextView)itemView.findViewById(R.id.tvBirthday);
        tvHandphone = (TextView)itemView.findViewById(R.id.tvHandphone);
        tvComname = (TextView)itemView.findViewById(R.id.tvComname);
        tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
        imgHandPhone = (ImageView)itemView.findViewById(R.id.imgHandPhone);
        imgEmail = (ImageView)itemView.findViewById(R.id.imgEmail);
        tvDept = (TextView)itemView.findViewById(R.id.tvDept);
        tvPosition = (TextView)itemView.findViewById(R.id.tvPosition);
        tvBusinessName = (TextView)itemView.findViewById(R.id.tvBusinessName);
        tvHomepage = (TextView)itemView.findViewById(R.id.tvHomepage);
        trHomepage = (TableRow)itemView.findViewById(R.id.trHomepage);
    }
}

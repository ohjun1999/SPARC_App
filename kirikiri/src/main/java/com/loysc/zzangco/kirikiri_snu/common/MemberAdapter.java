package com.loysc.zzangco.kirikiri_snu.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.loysc.zzangco.kirikiri_snu.R;
import com.loysc.zzangco.kirikiri_snu.activity.CallPhoneActivity;
import com.loysc.zzangco.kirikiri_snu.activity.DetailInfoActivity;
import com.loysc.zzangco.kirikiri_snu.activity.MemberListActivity;

import java.util.ArrayList;

/**
 * Created by zzangco on 2017-11-09.
 */

public class MemberAdapter extends RecyclerView.Adapter<MemberViewHolder> {

    private Context mCom;
    private ArrayList<MemberViewItem> mItems;
    private int lastPosition  = -1;

    private Pair<View,String> pair1;
    private Pair<View,String> pair2;
    private Pair<View,String> pair3;

    public MemberAdapter(Context mCon,ArrayList<MemberViewItem> mItems){
        this.mCom = mCon;
        this.mItems = mItems;
    }

    @Override
    public MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;

        if(viewType == 0){
            v = inflater.inflate(R.layout.item_member_cardview,parent,false);
        }else{
            v = inflater.inflate(R.layout.back_item_member_cardview,parent,false);
        }

        MemberViewHolder holder = new MemberViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(MemberViewHolder holder, final int position) {
        holder.imgMemberPic.setImageResource(mItems.get(position).getMemberPicDrawable());
        holder.tvMemberName.setText(mItems.get(position).getMemberName() + "(" + mItems.get(position).getGrannumber() + ")");
        holder.tvBirthday.setText(mItems.get(position).getBirthday() + "[" + mItems.get(position).getSunmoon()+"]");
        holder.tvDept.setText(mItems.get(position).getDept());
        holder.tvPosition.setText(mItems.get(position).getPosition());

        if(mItems.get(position).getComname().equals("OLP")){
            holder.tvComname.setText(mItems.get(position).getPosition());
        }else{
            holder.tvComname.setText(mItems.get(position).getComname() + " (" + mItems.get(position).getPosition() + ")");
        }

        holder.tvHandphone.setText(mItems.get(position).getHandphone());


        holder.member_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetali(v,position);
            }
        });

        holder.member_card.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                callPhone(v,position);
                return true;
            }
        });

        holder.imgHandPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callPhone(v,position);
            }
        });

        holder.imgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(position);
            }
        });


        holder.tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(position);
            }
        });

        holder.tvHandphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+mItems.get(position).getHandphone()));
                mCom.startActivity(intent);
            }
        });

        holder.tvEmail.setText(mItems.get(position).getEmail());


        setAnimation(holder.member_card,position);
    }

    private void callPhone(View view,int position){
        Intent intent = new Intent(mCom, CallPhoneActivity.class);
        intent.putExtra(CallPhoneActivity.MEMBERITEMS,mItems.get(position));

        //pair2 = Pair.create(view.findViewById(R.id.member_info),DetailInfoActivity.MEMBERINFO);
        //pair3 = Pair.create(view.findViewById(R.id.member_card),"membercard");
        //ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation((MemberListActivity)mCom,pair3,pair2);
        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation((MemberListActivity)mCom);
        ((MemberListActivity) mCom).startActivity(intent,option.toBundle());
    }

    private void sendEmail(int position){
        //EmailAddress = "olc@sogang.ac.kr";

        String olc_office_email = mItems.get(position).getEmail();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        String[] address = {olc_office_email};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        mCom.startActivity(email);

    }

    @SuppressLint("RestrictedApi")
    private void openDetali(View view, int position){
        Intent intent = new Intent(mCom, DetailInfoActivity.class);

        intent.putExtra(DetailInfoActivity.IMG,mItems.get(position).getMemberPicDrawable());
        intent.putExtra(DetailInfoActivity.MEMBERNAME,mItems.get(position).getMemberName());
        intent.putExtra(DetailInfoActivity.BIRTHDAY,mItems.get(position).getBirthday());
        intent.putExtra(DetailInfoActivity.COMNAME,mItems.get(position).getComname());
        intent.putExtra(DetailInfoActivity.HANDPHONE,mItems.get(position).getHandphone());
        intent.putExtra(DetailInfoActivity.MEMBERITEMS,mItems.get(position));
        intent.putExtra(DetailInfoActivity.POSITION,position);

        pair1 = Pair.create(view.findViewById(R.id.imgMemberPic),DetailInfoActivity.IMG);
        pair2 = Pair.create(view.findViewById(R.id.member_info),DetailInfoActivity.MEMBERINFO);
        pair3 = Pair.create(view.findViewById(R.id.member_card),"membercard");


        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation((MemberListActivity)mCom,pair1,pair2,pair3);
        ((MemberListActivity) mCom).startActivityForResult(intent,0,option.toBundle());
        //((MemberListActivity) mCom).startActivity(intent,option.toBundle());

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
            Animation animation = AnimationUtils.loadAnimation(mCom,android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);

            lastPosition = position;
        }
    }

    public void remove(int positon){
        mItems.remove(positon);
        notifyItemRemoved(positon);
        notifyItemRangeChanged(positon,getItemCount());
    }
}

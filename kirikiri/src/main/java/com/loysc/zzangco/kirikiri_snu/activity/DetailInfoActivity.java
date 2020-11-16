package com.loysc.zzangco.kirikiri_snu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loysc.zzangco.kirikiri_snu.R;

import com.loysc.zzangco.kirikiri_snu.common.MemberInfo;
import com.loysc.zzangco.kirikiri_snu.common.MemberViewItem;

public class DetailInfoActivity extends AppCompatActivity {

    private ImageView imgMemberPic;
    private TextView tvMemberName,tvBirthday,tvComname,tvHandphone;
    private TextView tvGraNumber,tvEmail,tvDept,tvPosition,tvComphone,tvComAddress,tvHomePhone,tvHomeAddress,tvFax,tvBusinessName;
    private FloatingActionButton fab;

    public static final String IMG = "img";
    public static final String MEMBERNAME = "name";
    public static final String BIRTHDAY = "birthday";
    public static final String HANDPHONE = "handphone";
    public static final String COMNAME = "comname";
    public static final String MEMBERINFO = "info";
    public static final String MEMBERITEMS = "item";
    public static final String POSITION = "positon";

    private MemberInfo memberInfo;
    private MemberViewItem item;

    private int positon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        int img = intent.getIntExtra(IMG,0);
        String memberName = intent.getStringExtra(MEMBERNAME);
        String birthday = intent.getStringExtra(BIRTHDAY);
        String handPhone = intent.getStringExtra(HANDPHONE);
        String comName = intent.getStringExtra(COMNAME);
        item = (MemberViewItem) intent.getSerializableExtra(MEMBERITEMS);
        positon = intent.getIntExtra(POSITION,0);

        imgMemberPic = (ImageView)findViewById(R.id.imgMemberPic);

        tvMemberName = (TextView)findViewById(R.id.tvMemberName);
        tvBirthday = (TextView)findViewById(R.id.tvBirthday);
        tvComname = (TextView)findViewById(R.id.tvComname);

        tvHandphone = (TextView)findViewById(R.id.tvHandphone);

        imgMemberPic.setImageResource(img);
        tvMemberName.setText(memberName);
        tvBirthday.setText(birthday);
        tvHandphone.setText(handPhone);
        tvComname.setText(comName);

        tvGraNumber = (TextView)findViewById(R.id.tvGraNumber);

        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvDept = (TextView)findViewById(R.id.tvDept);
        tvPosition = (TextView)findViewById(R.id.tvPosition);

        tvComphone = (TextView)findViewById(R.id.tvComphone);
        tvComAddress = (TextView)findViewById(R.id.tvComAddress);
        tvHomePhone = (TextView)findViewById(R.id.tvHomePhone);
        tvHomeAddress = (TextView)findViewById(R.id.tvHomeAddress);
        tvFax = (TextView)findViewById(R.id.tvFax);
        tvBusinessName = (TextView)findViewById(R.id.tvBusinessName);

        tvGraNumber.setText(item.getGrannumber());
        tvEmail.setText(item.getEmail());
        tvDept.setText(item.getDept());
        tvPosition.setText(item.getPosition());
        tvComphone.setText(item.getComphone());
        tvComAddress.setText(item.getComAddress());
        tvHomePhone.setText(item.getHomephone());
        tvHomeAddress.setText(item.getHomeAddress());
        tvFax.setText(item.getFax());
        tvBusinessName.setText(item.getBusiname());


        fab = (FloatingActionButton) findViewById(R.id.fab);

        if(null != item.getFav() && item.getFav().equals("T")){
            fab.setImageResource(R.drawable.ic_delete);
        }else{
            fab.setImageResource(R.drawable.ic_input_get);
        }
        getSupportActionBar().setTitle(memberName+" 상세정보");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != item.getFav() && item.getFav().equals("T")) {
                    Snackbar.make(view, item.getMemberName() + "님을 즐겨찾기에서 제거했습니다.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    removeFavorite();
                    fab.setImageResource(R.drawable.ic_input_get);
                } else {
                    Snackbar.make(view, item.getMemberName() + "님을 즐겨찾기에 추가했습니다.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    addFavorite();
                    fab.setImageResource(R.drawable.ic_delete);
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        setReturnValue(positon);
        super.onBackPressed();
    }

    private void setReturnValue(int positon){
        Intent intent = new Intent();
        intent.putExtra(POSITION,positon);
        setResult(0,intent);
    }
    private void addFavorite(){
        getDatastore();
        memberInfo.openc();
        memberInfo.addFavorite(item);

    }

    private void removeFavorite(){
        getDatastore();
        memberInfo.openc();
        memberInfo.removeFavorite(item);

    }

    private MemberInfo getDatastore(){
        if(null == memberInfo){
            memberInfo = new MemberInfo(getApplication());
        }
        return memberInfo;
    }

}

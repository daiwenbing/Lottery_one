package com.dwb.lottery.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.dwb.lottery.R;
import com.dwb.lottery.application.DSLApplication;
import com.dwb.lottery.fragment.HomeInformationFragment;
import com.dwb.lottery.fragment.HomeInliveFragment;
import com.dwb.lottery.fragment.HomepageFragment;
import com.dwb.lottery.fragment.HomepageMoreFragment;
import com.dwb.lottery.immersive.UltimateBar;
import com.dwb.lottery.service.DownLoadAppService;
import com.dwb.lottery.utils.Constant;
import com.dwb.lottery.utils.DSLConnections;
import com.dwb.lottery.utils.DSLContants;
import com.dwb.lottery.utils.DateChange;
import com.dwb.lottery.utils.NetWorkUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private static Boolean mIsExit = false;
    private FragmentTransaction transcation;
    private FragmentManager manager;
    private HomepageFragment homepageFragment;
    private HomeInformationFragment informationFragment;
    private HomeInliveFragment homeInliveFragment;
    private HomepageMoreFragment homeMoreFragment;
    private LinearLayout layout_shouye,layout_zixun,layout_moer,layout_live;
    private ImageView img_shouye,img_zixun,img_more,img_live;
    private TextView txt_shouye,txt_zixun,txt_more,txt_live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DSLApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        manager=getSupportFragmentManager();
        if (savedInstanceState != null) {
            homepageFragment=(HomepageFragment) manager.findFragmentByTag("homepageFragment") ;
            informationFragment=(HomeInformationFragment) manager.findFragmentByTag("informationFragment");
            homeMoreFragment= (HomepageMoreFragment) manager.findFragmentByTag("homeMoreFragment");
            homeInliveFragment= (HomeInliveFragment) manager.findFragmentByTag("homeInliveFragment");
        }
        initview();
//        check_updateapp();
//        check_app();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (NetWorkUtil.checkPackInfo(this, Constant.GO_Package)) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(Constant.GO_Package);
            startActivity(intent);
            DSLApplication.getInstance().onTerminate();
        }
        returnback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    /**
     * 检测更新
     */
    public void check_updateapp(){
            CustomVersionDialogActivity.customVersionDialogIndex = 2;
//        CustomVersionDialogActivity.isCustomDownloading=true;
            VersionParams.Builder builder=new VersionParams.Builder()
                    .setRequestUrl(DSLConnections.APP_check)
                    .setService(DownLoadAppService.class)
                    .setShowDownloadingDialog(true)
                    .setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
            AllenChecker.startVersionCheck(MainActivity.this, builder.build());
    }
    /**
 +     * 检测更新
 +     */
            public void check_app(){
                PackageManager packageManager = getPackageManager();
                if (NetWorkUtil.checkPackInfo(this, Constant.GO_Package)) {
                        Intent intent = packageManager.getLaunchIntentForPackage(Constant.GO_Package);
                        startActivity(intent);
                        DSLApplication.getInstance().onTerminate();
                    } else {
                        String dateStr = "2018-4-29 1:21:28";
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                                Date data1= format.parse(dateStr);
                                Date now = new Date();
                                if (DateChange.differentDays(data1,now)>0){
                                        CustomVersionDialogActivity.customVersionDialogIndex = 2;
                                        CustomVersionDialogActivity.isCustomDownloading=true;
                                                VersionParams.Builder builder=new VersionParams.Builder()
                                                        .setRequestUrl(DSLConnections.APP_check)
                                                        .setService(DownLoadAppService.class)
                                                        .setShowDownloadingDialog(true)
                                                        .setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
                                        AllenChecker.startVersionCheck(MainActivity.this, builder.build());
                                    }
                        } catch (ParseException e)
                        {e.printStackTrace();}
                }
            }
    public void initview(){
        layout_shouye= (LinearLayout) findViewById(R.id.layout_shouye);
        layout_zixun= (LinearLayout) findViewById(R.id.layout_zixun);
        layout_moer= (LinearLayout) findViewById(R.id.layout_more);
        layout_live=findViewById(R.id.layout_live);
        txt_shouye=(TextView) findViewById(R.id.txt_shouye);
        txt_zixun=(TextView) findViewById(R.id.txt_zixun);
        txt_more=(TextView) findViewById(R.id.txt_more);
        txt_live=findViewById(R.id.txt_live);
        img_shouye=(ImageView) findViewById(R.id.img_shouye);
        img_zixun=(ImageView) findViewById(R.id.img_zixun);
        img_more=(ImageView) findViewById(R.id.img_more);
        img_live=findViewById(R.id.img_live);
        layout_shouye.setOnClickListener(this);
        layout_zixun.setOnClickListener(this);
        layout_moer.setOnClickListener(this);
        layout_live.setOnClickListener(this);
    }
    public void returnback(){
        setimg();
        if(DSLContants.nums==1){
            SelectFragment(1);
            txt_shouye.setTextColor(getResources().getColor(R.color.main_typeface));
            img_shouye.setImageDrawable(getResources().getDrawable(R.mipmap.icon_fx_01a));
        }
        else if (DSLContants.nums==2) {
            SelectFragment(2);
            txt_zixun.setTextColor(getResources().getColor(R.color.main_typeface));
            img_zixun.setImageDrawable(getResources().getDrawable(R.mipmap.icon_goucai_2));
        }
        else if (DSLContants.nums==3) {
            SelectFragment(3);
            txt_more.setTextColor(getResources().getColor(R.color.main_typeface));
            img_more.setImageDrawable(getResources().getDrawable(R.mipmap.icon_wode_2));
        } else if (DSLContants.nums==4) {
            SelectFragment(4);
            txt_live.setTextColor(getResources().getColor(R.color.main_typeface));
            img_live.setImageDrawable(getResources().getDrawable(R.mipmap.icon_live2));
        }

    }
    /**
     * 设置背景
     */
    public void setimg(){
        txt_shouye.setTextColor(getResources().getColor(R.color.hui));
        txt_zixun.setTextColor(getResources().getColor(R.color.hui));
        txt_more.setTextColor(getResources().getColor(R.color.hui));
        txt_live.setTextColor(getResources().getColor(R.color.hui));
        img_shouye.setImageDrawable(getResources().getDrawable(R.mipmap.icon_fx_02));
        img_zixun.setImageDrawable(getResources().getDrawable(R.mipmap.icon_goucai));
        img_more.setImageDrawable(getResources().getDrawable(R.mipmap.icon_wode));
        img_live.setImageDrawable(getResources().getDrawable(R.mipmap.icon_live));
    }
    public void SelectFragment(int num){
        //开启事物
        transcation=manager.beginTransaction();
        if(homepageFragment!=null){
            transcation.hide(homepageFragment);
        }
        if(informationFragment!=null){
            transcation.hide(informationFragment);
        }
        if (homeInliveFragment!=null){
            transcation.hide(homeInliveFragment);
        }
        if (homeMoreFragment!=null){
            transcation.hide(homeMoreFragment);
        }
        if(num==1){
            if(homepageFragment==null) {
                homepageFragment=new HomepageFragment();
                transcation.add(R.id.content, homepageFragment,"homepageFragment");
            }
            transcation.show(homepageFragment);
        }
        else if (num==2) {
            if(informationFragment==null) {
                informationFragment=new HomeInformationFragment();
                transcation.add(R.id.content, informationFragment,"informationFragment");
            }
            transcation.show(informationFragment);
        }
        else if (num==3) {
            if(homeMoreFragment==null) {
                homeMoreFragment=new HomepageMoreFragment();
                transcation.add(R.id.content,homeMoreFragment,"homeMoreFragment");
            }
            transcation.show(homeMoreFragment);
        }
        else if (num==4) {
            if(homeInliveFragment==null) {
                homeInliveFragment=new HomeInliveFragment();
                transcation.add(R.id.content,homeInliveFragment,"homeInliveFragment");
            }
            transcation.show(homeInliveFragment);
        }
        //提交
        transcation.commit();
    }
    /**
     * 返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
                return false;
            } else {
                DSLApplication.getInstance().onTerminate();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        setimg();
        switch (view.getId()){
            case R.id.layout_shouye:
                DSLContants.nums=1;
                txt_shouye.setTextColor(getResources().getColor(R.color.main_typeface));
                img_shouye.setImageDrawable(getResources().getDrawable(R.mipmap.icon_fx_01a));
                SelectFragment(1);
                break;
            case R.id.layout_zixun:
                DSLContants.nums=2;
                txt_zixun.setTextColor(getResources().getColor(R.color.main_typeface));
                img_zixun.setImageDrawable(getResources().getDrawable(R.mipmap.icon_goucai_2));
                SelectFragment(2);
                break;
            case R.id.layout_more:
                DSLContants.nums=3;
                txt_more.setTextColor(getResources().getColor(R.color.main_typeface));
                img_more.setImageDrawable(getResources().getDrawable(R.mipmap.icon_wode_2));
                SelectFragment(3);
                break;
            case R.id.layout_live:
                DSLContants.nums=4;
                txt_live.setTextColor(getResources().getColor(R.color.main_typeface));
                img_live.setImageDrawable(getResources().getDrawable(R.mipmap.icon_live2));
                SelectFragment(4);
                break;

        }
    }
}

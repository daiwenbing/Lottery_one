package com.dwb.lottery.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dwb.lottery.R;
import com.dwb.lottery.activity.ListActivity;
import com.dwb.lottery.activity.WebviewActivity;
import com.dwb.lottery.utils.Constant;
import com.dwb.lottery.viewpager_adaper.AdvAdapter;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页
 * Created by dwb on 2018/3/26.
 */

public class HomepageFragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.layout_kaijiang)
    LinearLayout layout_kaijiang;
    @BindView(R.id.layout_gonggao)
    LinearLayout layout_gonggao;
    @BindView(R.id.layout_jingcai)
    LinearLayout layout_jingcai;
    @BindView(R.id.adv_pager)
    ViewPager adv_pager;
    @BindView(R.id.viewGroup)
    LinearLayout viewGroup;
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.homepage_layout_kj)
    RelativeLayout homepage_layout_kj;
    @BindView(R.id.homepage_layout_gg)
    RelativeLayout homepage_layout_gg;
    @BindView(R.id.homepage_layout_3)
    RelativeLayout homepage_layout_3;
    @BindView(R.id.homepage_layout_4)
    RelativeLayout homepage_layout_4;
    @BindView(R.id.homepage_layout_5)
    RelativeLayout homepage_layout_5;
    @BindView(R.id.homepage_layout_6)
    RelativeLayout homepage_layout_6;
    @BindView(R.id.homepage_layout_7)
    RelativeLayout homepage_layout_7;
    @BindView(R.id.homepage_layout_8)
    RelativeLayout homepage_layout_8;
    @BindView(R.id.homepage_layout_9)
    RelativeLayout homepage_layout_9;
    @BindView(R.id.homepage_layout_10)
    RelativeLayout homepage_layout_10;
    @BindView(R.id.layout_img)
    RelativeLayout layout_img;
    private Intent mintent;
    private String[] imageUrls;
    private List<String> banner_list;
    //////////////////////////viewpager//////////////////
    private View view_banner;
    private ImageView img1;
    private List<View> advPics;
    private int item;
    private ImageView[] imageViews = null;
    private ImageView imageView = null;
    private AdvAdapter advAdapter;
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isContinue = true;

    //////////////////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagefragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    public void initview() {
        bind_banner();
        initViewPager();
        bindmequrelist();
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title", textView.getText().toString());
                mintent.putExtra("url", "http://mapi.159cai.com/discovery/notice/2018/0320/32942.html");
                startActivity(mintent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void bindmequrelist() {
        List<String> info = new ArrayList<>();
        info.add("关于发单【跟单异常】调整公告");
        info.add("关于【预约发单调整】公告");
        marqueeView.startWithList(info);

// 在代码里设置自己的动画
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
    }

    /**
     * 初始化banner数据
     */
    public void bind_banner() {
           /*    this.imgurllist.add("http://img.500.com/upimages/wap/img/20171215102026226.jpg?!1349.webp");
        this.imgurllist.add("http://img.500.com/upimages/wap/img/20171201174313643.jpg?!1349.webp");
        this.imgurllist.add("http://m.159cai.com/uploads/171210/2-1G2101H522149.jpg");
        this.imgurllist.add("http://m.159cai.com/uploads/171104/2-1G1041602113P.png");

        http://m.159cai.com/uploads/180328/2-1P32P92AL45.jpg http://m.159cai.com/sjbguanyajun/guanjun.html
        */
        imageUrls = new String[]{
                "http://m.dididapiao.com/upload/cms/news/images/2017/12/20/tn_up_201712201706000007.png",
                "http://m.159cai.com/uploads/180328/2-1P32P92AL45.jpg",
                "http://m.dididapiao.com/upload/cms/news/images/2018/3/27/tn_up_201803271034000037.png",
                "http://m.159cai.com/uploads/180302/2-1P302114923240.jpg",
                "http://m.159cai.com/uploads/180313/2-1P313105F0448.png"
        };
        banner_list = new ArrayList();
        banner_list.add("http://m.lottech.cn/vue/views/didi/betRedPlan.html#/redPlan/1/D33269DE20A5278C6517B89FC1940850-384234?hiddenHead=true");
        banner_list.add("http://m.159cai.com/sjbguanyajun/guanjun.html");
        banner_list.add("http://mapi.159cai.com/discovery/news/football/2018/0330/33094.html");
        banner_list.add("http://m.159cai.com/discovery/news/football/2018/0302/32651.html");
        banner_list.add("http://mapi.159cai.com/discovery/news/basketball/2018/0328/33054.html");

    }

    private void initViewPager() {
//	      这里存放的是四张广告背景
        //http://m.lifan99.com/static/images/home/banner-1.jpg
        advPics = new ArrayList<View>();
        for (int i = 0; i < banner_list.size(); i++) {
            img1 = new ImageView(getActivity().getApplicationContext());
            img1.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(this)
                    .load(imageUrls[i])
                    .placeholder(R.mipmap.loag_station_banner)
                    .into(img1);
            advPics.add(img1);
        }
        //对imageviews进行填充
        imageViews = new ImageView[advPics.size()];
        /**
         * 有几张图片 下面就显示几个小圆点
         */
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        for (int i = 0; i < advPics.size(); i++) {
            LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置每个小圆点距离左边的间距
            margin.setMargins(Constant.WIDTHPIXELS / 10, 0, 0, 0);
            imageView = new ImageView(getActivity());
            //设置每个小圆点的宽高
            imageView.setLayoutParams(new LinearLayout.LayoutParams(20, 20));
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i]
                        .setBackgroundResource(R.mipmap.circle_blude_select);
            } else {
                //其他图片都设置未选中状态
                imageViews[i]
                        .setBackgroundResource(R.mipmap.circle_blue);
            }
            viewGroup.addView(imageViews[i], margin);
        }

        advAdapter = new AdvAdapter(advPics);
        adv_pager.setAdapter(advAdapter);
        advAdapter.notifyDataSetChanged();
        adv_pager.setOnPageChangeListener(new GuidePageChangeListener());
        adv_pager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (isContinue) {
                        viewHandler.sendEmptyMessage(what.get());
                        whatOption();
                    }
                }
            }

        }).start();

    }

    private void whatOption() {
        what.incrementAndGet();
        if (what.get() > imageViews.length - 1) {
            what.getAndAdd(-imageViews.length);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            return;
        }
    }

    private final Handler viewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            adv_pager.setCurrentItem(msg.what);
            super.handleMessage(msg);

        }

    };

    @OnClick({R.id.homepage_layout_kj, R.id.homepage_layout_gg, R.id.layout_img,R.id.layout_kaijiang, R.id.layout_gonggao, R.id.layout_jingcai,
            R.id.homepage_layout_3, R.id.homepage_layout_4, R.id.homepage_layout_5, R.id.homepage_layout_6, R.id.homepage_layout_7, R.id.homepage_layout_8, R.id.homepage_layout_9, R.id.homepage_layout_10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homepage_layout_kj:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/ssq/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_gg:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/tcdlt/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.layout_kaijiang:
                mintent = new Intent(getActivity(), ListActivity.class);
                startActivity(mintent);
                break;
            case R.id.layout_gonggao:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title","公告信息");
                mintent.putExtra("url", "http://m.159cai.com/gong/index.html");
                startActivity(mintent);
                break;
            case R.id.layout_jingcai:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("title","每日竞猜");
                mintent.putExtra("url", "http://m.159cai.com/gong/dailycontest.html");
                startActivity(mintent);
                break;
            case R.id.layout_img:
                //第一资讯
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.159cai.com/gong/news.html");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_3:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/fc3d/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_4:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/y11x5/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_5:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/jxk3/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_6:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/jsk3/index;jsessionid=A69D197C8A190FBA482D85A53F6BC92B.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_7:
            case R.id.homepage_layout_8:
            case R.id.homepage_layout_9:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/tcpl3/index;jsessionid=925822A1DDCEBE6924C1D2CE8B630820.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
            case R.id.homepage_layout_10:
                mintent = new Intent(getActivity(), WebviewActivity.class);
                mintent.putExtra("url", "http://m.dididapiao.com/bet/tcpl5/index;jsessionid=925822A1DDCEBE6924C1D2CE8B630820.w3?clear=1&agentId=100107");
                startActivity(mintent);
                break;
        }
    }
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int arg0) {

            item = arg0;
            view_banner = advPics.get(arg0);
            view_banner.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        String url = banner_list.get(item);
                        mintent = new Intent(getActivity(), WebviewActivity.class);
                        mintent.putExtra("url", url);
                        mintent.putExtra("title", "详情");
                        startActivity(mintent);
                    } catch (NullPointerException e) {
                    }
                }
            });
            what.getAndSet(arg0);
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(R.mipmap.circle_blude_select);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.mipmap.circle_blue);
                }
            }
        }
    }

}

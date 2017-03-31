package com.kxt.kxtcjst.index;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.index.persenter.MainPersenter;

import butterknife.BindView;

public class SuperPlayerActivity  extends CommunalActivity   {

    @BindView(R.id.filter_img)
    RelativeLayout filterIcon;
    @BindView(R.id.tab_main)
    CommonTabLayout tabMain;
    @BindView(R.id.view_pager)
    ViewPager viewpagerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_main);
//        mainPersenter=new MainPersenter();
//        mainPersenter.attach(this);
//        mainPersenter.initTabs(tabMain,mTitles);
//        mainPersenter.initViewPager(viewpagerMain, getSupportFragmentManager());
    }


}

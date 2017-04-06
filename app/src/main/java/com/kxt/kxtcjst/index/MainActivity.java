package com.kxt.kxtcjst.index;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.flyco.tablayout.SlidingTabLayout;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.DoubleClickUtils;
import com.kxt.kxtcjst.index.jsonBean.VedioTitleBean;
import com.kxt.kxtcjst.index.persenter.MainPersenter;
import com.kxt.kxtcjst.index.view.IMainView;
import com.library.manager.ActivityManager;
import com.library.util.volley.load.PageLoadLayout;
import com.library.widget.window.ToastView;
import com.socks.library.KLog;

import butterknife.BindView;

public class MainActivity extends CommunalActivity implements IMainView {
    @BindView(R.id.page_load)
    PageLoadLayout pageLoad;
    private MainPersenter mainPersenter;
    private boolean isShow = false;
    @BindView(R.id.filter_img)
    RelativeLayout filterIcon;
    @BindView(R.id.tab_main)
    SlidingTabLayout tabMain;
    @BindView(R.id.view_pager)
    ViewPager viewpagerMain;

    private String[] mTitles = {"推荐", "财经视听", "财经汇"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_main);
        mainPersenter = new MainPersenter();
        mainPersenter.attach(this);
        mainPersenter.getSpTitlePer(pageLoad);
    }


    @Override
    public void onBackPressed() {
        if (!DoubleClickUtils.isFastDoubleClick(2000)) {
            ToastView.makeText3(this, "再按一次退出");
        } else {
            ActivityManager.getInstance().finishAllActivity();
            super.onBackPressed();
        }
    }

    @Override
    public void ShowAdPopView() {
        if (CjstApplicaion.getInstance().getAdConfigBean() != null) {
            mainPersenter.showAdPop(filterIcon);
        }
    }

    @Override
    public void initTabView(VedioTitleBean vedioTitleBean) {
        mainPersenter.initTabs(tabMain, vedioTitleBean);
        mainPersenter.initViewPager(viewpagerMain, getSupportFragmentManager(),vedioTitleBean);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
// TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (!isShow) {
                if (BaseUtils.isNetworkConnected(this)) {
                    if (CjstApplicaion.getInstance().getUpdateBean() != null) {
                        KLog.json(JSON.toJSONString(CjstApplicaion.getInstance().getUpdateBean()));
                        mainPersenter.showUpdatePop(filterIcon);
                    } else if (CjstApplicaion.getInstance().getAdConfigBean() != null) {
                        KLog.json(JSON.toJSONString(CjstApplicaion.getInstance().getAdConfigBean()));
                        mainPersenter.showAdPop(filterIcon);
                    }
                    isShow = true;

                }

            }


        }
    }
}

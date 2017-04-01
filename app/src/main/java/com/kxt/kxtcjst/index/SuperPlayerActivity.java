package com.kxt.kxtcjst.index;


import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.index.persenter.SuperPayPersenter;
import com.kxt.kxtcjst.index.view.ISuperPlayView;
import com.library.util.volley.load.PageLoadLayout;
import com.superplayer.library.SuperPlayer;

import butterknife.BindView;

public class SuperPlayerActivity extends CommunalActivity implements ISuperPlayView {


    SuperPayPersenter superPayPersenter;

    @BindView(R.id.view_super_player)
    SuperPlayer viewSuperPlayer;
    @BindView(R.id.layout)
    LinearLayout layout;

    String playId;
    @BindView(R.id.page_load)
    PageLoadLayout pageLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_super_play);
        superPayPersenter = new SuperPayPersenter();
        superPayPersenter.attach(this);
        // 获取意图中的数据
        Intent intent = getIntent();
        playId = intent.getStringExtra("id");
        superPayPersenter.getPlayIdVideoData(pageLoad,playId);
//        mainPersenter.initViewPager(viewpagerMain, getSupportFragmentManager());
    }


    @Override
    public void playTuijain() {

    }
}

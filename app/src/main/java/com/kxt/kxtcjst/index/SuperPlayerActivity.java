package com.kxt.kxtcjst.index;


import android.os.Bundle;
import android.widget.LinearLayout;

import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.index.persenter.SuperPayPersenter;
import com.kxt.kxtcjst.index.view.ISuperPlayView;
import com.superplayer.library.SuperPlayer;

import butterknife.BindView;

public class SuperPlayerActivity extends CommunalActivity implements ISuperPlayView {


    SuperPayPersenter superPayPersenter;

    @BindView(R.id.view_super_player)
    SuperPlayer viewSuperPlayer;
    @BindView(R.id.layout)
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_super_play);
        superPayPersenter = new SuperPayPersenter();
        superPayPersenter.attach(this);
//        mainPersenter.initViewPager(viewpagerMain, getSupportFragmentManager());
    }


    @Override
    public void playTuijain() {

    }
}

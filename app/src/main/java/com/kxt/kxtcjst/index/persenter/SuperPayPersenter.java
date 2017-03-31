package com.kxt.kxtcjst.index.persenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalPresenter;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.PopupWindowUtils;
import com.kxt.kxtcjst.common.utils.ViewFindUtils;
import com.kxt.kxtcjst.index.DetailsActivity;
import com.kxt.kxtcjst.index.adapter.MainPagerAdapter;
import com.kxt.kxtcjst.index.entity.TabEntity;
import com.kxt.kxtcjst.index.fragment.VideoDataFragment;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;
import com.kxt.kxtcjst.index.model.IVideoPlayModelImp;
import com.kxt.kxtcjst.index.view.IMainView;
import com.kxt.kxtcjst.index.view.ISuperPlayView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/30.
 */

public class SuperPayPersenter extends CommunalPresenter<ISuperPlayView>  implements View.OnClickListener  {
    IVideoPlayModelImp   iVideoPlayModelImp;
    public SuperPayPersenter(){
        iVideoPlayModelImp=new IVideoPlayModelImp(this);

    }





    public void showUpdatePop(RelativeLayout filterIcon) {


    }

    @Override
    public void onClick(View view) {
      /*  switch (view.getId()) {
            case R.id.update_close:
            case R.id.ad_close:
        }*/
    }

}

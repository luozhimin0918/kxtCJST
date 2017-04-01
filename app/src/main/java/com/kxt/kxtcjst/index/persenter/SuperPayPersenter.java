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

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalPresenter;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.common.utils.PopupWindowUtils;
import com.kxt.kxtcjst.common.utils.ViewFindUtils;
import com.kxt.kxtcjst.index.DetailsActivity;
import com.kxt.kxtcjst.index.adapter.MainPagerAdapter;
import com.kxt.kxtcjst.index.adapter.VideoAdapter;
import com.kxt.kxtcjst.index.entity.TabEntity;
import com.kxt.kxtcjst.index.fragment.VideoDataFragment;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.ConfigJson;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.jsonBean.VideoDetails;
import com.kxt.kxtcjst.index.model.IVideoPlayModelImp;
import com.kxt.kxtcjst.index.view.IMainView;
import com.kxt.kxtcjst.index.view.ISuperPlayView;
import com.library.util.volley.VolleyHttpUtil2;
import com.library.util.volley.load.PageLoadLayout;
import com.library.widget.handmark.PullToRefreshListView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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





    /**
     * 获取个别视频的数据
     *
     */
    public void getPlayIdVideoData( final PageLoadLayout pageLoad, String  playId) {
        if (!BaseUtils.isNetworkConnected(getContext())) {
            pageLoad.loadError("亲，网络出错了！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        String url = UrlConstant.VIDEO_DATA_URL_ONE+"?vid="+playId;
        ConfigJson dataJson = new ConfigJson();
        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(dataJson)));
            iVideoPlayModelImp.getVideoDateListData(new ObserverData<VideoDetails>() {
                @Override
                public void onCallback(final VideoDetails data) {
                    super.onCallback(data);
                    if (null != data) {
                        if (data.getCode()==200) {
                            if (null != data.getData() && data.getData().getList().size() > 0) {
                                //数据获取成功

                                KLog.json(JSON.toJSONString(data));
                               /* dataListview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        lastData = (ArrayList<VedioBean.DataBean>) data.getData();
                                        pageLoad.loadSuccess();
                                        videoAdapter = new VideoAdapter(getContext(), lastData);
                                        dataListview.setAdapter(videoAdapter);
                                        videoAdapter.notifyDataSetChanged();
                                    }
                                });*/
                            } else {
                                pageLoad.loadNoData(getContext().getResources().getString(R.string.no_video_data));
//                                CjrlApplication.getInstance().setAddValues(null);
                            }

                        } else {
//                            CjrlApplication.getInstance().setAddValues(null);
                            pageLoad.loadError(getContext().getResources().getString(R.string.load_err));
                        }
                    }
                }

                @Override
                public void onError(final String error) {
                    super.onError(error);
//                    CjrlApplication.getInstance().setAddValues(null);
                    if (error.equals(VolleyHttpUtil2.ERROR_NOT_NETWORK)) {
                        pageLoad.loadError(getContext().getResources().getString(R.string.load_nonet));
                    } else {
                        pageLoad.loadError(getContext().getResources().getString(R.string.load_err));

                    }
                }
            }, map, url);
        } catch (Exception e) {
            e.printStackTrace();
            pageLoad.loadError(getContext().getResources().getString(R.string.load_err));
        }
    }


    @Override
    public void onClick(View view) {
      /*  switch (view.getId()) {
            case R.id.update_close:
            case R.id.ad_close:
        }*/
    }

}

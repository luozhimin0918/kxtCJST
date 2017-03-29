package com.kxt.kxtcjst.index.persenter;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.common.base.CommunalPresenter;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.ConfigJson;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;
import com.kxt.kxtcjst.index.model.IWelcomeModelImp;
import com.kxt.kxtcjst.index.view.IWelcomeView;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class WelcomePersenter extends CommunalPresenter<IWelcomeView> {

    private IWelcomeModelImp welcomeModelImp;

    public WelcomePersenter() {
        welcomeModelImp = new IWelcomeModelImp(this);
    }


    /*public void getWscUrl() {
        Map<String, String> map = new HashMap();
        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(new ConfigJson())));
            welcomeModelImp.getWscUrl(new ObserverData<String>() {
                @Override
                public void onCallback(String data) {
                    super.onCallback(data);
                    if (null != data && !"".equals(data)) {
                        SpConstant.getWscPreferences().edit().putString(SpConstant.WEBSOCKET_URL, data).commit();
                    }

                }

                @Override
                public void onError(String error) {
                    super.onError(error);
                }
            }, map, UrlConstant.WEBSOCKET_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getUpdateMsg();

    }
*/
    /**
     * 获取版本更新信息
     */
    public void getUpdateMsg() {
        Map<String, String> map = new HashMap();
        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(new ConfigJson())));
            welcomeModelImp.getUpdateMsg(new ObserverData<UpdateBean>() {
                @Override
                public void onCallback(UpdateBean data) {
                    super.onCallback(data);
                    if (null != data && data.getStatus().equals("1")) {
                        if (!"".equals(data.getData().getDownloadUrl())) {
                            //显示更新
                            CjstApplicaion.getInstance().setUpdateBean(data);
                        }
                    }
                }

                @Override
                public void onError(String error) {
                    super.onError(error);
                }
            }, map, UrlConstant.UPDATE_MSG_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取广告信息
     */
    public void getAdConfig() {
        Map<String, String> map = new HashMap();
        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(new ConfigJson())));
            welcomeModelImp.getAdConfig(new ObserverData<AdConfigBean>() {
                @Override
                public void onCallback(AdConfigBean data) {
                    super.onCallback(data);
                    KLog.json(JSON.toJSONString(data));
                    if (null != data && data.getStatus().equals("1")) {
                        if (data.getData().getAdvertisement().getType().equals("normal")) {
                            mView.showAd(data);
                            KLog.d("mView.showAd");
                        } else {
                            mView.toMainActivity();
                            KLog.d("mView.toMainActivity");
                        }
                        CjstApplicaion.getInstance().setAdConfigBean(data);
                    } else {
                        KLog.d("mView.toMainActivity   no  1");
                        mView.toMainActivity();
                    }
                }

                @Override
                public void onError(String error) {
                    super.onError(error);
                    mView.toMainActivity();
                }
            }, map, UrlConstant.AD_CONFIG_URL);
        } catch (Exception e) {
            e.printStackTrace();
            mView.toMainActivity();
        }
        getUpdateMsg();
//        getWscUrl();
    }
}

package com.kxt.kxtcjst.index.model;

import com.android.volley.RequestQueue;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;
import com.kxt.kxtcjst.index.persenter.WelcomePersenter;
import com.library.util.volley.VolleyHttpListener;
import com.library.util.volley.VolleyHttpUtil2;

import java.util.LinkedHashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;


/**
 * Created by Administrator on 2017/3/8 0008.
 */

public class IWelcomeModelImp implements IWelcomeModel {

    private WelcomePersenter persenter;

    public IWelcomeModelImp(WelcomePersenter persenter) {
        this.persenter = persenter;
    }

    @Override
    public void getWscUrl(final ObserverData<String> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = persenter.mView.getRequestQueue();
        VolleyHttpUtil2 request = new VolleyHttpUtil2(persenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {
                    Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                    String value = (String) claims.get("data");
                    observerData.onCallback(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                super.onError(error);
                observerData.onError(error);
            }
        });

    }

    @Override
    public void getUpdateMsg(final ObserverData<UpdateBean> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = persenter.mView.getRequestQueue();
        VolleyHttpUtil2 request = new VolleyHttpUtil2(persenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {
                    UpdateBean updateBean = new UpdateBean();
                    UpdateBean.DataBean updateData = new UpdateBean.DataBean();
                    Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                    updateBean.setMsg(claims.get("msg").toString());
                    updateBean.setAud(claims.get("aud").toString());
                    updateBean.setStatus(claims.get("status").toString());
                    LinkedHashMap<String, Object> dataClaims = (LinkedHashMap<String, Object>) claims.get("data");
                    updateData.setContent(dataClaims.get("content").toString());
                    updateData.setSize(dataClaims.get("size").toString());
                    updateData.setVersion(dataClaims.get("version").toString());
                    updateData.setDownloadUrl(dataClaims.get("downloadUrl").toString());
                    updateBean.setData(updateData);
                    if (claims.get("status").toString().equals("1")) {
                        CjstApplicaion.getInstance().setUpdateBean(updateBean);

                    }
                    observerData.onCallback(updateBean);


                } catch (Exception e) {
                    e.printStackTrace();
                    observerData.onError(e.getMessage());
                }
            }

            @Override
            public void onError(String error) {
                super.onError(error);
                observerData.onError(error);
            }
        });
    }

    @Override
    public void getAdConfig(final ObserverData<AdConfigBean> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = persenter.mView.getRequestQueue();
        VolleyHttpUtil2 request = new VolleyHttpUtil2(persenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {
                    AdConfigBean adConfigBean = new AdConfigBean();
                    AdConfigBean.DataBean adDataBean = new AdConfigBean.DataBean();
                    AdConfigBean.DataBean.AdvertisementBean advertisementBean = new AdConfigBean.DataBean.AdvertisementBean();
                    AdConfigBean.DataBean.StartPage startPage = new AdConfigBean.DataBean.StartPage();

                    Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                    adConfigBean.setStatus(claims.get("status").toString());
                    adConfigBean.setAud(claims.get("aud").toString());
                    adConfigBean.setMsg(claims.get("msg").toString());
                    Map<String, Object> dataClaims = (Map<String, Object>) claims.get("data");
                    Map<String, Object> adClaims = (Map<String, Object>) dataClaims.get("advertisement");
                    advertisementBean.setImageUrl(adClaims.get("imageUrl").toString());
                    advertisementBean.setTitle(adClaims.get("title").toString());
                    advertisementBean.setType(adClaims.get("type").toString());
                    advertisementBean.setUrl(adClaims.get("url").toString());
                    adDataBean.setAdvertisement(advertisementBean);

                    Map<String, Object> stClaims = (Map<String, Object>) dataClaims.get("startPage");
                    startPage.setImageUrl(stClaims.get("imageUrl").toString());
                    startPage.setTitle(stClaims.get("title").toString());
                    startPage.setType(stClaims.get("type").toString());
                    startPage.setUrl(stClaims.get("url").toString());
                    adDataBean.setStartPage(startPage);
                    adConfigBean.setData(adDataBean);
                    if (claims.get("status").toString().equals("1")) {
                        CjstApplicaion.getInstance().setAdConfigBean(adConfigBean);
                    }
                    observerData.onCallback(adConfigBean);

                } catch (Exception e) {
                    e.printStackTrace();
                    observerData.onError(e.getMessage());
                }
            }

            @Override
            public void onError(String error) {
                super.onError(error);
                observerData.onError(error);
            }
        });
    }
}

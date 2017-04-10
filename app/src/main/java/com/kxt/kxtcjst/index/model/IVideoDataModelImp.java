package com.kxt.kxtcjst.index.model;


import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.persenter.VideoDataPersenter;
import com.library.util.volley.VolleyHttpListener;
import com.library.util.volley.VolleyHttpUtil2;

import java.util.ArrayList;
import java.util.Map;

import io.jsonwebtoken.Claims;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class IVideoDataModelImp implements IVideoDataModel {

    private VideoDataPersenter dataPersenter;

    public IVideoDataModelImp(VideoDataPersenter dataPersenter) {
        this.dataPersenter = dataPersenter;
    }


    @Override
    public void getDateListData(final ObserverData<VedioBean> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = dataPersenter.mView.getRequestQueue();
        final VolleyHttpUtil2 request = new VolleyHttpUtil2(dataPersenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {

                 Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);

                    ArrayList arrayList = (ArrayList) claims.get("data");
                    ArrayList<VedioBean.DataBean> dataBeens = new ArrayList<VedioBean.DataBean>();
                    VedioBean cjrlDataBean = new VedioBean();
                    cjrlDataBean.setMsg(claims.get("msg").toString());
                    cjrlDataBean.setStatus(Integer.parseInt(claims.get("status").toString()));
                    cjrlDataBean.setAud(claims.get("aud").toString());
                    for (int i = 0; i < arrayList.size(); i++) {
                        Map<String, String> map1 = (Map<String, String>) arrayList.get(i);
                        VedioBean.DataBean dataBean = new VedioBean.DataBean();
                        dataBean.setCategory_id(map1.get("category_id"));
                        dataBean.setId(map1.get("id"));
                        dataBean.setTitle(map1.get("title"));
                        dataBean.setIstoutiao(map1.get("istoutiao"));
                        dataBean.setPicture(map1.get("picture"));
                        dataBean.setCate_name(map1.get("cate_name"));
                        dataBean.setPlay_count(map1.get("play_count"));
                        dataBean.setPublish_time(map1.get("publish_time"));
                        dataBean.setUrl(map1.get("url"));
                        dataBeens.add(dataBean);
                    }
                    cjrlDataBean.setData(dataBeens);

                    observerData.onCallback(cjrlDataBean);


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

package com.kxt.kxtcjst.index.model;


import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.persenter.SuperPayPersenter;
import com.kxt.kxtcjst.index.persenter.VideoDataPersenter;
import com.library.util.volley.VolleyHttpListener;
import com.library.util.volley.VolleyHttpUtil2;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class IVideoPlayModelImp implements IVideoPlayModel {

    private SuperPayPersenter superPayPersenter;

    public IVideoPlayModelImp(SuperPayPersenter superPayPersenter) {
        this.superPayPersenter = superPayPersenter;
    }

    @Override
    public void getVideoDateListData( final ObserverData<VedioBean> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = superPayPersenter.mView.getRequestQueue();
        VolleyHttpUtil2 request = new VolleyHttpUtil2(superPayPersenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {
                   /*    Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                    ArrayList arrayList = (ArrayList) claims.get("data");
                    ArrayList<VedioBean.DataBean> dataBeens = new ArrayList<VedioBean.DataBean>();
                    VedioBean cjrlDataBean = new VedioBean();
                    cjrlDataBean.setMsg(claims.get("msg").toString());
                    cjrlDataBean.setCode(Integer.parseInt(claims.get("code").toString()));
//                    cjrlDataBean.setAud(claims.get("aud").toString());
                    for (int i = 0; i < arrayList.size(); i++) {
                        Map<String, String> map1 = (Map<String, String>) arrayList.get(i);
                        VedioBean.DataBean dataBean = new VedioBean.DataBean();
                        dataBean.setCategory_id(map1.get("category_id"));
                        dataBean.setId(map1.get("id"));
                        dataBean.setTitle(map1.get("title"));
                        dataBean.setIstoutiao(map1.get("istoutiao"));
                        dataBean.setPicture(map1.get("picture"));
                        dataBean.setPlay_count(map1.get("play_count"));
                        dataBean.setPublish_time(map1.get("publish_time"));
                        dataBean.setUrl(map1.get("url"));
                        dataBeens.add(dataBean);
                    }
                    cjrlDataBean.setData(dataBeens);*/

                    observerData.onCallback(JSON.parseObject(result,VedioBean.class));


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

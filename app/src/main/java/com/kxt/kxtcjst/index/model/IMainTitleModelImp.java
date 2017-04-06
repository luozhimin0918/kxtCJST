package com.kxt.kxtcjst.index.model;


import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.jsonBean.VedioTitleBean;
import com.kxt.kxtcjst.index.persenter.MainPersenter;
import com.library.util.volley.VolleyHttpListener;
import com.library.util.volley.VolleyHttpUtil2;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Map;

import io.jsonwebtoken.Claims;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class IMainTitleModelImp implements IMainTitleModel {

    private MainPersenter mainPersenter;

    public IMainTitleModelImp(MainPersenter mainPersenter) {
        this.mainPersenter = mainPersenter;
    }




    @Override
    public void getSpTitle(final ObserverData<VedioTitleBean> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = mainPersenter.mView.getRequestQueue();
        final VolleyHttpUtil2 request = new VolleyHttpUtil2(mainPersenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {




                   Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                    ArrayList arrayList = (ArrayList) claims.get("data");
                    ArrayList<VedioTitleBean.DataBean> dataBeens = new ArrayList<VedioTitleBean.DataBean>();
                    VedioTitleBean vedioTitleBean = new VedioTitleBean();
                    vedioTitleBean.setMsg(claims.get("msg").toString());
                    vedioTitleBean.setStatus(Integer.parseInt(claims.get("status").toString()));
                    vedioTitleBean.setAud(claims.get("aud").toString());
                    for (int i = 0; i < arrayList.size(); i++) {
                        Map<String, String> map1 = (Map<String, String>) arrayList.get(i);
                        VedioTitleBean.DataBean dataBean = new VedioTitleBean.DataBean();
                        dataBean.setCat_name(map1.get("cat_name"));
                        dataBean.setId(map1.get("id"));
                        dataBeens.add(dataBean);
                    }
                    vedioTitleBean.setData(dataBeens);
                    observerData.onCallback(vedioTitleBean);


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

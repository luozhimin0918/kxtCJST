package com.kxt.kxtcjst.index.model;


import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VideoDetails;
import com.kxt.kxtcjst.index.persenter.SuperPayPersenter;
import com.library.util.volley.VolleyHttpListener;
import com.library.util.volley.VolleyHttpUtil2;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class IVideoPlayModelImp implements IVideoPlayModel {

    private SuperPayPersenter superPayPersenter;

    public IVideoPlayModelImp(SuperPayPersenter superPayPersenter) {
        this.superPayPersenter = superPayPersenter;
    }

    @Override
    public void getVideoDateListData(final ObserverData<VideoDetails> observerData, Map<String, String> map, String url) {
        RequestQueue requestQueue = superPayPersenter.mView.getRequestQueue();
        VolleyHttpUtil2 request = new VolleyHttpUtil2(superPayPersenter.getContext(), requestQueue);
        request.doGet(url, map, new VolleyHttpListener() {
            @Override
            public void onSuccess(String result) {
                try {
                      Claims claims = BaseUtils.parseJWT(result, UrlConstant.URL_PRIVATE_KEY);
                   Object object =  claims.get("data");
                    Map<String,Object> dataBeanObj= (Map<String, Object>) object;


                    VideoDetails  videoDetails =new VideoDetails();
                    videoDetails.setStatus(Integer.parseInt(claims.get("status").toString()));
                    videoDetails.setMsg(claims.get("msg").toString());


                    VideoDetails.DataBean  dataBean=new VideoDetails.DataBean();


                   Object  objectInfo = dataBeanObj.get("info");
                    Map<String,Object> objInfoMap= (Map<String, Object>) objectInfo;
                    VideoDetails.DataBean.InfoBean  infoBean =new VideoDetails.DataBean.InfoBean();
                    infoBean.setId(objInfoMap.get("id").toString());
                    infoBean.setTitle(objInfoMap.get("title").toString());
                    infoBean.setPicture(objInfoMap.get("picture").toString());
                    infoBean.setDescription(objInfoMap.get("description").toString());
                    infoBean.setUrl(objInfoMap.get("url").toString());
                    infoBean.setPublish_time(objInfoMap.get("publish_time").toString());
                    infoBean.setPlay_count(objInfoMap.get("play_count").toString());
                    infoBean.setShare_url(objInfoMap.get("share_url").toString());
                    infoBean.setIs_collect(objInfoMap.get("is_collect").toString());
                    dataBean.setInfo(infoBean);
                    //list
                    ArrayList arrayList = (ArrayList) dataBeanObj.get("list");
                    List<VideoDetails.DataBean.ListBean> listBeanList=new ArrayList<VideoDetails.DataBean.ListBean>();
                    for (int i = 0; i < arrayList.size(); i++) {
                        Map<String, String> map1 = (Map<String, String>) arrayList.get(i);
                        VideoDetails.DataBean.ListBean listBean=new VideoDetails.DataBean.ListBean();
                        listBean.setId(map1.get("id").toString());
                        listBean.setTitle(map1.get("title").toString());
                        listBean.setPicture(map1.get("picture").toString());
                        listBean.setDescription(map1.get("description").toString());
                        listBean.setUrl(map1.get("url").toString());
                        listBean.setPublish_time(map1.get("publish_time").toString());
                        listBean.setPlay_count(map1.get("play_count").toString());
                        listBean.setCategory_id(map1.get("category_id").toString());
                        listBeanList.add(listBean);

                    }
                    dataBean.setList(listBeanList);
                    videoDetails.setData(dataBean);


                    observerData.onCallback(videoDetails);


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

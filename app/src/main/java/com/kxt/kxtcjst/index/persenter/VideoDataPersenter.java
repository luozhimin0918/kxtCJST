package com.kxt.kxtcjst.index.persenter;


import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalPresenter;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.adapter.VideoAdapter;
import com.kxt.kxtcjst.index.jsonBean.ConfigListVideo;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.model.IVideoDataModelImp;
import com.kxt.kxtcjst.index.view.IVideoDataView;
import com.library.util.volley.VolleyHttpUtil2;
import com.library.util.volley.load.PageLoadLayout;
import com.library.widget.handmark.PullToRefreshListView;
import com.library.widget.window.ToastView;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class VideoDataPersenter extends CommunalPresenter<IVideoDataView> {

    private IVideoDataModelImp dataModelImp;

    private VideoAdapter videoAdapter;

    private ArrayList<VedioBean.DataBean> lastData;
//    private ArrayList<CjrlDataBean.DataBean> tempData = new ArrayList<>();

    public VideoDataPersenter() {
        dataModelImp = new IVideoDataModelImp(this);
    }



    /**
     * 获取视频的数据
     *
     */
    public void getVideoData(final PullToRefreshListView dataListview, final PageLoadLayout pageLoad,String  tagId) {
        if (!BaseUtils.isNetworkConnected(getContext())) {
            dataListview.onRefreshComplete();
            pageLoad.loadError("亲，网络出错了！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        String url = UrlConstant.VIDEO_DATA_URL_ITEM;

        ConfigListVideo dataJson = new ConfigListVideo();
        if(tagId.equals("")){
            url=UrlConstant.VIDEO_DATA_URL_ITEM_TUIJIAN;
        }else{
            dataJson.setCid(tagId);
        }


        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(dataJson)));
            dataModelImp.getDateListData(new ObserverData<VedioBean>() {
                @Override
                public void onCallback(final VedioBean data) {
                    super.onCallback(data);
                    if (null != data) {
                        dataListview.onRefreshComplete();
                        KLog.json(JSON.toJSONString(data));
                        if (data.getStatus()==1) {
                            if (null != data.getData() && data.getData().size() > 0) {
                                //数据获取成功
                                dataListview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        lastData = (ArrayList<VedioBean.DataBean>) data.getData();
                                        pageLoad.loadSuccess();
                                        videoAdapter = new VideoAdapter(getContext(), lastData);
                                        dataListview.setAdapter(videoAdapter);
                                        videoAdapter.notifyDataSetChanged();
                                    }
                                });
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

    /**
     * 获取假期的数据
     * @param dataListview
     */
    public void getMoreNewsData( final PullToRefreshListView dataListview,String tagId) {
        if (BaseUtils.isNetworkConnected(getContext())) {
            try {
                final Map<String, String> map = new HashMap<>();
                Gson gson = new Gson();
                String url = UrlConstant.VIDEO_DATA_URL_ITEM;
                ConfigListVideo dataJson = new ConfigListVideo();
                if(tagId.equals("")){
                    url=UrlConstant.VIDEO_DATA_URL_ITEM_TUIJIAN;
                }else{
                    dataJson.setCid(tagId);
                }
                if(lastData!=null&&lastData.size()>0){

                    dataJson.setMarkid(lastData.get(lastData.size()-1).getId());
                }

                map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(dataJson)));
                dataModelImp.getDateListData(new ObserverData<VedioBean>() {
                    @Override
                    public void onCallback(VedioBean data) {
                        super.onCallback(data);
                        if (null != data && data.getStatus()==1) {
                            List<VedioBean.DataBean> databeas = data.getData();
                            KLog.json("videoDataPersenter More",JSON.toJSONString(data));
                            if(databeas!=null&&databeas.size()>0){
                                lastData.addAll(databeas);
                                videoAdapter.notifyDataSetChanged();
                            }

                            dataListview.onRefreshComplete();
                            if(databeas==null||databeas.size()==0){
                                ToastView.makeText3(getContext(), "没有更多数据了");
                            }
                        } else {
                            dataListview.onRefreshComplete();
                            ToastView.makeText3(getContext(), getContext().getResources().getString(R.string.load_err));
                        }
                    }
                }, map, url);
            } catch (Exception e) {
                e.printStackTrace();
                dataListview.onRefreshComplete();
                ToastView.makeText3(getContext(), getContext().getResources().getString(R.string.load_err));
            }
        } else {
            dataListview.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dataListview.onRefreshComplete();
                }
            }, 200);

            ToastView.makeText3(getContext(), getContext().getResources().getString(R.string.load_nonet));
        }


    }

    /**
     * 获取日历数据
     * @param dataListview
     * @param pageLoad
     * @param dateBean
     */
   /* public void getDataList(final PullToRefreshListView dataListview, final PageLoadLayout pageLoad, DateBean dateBean) {
        if (!BaseUtils.isNetworkConnected(getContext())) {
            dataListview.onRefreshComplete();
            pageLoad.loadError("亲，网络出错了！");
            return;
        }
        Map<String, String> map = new HashMap<>();
        String url = UrlConstant.GET_DATE_URL;
        String value = dateBean.getYear() + "-" + dateBean.getMonthNum() + "-" + dateBean.getDay();
        DataJson dataJson = new DataJson(value);
        Gson gson = new Gson();
        try {
            map.put("content", BaseUtils.createJWT(UrlConstant.URL_PRIVATE_KEY, gson.toJson(dataJson)));
            dataModelImp.getDateListData(new ObserverData<CjrlDataBean>() {
                @Override
                public void onCallback(final CjrlDataBean data) {
                    super.onCallback(data);
                    if (null != data) {
                        dataListview.onRefreshComplete();
                        if (data.getStatus().equals("1")) {
                            if (null != data.getData() && data.getData().size() > 0) {
                                //数据获取成功
                                dataListview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        lastData = (ArrayList<CjrlDataBean.DataBean>) data.getData();
                                        getAllState(lastData);
                                        fliterListView(dataListview, pageLoad);
                                    }
                                });
                            } else {
                                pageLoad.loadNoData(getContext().getResources().getString(R.string.no_data));
                                CjrlApplication.getInstance().setAddValues(null);
                            }

                        } else {
                            CjrlApplication.getInstance().setAddValues(null);
                            pageLoad.loadError("亲，数据加载出错了！");
                        }
                    }
                }

                @Override
                public void onError(final String error) {
                    super.onError(error);
                    CjrlApplication.getInstance().setAddValues(null);
                    if (error.equals(VolleyHttpUtil2.ERROR_NOT_NETWORK)) {
                        pageLoad.loadError("亲，网络出错了！");
                    } else {
                        pageLoad.loadError("亲，数据加载出错了！");

                    }
                }
            }, map, url);
        } catch (Exception e) {
            e.printStackTrace();
            CjrlApplication.getInstance().setAddValues(null);
            pageLoad.loadError("亲，数据加载出错了！");
        }
    }*/

    /**
     * 根据返回的数据获取所有的国家
     *
     * @param dataBeen
     */
   /* public void getAllState(ArrayList<CjrlDataBean.DataBean> dataBeen) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < dataBeen.size(); i++) {
            String state = dataBeen.get(i).getState();
            map.put(state, state);
        }

        List<String> values = new ArrayList<>();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            values.add(entry.getKey());
        }
        values.add(0, "全部");
        CjrlApplication.getInstance().setAddValues(values);
    }*/



    /**
     * 过滤数据
     */
   /* public void fliterListView(final PullToRefreshListView dataListview, PageLoadLayout pageLoad) {
        tempData.clear();
        tempData.addAll(lastData);
        Map<Integer, CjrlDataBean.DataBean> stateMap = new HashMap<>();
        Map<Integer, CjrlDataBean.DataBean> keyMap = new HashMap<>();
        Map<Integer, CjrlDataBean.DataBean> addressMap = new HashMap<>();
        SharedPreferences preferences = CjrlApplication.getInstance().getFlowCheckedSP();
        String state = preferences.getString(SpConstant.CJRL_FLOW_STATE, "");
        String key = preferences.getString(SpConstant.CJRL_FLOW_KEY, "");
        String duokong = preferences.getString(SpConstant.CJRL_FLOW_DUOKONG, "");
        String address = preferences.getString(SpConstant.CJRL_FLOW_ADDRESS, "");

        if (null != key && !"".equals(key)) {
            if ((key.contains("高") && key.contains("中") && key.contains("低")) || key.contains("全部")) {
                tempData.clear();
                tempData.addAll(lastData);
            } else {

                if (key.contains("高") && key.contains("中")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("高") && !tempData.get(i).getNature().equals("中")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                } else if (key.contains("高") && key.contains("低")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("高") && !tempData.get(i).getNature().equals("低")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                } else if (key.contains("中") && key.contains("低")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("中") && !tempData.get(i).getNature().equals("低")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                } else if (key.equals("高,")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("高")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                } else if (key.equals("中,")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("中")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                } else if (key.equals("低,")) {
                    for (int i = 0; i < tempData.size(); i++) {
                        if (!tempData.get(i).getNature().equals("低")) {
                            keyMap.put(i, tempData.get(i));
                        }
                    }
                }
            }
        } else {
            tempData.clear();
            tempData.addAll(lastData);
        }
        if (null != state && !"".equals(state)) {
            if ((state.contains("已公布") && state.contains("未公布")) || state.contains("全部")) {
                tempData.clear();
                tempData.addAll(lastData);
            } else if (state.contains("已公布")) {
                for (int i = 0; i < tempData.size(); i++) {
                    if (tempData.get(i).getReality().contains("--")) {
                        stateMap.put(i, tempData.get(i));
                    }
                }
            } else if (state.contains("未公布")) {
                for (int i = 0; i < tempData.size(); i++) {
                    if (!tempData.get(i).getReality().contains("--")) {
                        stateMap.put(i, tempData.get(i));
                    }
                }
            }

        } else {
            tempData.clear();
            tempData.addAll(lastData);
        }


        if (null != duokong && !"".equals(duokong)) {
            if (duokong.contains("全部") || (duokong.contains("金银") && duokong.contains("石油") && duokong.contains("外汇"))) {
                tempData.clear();
                tempData.addAll(lastData);
                for (int i = 0; i < tempData.size(); i++) {
                    tempData.get(i).setShowJy(true);
                    tempData.get(i).setShowSy(true);
                    tempData.get(i).setShowWh(true);
                }
            } else {
                for (int i = 0; i < tempData.size(); i++) {
                    if (duokong.contains("金银")) {
                        tempData.get(i).setShowJy(true);
                    } else {
                        tempData.get(i).setShowJy(false);
                    }
                    if (duokong.contains("石油")) {
                        tempData.get(i).setShowSy(true);
                    } else {
                        tempData.get(i).setShowSy(false);
                    }
                    if (duokong.contains("外汇")) {
                        tempData.get(i).setShowWh(true);
                    } else {
                        tempData.get(i).setShowWh(false);
                    }
                }
            }

        } else {
            for (int i = 0; i < tempData.size(); i++) {
                tempData.get(i).setShowJy(true);
                tempData.get(i).setShowSy(true);
                tempData.get(i).setShowWh(true);
            }
        }
        if (null != address && !"".equals(address)) {
            if (address.contains("全部")) {
                tempData.clear();
                tempData.addAll(lastData);
            } else {
                for (int i = 0; i < tempData.size(); i++) {
                    if (!address.contains(tempData.get(i).getState())) {
                        addressMap.put(i, tempData.get(i));

                    }
                }
            }
        } else {
            tempData.clear();
            tempData.addAll(lastData);
        }
        for (Map.Entry<Integer, CjrlDataBean.DataBean> entry : stateMap.entrySet()) {
            tempData.remove(entry.getValue());
        }
        for (Map.Entry<Integer, CjrlDataBean.DataBean> entry : keyMap.entrySet()) {
            tempData.remove(entry.getValue());
        }
        for (Map.Entry<Integer, CjrlDataBean.DataBean> entry : addressMap.entrySet()) {
            tempData.remove(entry.getValue());
        }

        if (null != tempData && tempData.size() > 0) {
            pageLoad.loadSuccess();
            adapter = new DataListAdapter(getContext(), tempData);
            dataListview.setAdapter(adapter);
            *//**
             * lsitview滑动到当前时间item
             *//*
//            if (!isPullDown) {
//                for (int i = 0; i < tempData.size(); i++) {
//                    if (tempData.get(i).getReality().contains("--")) {
//                        final int finalI = i;
//                        this.dataListview = dataListview;
//                        handler.sendEmptyMessage(finalI);
//                        break;
//
//                    }
//                }
//            }

            adapter.notifyDataSetChanged();
            pageLoad.loadSuccess();
        } else {
            pageLoad.loadNoData(getContext().getResources().getString(R.string.fliter_nodata));
        }

    }*/


}

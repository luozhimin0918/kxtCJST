package com.kxt.kxtcjst.index.model;

import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public interface IWelcomeModel {
    void getWscUrl(ObserverData<String> observerData, Map<String, String> map, String url);

    void getUpdateMsg(ObserverData<UpdateBean> observerData, Map<String, String> map, String url);

    void getAdConfig(ObserverData<AdConfigBean> observerData, Map<String, String> map, String url);

}

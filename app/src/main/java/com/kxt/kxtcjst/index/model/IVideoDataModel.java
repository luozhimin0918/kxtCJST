package com.kxt.kxtcjst.index.model;


import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;

import java.util.Map;


/**
 * Created by Administrator on 2017/2/14 0014.
 */

public interface IVideoDataModel {

    void getDateListData(ObserverData<VedioBean> observerData, Map<String, String> map, String url);

}

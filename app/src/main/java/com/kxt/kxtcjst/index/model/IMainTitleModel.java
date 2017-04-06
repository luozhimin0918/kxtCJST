package com.kxt.kxtcjst.index.model;


import com.kxt.kxtcjst.common.utils.ObserverData;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.jsonBean.VedioTitleBean;

import java.util.Map;


/**
 * Created by Administrator on 2017/2/14 0014.
 */

public interface IMainTitleModel {

    void getSpTitle(ObserverData<VedioTitleBean> observerData, Map<String, String> map, String url);

}

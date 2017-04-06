package com.kxt.kxtcjst.index.view;

import com.kxt.kxtcjst.common.base.CommunalView;
import com.kxt.kxtcjst.index.jsonBean.VedioTitleBean;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface IMainView extends CommunalView {
    void ShowAdPopView();
    void initTabView(VedioTitleBean vedioTitleBean);
}

package com.kxt.kxtcjst.index.view;


import com.kxt.kxtcjst.common.base.CommunalView;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;

/**
 * Created by Administrator on 2017/3/8 0008.
 */

public interface IWelcomeView extends CommunalView {

    void toMainActivity();

    void showAd(AdConfigBean adConfigBean);
}

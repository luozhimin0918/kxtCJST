package com.kxt.kxtcjst;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.kxt.kxtcjst.common.utils.CrashHandler;
import com.kxt.kxtcjst.index.jsonBean.AdConfigBean;
import com.kxt.kxtcjst.index.jsonBean.UpdateBean;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CjstApplicaion extends Application {
    private static CjstApplicaion cjstApplicaion;
    private UpdateBean updateBean;
    private AdConfigBean adConfigBean;

    public AdConfigBean getAdConfigBean() {
        return adConfigBean;
    }

    public void setAdConfigBean(AdConfigBean adConfigBean) {
        this.adConfigBean = adConfigBean;
    }

    public UpdateBean getUpdateBean() {
        return updateBean;
    }

    public void setUpdateBean(UpdateBean updateBean) {
        this.updateBean = updateBean;
    }


    public static CjstApplicaion getInstance() {
        return cjstApplicaion;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.cjstApplicaion = this;
        Glide.get(getApplicationContext()).setMemoryCategory(MemoryCategory.LOW);
        CrashHandler crashHandler = new CrashHandler();
        crashHandler.init(getApplicationContext());
    }

}

package com.kxt.kxtcjst.index.jsonBean;



import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class ConfigListVideo implements Serializable {
    private String version;
    private String system;
    private String num;
    private String markid;
    private String cid;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMarkid() {
        return markid;
    }

    public void setMarkid(String markid) {
        this.markid = markid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public ConfigListVideo() {
        this.setVersion();
        this.setSystem();
        this.setNum("10");
    }

    public String getSystem() {
        return this.system;
    }

    public void setSystem() {
        this.system = UrlConstant.SYSTEM_VALUE;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion() {
        String verison = BaseUtils.getVersionName(CjstApplicaion.getInstance());
        this.version = verison;
    }

    @Override
    public String toString() {
        return "DataJson{" +
                "version='" + version + '\'' +
                ", system='" + system + '\'' +
                '}';
    }
}

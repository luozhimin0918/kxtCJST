package com.kxt.kxtcjst.index.jsonBean;



import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.common.constant.UrlConstant;
import com.kxt.kxtcjst.common.utils.BaseUtils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/28 0028.
 */

public class ConfigOneVideoJson implements Serializable {
    private String version;
    private String system;
    private String vid;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public ConfigOneVideoJson() {
        this.setVersion();
        this.setSystem();
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

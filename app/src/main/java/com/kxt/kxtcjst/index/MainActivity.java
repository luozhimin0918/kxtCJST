package com.kxt.kxtcjst.index;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.kxt.kxtcjst.CjstApplicaion;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.index.persenter.MainPersenter;
import com.kxt.kxtcjst.index.view.IMainView;
import com.socks.library.KLog;

import butterknife.BindView;

public class MainActivity extends CommunalActivity  implements IMainView{
    private MainPersenter mainPersenter;
    private boolean isShow = false;
    @BindView(R.id.filter_img)
    RelativeLayout filterIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_main);
        mainPersenter=new MainPersenter();
        mainPersenter.attach(this);
    }

    @Override
    public void ShowAdPopView() {
        if (CjstApplicaion.getInstance().getAdConfigBean() != null) {
            mainPersenter.showAdPop(filterIcon);
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
// TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (!isShow) {
                if (BaseUtils.isNetworkConnected(this)) {
                    if (CjstApplicaion.getInstance().getUpdateBean() != null) {
                        KLog.json(JSON.toJSONString(CjstApplicaion.getInstance().getUpdateBean()));
                        mainPersenter.showUpdatePop(filterIcon);
                    } else if (CjstApplicaion.getInstance().getAdConfigBean() != null) {
                        KLog.json(JSON.toJSONString(CjstApplicaion.getInstance().getAdConfigBean()));
                        mainPersenter.showAdPop(filterIcon);
                    }
                    isShow = true;

                }

            }


        }
    }
}

package com.kxt.kxtcjst.index.fragment;


import android.os.Bundle;
import android.widget.ListView;


import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalFragment;
import com.kxt.kxtcjst.index.persenter.VideoDataPersenter;
import com.kxt.kxtcjst.index.view.IVideoDataView;
import com.library.util.volley.load.PageLoadLayout;
import com.library.widget.handmark.PullToRefreshBase;
import com.library.widget.handmark.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class VideoDataFragment extends CommunalFragment implements IVideoDataView, PageLoadLayout.OnAfreshLoadListener {

//    private static CjrlDataFragment cjrlDataFragment = new CjrlDataFragment();


    PullToRefreshListView dataListview;
    PageLoadLayout pageLoad;

//    private DateBean dateBean;

//    public static CjrlDataFragment getInstance() {
//        return cjrlDataFragment;
//
//    }

    private VideoDataPersenter videoDataPersenter;

    @Override
    protected void onInitialize(Bundle savedInstanceState) {
        setBindingView(R.layout.video_data_fragment);

        super.onInitialize(savedInstanceState);

        dataListview = (PullToRefreshListView) replaceLayout.findViewById(R.id.data_listview);
        pageLoad = (PageLoadLayout) replaceLayout.findViewById(R.id.page_load);
        pageLoad.setOnAfreshLoadListener(this);
       /* if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }*/
        videoDataPersenter = new VideoDataPersenter();
        videoDataPersenter.attach(this);
        init();
    }


    public void init() {
        dataListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        dataListview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //加上延迟效果,取消收藏之后回来,避免头部加载一闪而过
                dataListview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        videoDataPersenter.getVideoData(dataListview, pageLoad);
                    }
                }, 200);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });
    }


    @Override
    public void OnAfreshLoad() {
        pageLoad.startLoading();//TODO: 请求失败之后要显示圈圈了
//        cjrlDataPersenter.getDataList(dataListview, pageLoad, dateBean);
    }

/*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventType event) {
        if (event == EventType.POST_FEFRESH_LIST) {
            if (null != event.getObj()) {
                this.dateBean = (DateBean) event.getObj();
                if (dateBean.getLastTab() == 0) {
                    cjrlDataPersenter.getDataList(dataListview, pageLoad, dateBean);
                }
            }

        }
        if (event == EventType.POST_FEFRESH_FLITER) {
            int lastTab = (int) event.getObj();
            if (lastTab == 0) {
                cjrlDataPersenter.getDataList(dataListview, pageLoad, dateBean);
            }
        }
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();

      /*  EventBus.getDefault().unregister(this);*/
    }


}

package com.kxt.kxtcjst.index;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.base.CommunalActivity;
import com.kxt.kxtcjst.common.utils.EventType;
import com.kxt.kxtcjst.index.jsonBean.VideoDetails;
import com.kxt.kxtcjst.index.persenter.SuperPayPersenter;
import com.kxt.kxtcjst.index.view.ISuperPlayView;
import com.library.util.volley.load.PageLoadLayout;
import com.library.widget.handmark.PullToRefreshListView;
import com.superplayer.library.SuperPlayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class SuperPlayerActivity extends CommunalActivity implements ISuperPlayView, SuperPlayer.OnNetChangeListener, PageLoadLayout.OnAfreshLoadListener  {


    SuperPayPersenter superPayPersenter;

    @BindView(R.id.view_super_player)
    SuperPlayer player;
    @BindView(R.id.layout)
    LinearLayout layout;

    String playId;
    @BindView(R.id.page_load)
    PageLoadLayout pageLoad;
    @BindView(R.id.data_listview)
    PullToRefreshListView dataListview;
    @BindView(R.id.sp_title_text)
    TextView spTitleText;
    @BindView(R.id.sp_showtime)
    TextView spShowtime;
    @BindView(R.id.back_but_play)
    ImageView backButPlay;

    private VideoDetails videoDetails;
    private String vedioUrl;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(R.layout.activity_super_play, false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        superPayPersenter = new SuperPayPersenter();
        superPayPersenter.attach(this);
        EventBus.getDefault().register(this);
        // 获取意图中的数据
        Intent intent = getIntent();
        playId = intent.getStringExtra("id");
        backButPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pageLoad.setOnAfreshLoadListener(this);
        superPayPersenter.getPlayIdVideoData(pageLoad, playId);
//        mainPersenter.initViewPager(viewpagerMain, getSupportFragmentManager());
    }

    /**
     * 当横竖屏切换时会调用该方法
     *
     * @author
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                layout.setVisibility(View.GONE);
//                backButPlay.setVisibility(View.GONE);
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                layout.setVisibility(View.VISIBLE);
//                backButPlay.setVisibility(View.VISIBLE);

            }
        }
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        } else {

        }
        super.onBackPressed();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }

        try {
            EventBus.getDefault().unregister(this);

        } catch (Exception e) {

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventType eventType) {
        try {

            switch (eventType) {
                case POST_USER_NAME:
                    playId = (String) eventType.getObj();
                    superPayPersenter.getPlayIdVideoData(pageLoad, playId);
                    break;
            }


        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playTuijain(VideoDetails vidDetails) {
        VideoDetails.DataBean dataBean = vidDetails.getData();
        if (dataBean.getList() != null && dataBean.getList().size() > 0) {

            superPayPersenter.putTuijian(pageLoad, dataListview, dataBean.getList());
        }

    }

    @Override
    public void playVideo(VideoDetails vidDetails, boolean isFistPlay) {
        VideoDetails.DataBean.InfoBean infoBean = vidDetails.getData().getInfo();
        title = infoBean.getTitle();
        vedioUrl = infoBean.getUrl();
        spTitleText.setText(infoBean.getTitle());
        spShowtime.setText(infoBean.getPlay_count());
        if (isFistPlay) {

            initFistVideo();
        } else {
            //代谢
        }

    }

    private void initFistVideo() {
        if (false) {
            player.setLive(true);//设置该地址是直播的地址
        }

        if (player != null) {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            player.measure(w, h);
            int height = player.getMeasuredHeight();
            int width = player.getMeasuredWidth();
            player.setLayoutParamsNorheight(height);

        }
        player.setSupportGesture(true);
        player.setShowTopControl(true);
        player.setNetChangeListener(true)//设置监听手机网络的变化
                .setOnNetChangeListener(SuperPlayerActivity.this)//实现网络变化的回调
                .onPrepared(new SuperPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared() {
                        /**
                         * 监听视频是否已经准备完成开始播放。（可以在这里处理视频封面的显示跟隐藏）
                         */
                    }
                }).onComplete(new Runnable() {
            @Override
            public void run() {
                /**
                 * 监听视频是否已经播放完成了。（可以在这里处理视频播放完成进行的操作）
                 */
                if (player != null && vedioUrl != null) {
                    player.play(vedioUrl, 1000);
                }
            }
        }).onInfo(new SuperPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                /**
                 * 监听视频的相关信息。
                 */

            }
        }).onError(new SuperPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                /**
                 * 监听视频播放失败的回调
                 */

            }
        }).setTitle(title)//设置视频的titleName
                .play(vedioUrl);//开始播放视频
        player.setShowTopControl(false).setSupportGesture(true);
        player.showCenterControl(true);
        player.setScaleType(SuperPlayer.SCALETYPE_FITXY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }

    @Override
    public void onWifi() {

    }

    @Override
    public void onMobile() {

    }

    @Override
    public void onDisConnect() {

    }

    @Override
    public void onNoAvailable() {

    }

    @Override
    public void OnAfreshLoad() {
        superPayPersenter.getPlayIdVideoData(pageLoad, playId);
    }
}

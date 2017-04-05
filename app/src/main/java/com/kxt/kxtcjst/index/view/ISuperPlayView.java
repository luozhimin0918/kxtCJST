package com.kxt.kxtcjst.index.view;

import com.kxt.kxtcjst.common.base.CommunalView;
import com.kxt.kxtcjst.index.jsonBean.VideoDetails;

/**
 * Created by Administrator on 2017/3/30.
 */

public interface ISuperPlayView extends CommunalView {
    void playTuijain(VideoDetails videoDetails);
    void playVideo(VideoDetails videoDetails,boolean  isFistPlay);
}

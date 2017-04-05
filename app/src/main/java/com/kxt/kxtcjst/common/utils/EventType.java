package com.kxt.kxtcjst.common.utils;

/**
 * @author zml
 *         自定义的 广播类型
 */
public enum EventType {
    POST_HUODONG,//新活动
    POST_HUIFU,//新回复
    POST_HUODONG_NONE,//隐藏活动的点
    POST_HUIFU_NONE,//隐藏回复的点
    POST_SEFL_HUODONG_NONE,//隐藏我的上面的红点
    POST_HUODONG_STATE,//获取我的中 红点的状态
    POST_PL_ADD,//添加新评论
    POST_PL_RETURN,//添加新回复
    POST_USER_WXLOGIN,//用户微信登陆成功
    POST_USER_CITYINFO,//用户选择城市信息
    POST_HQ_UPDATE,//行情更新数据
    POST_HQ_ADD,//行情添加数据
    POST_HQ_ADD_ZX,//行情添加数据自选
    POST_HQ_TOP_ADD,//行情头部添加数据
    POST_HQ_TOP_UPDATE,//行情头部更新数据
    POST_HQ_TOSERVICE,//
    POST_HQ_ZX_REQUEST,//重新获取行情自选的信息
    POST_HQ_ZX_CLEAR,//清空自选信息
    POST_HQ_ZX_HIDDEN,
    POST_HQ_ZX_REFRESH,//自选刷新
    POST_MAIN_GETINFO,//通知获取相关信息
    POST_PAGER_REFRESH,
    POST_USER_NAME, EventType,//向基本资料界面发送用户昵称
    POST_CLOSE_SERVER,
    POST_NETNET_PLAY_TO,//发送关闭service
    POST_ST_FL_TITLE,//试听头部标签切换
    POST_YW_FL_TITLE,//要闻头部标签切换
    POST_RL_FL_TITLE,//日历数据中心头部标签切换
    POST_RL_REFRESH,//
    POST_RL_REFRESH_TAB,//
    POST_RL_CHANGE_DAY,//
    POST_RL_CURRENT_TIME,
    POST_HQ_ZX_EDIT,//
    POST_MAIN_AD;//
    private Object obj;

    public EventType setObject(Object obj) {
        this.obj = obj;
        return this;
    }

    public Object getObj() {
        return obj;
    }

}

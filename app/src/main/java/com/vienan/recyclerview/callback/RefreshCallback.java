package com.vienan.recyclerview.callback;

/**
 * 刷新事件的回调
 * Created by leege100 on 15-5-14.
 */
public interface RefreshCallback {

    /**
     * 开始刷新
     * @param info
     */
    public void onRefreshStart(String info);

    /**
     * 刷新失败
     * @param info
     */
    public void onRefreshFailed(String info);

    /**
     * 刷新成功
     * @param info
     */
    public void onRefreshSucced(String info);

    /**
     * 是否可以刷新
     * @return
     */
    public boolean isAbleRefresh();

}

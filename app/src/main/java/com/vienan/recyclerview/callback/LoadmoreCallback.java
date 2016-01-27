package com.vienan.recyclerview.callback;

/**
 * 加载更多的回调
 * Created by leege100 on 15-5-14.
 */
public interface LoadmoreCallback {

    /**
     * 开始加载更多
     * @param info
     */
    public void onLoadmoreStart(String info);

    /**
     * 加载更多成功
     * @param info
     */
    public void onLoadmoreSucced(String info);

    /**
     * 加载更多失败
     * @param info
     */
    public void onLoadmoreFail(String info);

    /**
     * 完成加载了所有条目
     * @param info
     */
    public void onLoadedAll(String info);

    /**
     * 是否可以加载更多
     * @return
     */
    public boolean isAbleLoadmore();

}

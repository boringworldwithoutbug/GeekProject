package com.guoxw.geekproject.gankio.ui.views

/**
 * Created by guoxw on 2017/11/7 0007.
 */
interface IWebView {

    /**
     * 显示进度条
     * @param progress 百分数
     */
    fun showProgressBar(progress: Int)

    /**
     * 设置标题
     * @param title 设置页面标题
     */
    fun setWebTitle(title: String)

    /**
     * 打开失败
     */
    fun openFailed()

}
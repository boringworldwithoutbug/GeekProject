package com.guoxw.geekproject.base

import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import com.guoxw.geekproject.R
import kotlinx.android.synthetic.main.activity_toolbar_base.*
import kotlinx.android.synthetic.main.include_toolbar.*

/**
 * Created by guoxw on 2017/9/8 0008.
 * @auther guoxw
 * @date 2017/9/8 0008
 * @desciption
 * @package com.guoxw.geekproject.base
 */
abstract class BaseToolbarActivity : BaseActivity() {

    /**
     * 显示标识
     */
    protected var isToolBarHiding = false

    /**
     * 初始化试图
     */
    protected abstract fun initUI()

    /**
     * 绑定视图
     * @return getContentLayoutId 视图id
     */
    abstract fun getContentLayoutId(): Int

    /**
     * 绑定父视图
     */
    override fun getLayoutId(): Int = R.layout.activity_toolbar_base

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initView() {
        //找寻视图
        val contentView = LayoutInflater.from(this).inflate(getContentLayoutId(), null)
        //添加到Fragment中
        fl_toolbar_base.addView(contentView)
        //设置toolbar标题
        setToolBar(tb_toolbar_base, "")
        //设置标题颜色
        window.statusBarColor = Color.RED

        initUI()
    }

    /**
     * 设置title
     * 子类可以直接用
     *
     * @param toolbar
     * @param title
     */
    protected fun setToolBar(toolbar: Toolbar, title: String) {
        //设置标题
        toolbar.title = title
        setSupportActionBar(toolbar)
        //标题颜色
        toolbar.setTitleTextColor(Color.WHITE)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)//显示返回按钮
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //返回按钮监听事件
        toolbar.setNavigationOnClickListener {
            //返回
            onBackPressed()
        }
    }

    /**
     * 隐藏或者关闭toolbar
     */
    protected fun hideOrShowToolBar() {

        app_bar.animate()//设置动画
                .translationY((if (isToolBarHiding) 0 else -supportActionBar!!.height).toFloat())
                .setInterpolator(DecelerateInterpolator(2f))
                .start()

        isToolBarHiding = !isToolBarHiding
    }

}
package com.guoxw.geekproject.gankio.ui.activity

import com.bumptech.glide.Glide
import com.guoxw.geekproject.R
import com.guoxw.geekproject.base.BaseToolbarActivity
import kotlinx.android.synthetic.main.activity_beauty.*
import kotlinx.android.synthetic.main.activity_toolbar_base.*

class BeautyActivity : BaseToolbarActivity() {

    var url: String? = ""

    override fun initUI() {
        setToolBar(tb_toolbar_base, "福利")

        Glide.with(this)
                .load(url)
                .into(img_beauty)
    }

    override fun getContentLayoutId(): Int = R.layout.activity_beauty

    override fun initData() {

        val data = intent.getBundleExtra("data")
        url = data.getString("url")

    }

    override fun initListener() {


    }

//    override fun getLayoutId(): Int = R.layout.activity_beauty


}
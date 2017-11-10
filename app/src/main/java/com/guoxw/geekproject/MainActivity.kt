package com.guoxw.geekproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.Gravity
import android.view.KeyEvent
import android.widget.Toast
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.services.weather.LocalWeatherForecastResult
import com.amap.api.services.weather.LocalWeatherLiveResult
import com.amap.api.services.weather.WeatherSearch
import com.amap.api.services.weather.WeatherSearchQuery
import com.blankj.utilcode.utils.ToastUtils
import com.guoxw.geekproject.base.BaseActivity
import com.guoxw.geekproject.calendar.ui.fargment.CalendarFragment
import com.guoxw.geekproject.gankio.ui.fragment.FragmentGank
import com.guoxw.geekproject.utils.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_main_left.*
import kotlinx.android.synthetic.main.include_title_main.*
import java.util.*


class MainActivity : BaseActivity(), AMapLocationListener {

    private val fragmentGank: FragmentGank = FragmentGank()
    private val calendarFragment: CalendarFragment = CalendarFragment()

    private val mainFragments: MutableList<Fragment> = ArrayList<Fragment>()

    /**
     * 定位终端
     */
    private val mlocationClient: AMapLocationClient = AMapLocationClient(this)

    /**
     * 定位参数
     */
    private val mlocationOption: AMapLocationClientOption = AMapLocationClientOption()

    override fun getLayoutId(): Int = R.layout.activity_main


    override fun initView() {

        tv_title_menu.text = "首页"

        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.add(R.id.fl_main_content, mainFragments[0], "main")
        beginTransaction.add(R.id.fl_main_content, mainFragments[1], "cale")
//        beginTransaction.addToBackStack(null)
        beginTransaction.show(mainFragments[0])
        beginTransaction.hide(mainFragments[1])
        beginTransaction.commit()
    }

    override fun initData() {
        //注意顺序
        mainFragments.add(fragmentGank)
        mainFragments.add(calendarFragment)

        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
        val mquery = WeatherSearchQuery("北京", WeatherSearchQuery.WEATHER_TYPE_LIVE)


        val mweathersearch = WeatherSearch(this)
        mweathersearch.setOnWeatherSearchListener(object : WeatherSearch.OnWeatherSearchListener {
            override fun onWeatherLiveSearched(result: LocalWeatherLiveResult?, rCode: Int) {
                tv_main_city.text = result!!.liveResult.city
                tv_main_temp.text = result!!.liveResult.temperature.plus("℃")
                tv_main_weather.text = result!!.liveResult.weather.plus(" | 风力").plus(result!!.liveResult.windPower).plus("级")
            }

            override fun onWeatherForecastSearched(localWeatherForecastResult: LocalWeatherForecastResult?, rCode: Int) {
            }

        })
        //一次只能查实时数据或者预报数据
        mweathersearch.query = mquery
        mweathersearch.searchWeatherAsyn() //异步搜索

    }

    override fun initListener() {

        fl_theme_main.setOnClickListener {
            showNewPage(mainFragments[0])
            tv_title_menu.text = "首页"
        }

        fl_theme_calendar.setOnClickListener {
            showNewPage(mainFragments[1])
            tv_title_menu.text = "程序猿老黄历"
        }

        //退出
        fl_exit_app.setOnClickListener {
            exitApp()
        }

        //反馈
        fl_feedback.setOnClickListener {
            openActivity(FeedbackActivity::class.java, Bundle())
        }

        //关于
        fl_about_us.setOnClickListener {
            openActivity(AboutActivity::class.java, Bundle())
        }

        fl_setting.setOnClickListener {
            openActivity(SettingActivity::class.java, Bundle())
        }

        fl_title_main_menu.setOnClickListener {
            dl_main.openDrawer(Gravity.LEFT)
        }

    }

    /**
     * 显示要展示的页面
     * @param fragment
     */
    fun showNewPage(fragment: Fragment) {

        val beginTransaction = supportFragmentManager.beginTransaction()
        hideAllPage(beginTransaction)
        beginTransaction.show(fragment)
        beginTransaction.commit()
        dl_main.closeDrawers()

    }

    /**
     * 隐藏所有fragment
     */
    fun hideAllPage(transaction: FragmentTransaction) {

        mainFragments.forEach { fragment ->
            transaction.hide(fragment)
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click()
        }
        return false
    }

    fun initLocation() {

        //设置定位监听
        mlocationClient.setLocationListener(this)
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mlocationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        // 设置定位间隔
        mlocationOption.interval = 2000
        //设置定位参数
        mlocationClient.setLocationOption(mlocationOption)

    }

    override fun onLocationChanged(aMapLocation: AMapLocation?) {
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}

package com.guoxw.geekproject.gankio.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.guoxw.geekproject.R
import com.guoxw.geekproject.gankio.bean.GankData
import java.util.*


/**
 * Created by guoxw on 2017/9/6 0006.
 * @auther guoxw
 * @date 2017/9/6 0006
 * @desciption
 * @package com.guoxw.geekproject.gankio.adapter
 */
class WaterFallAdapter : RecyclerView.Adapter<WaterFallAdapter.ViewHolder> {

    var mImages: MutableList<GankData> = ArrayList<GankData>()

    var mHeights: MutableList<Int>? = null

    var mContext: Context? = null

    constructor(mContext: Context?) : super() {
        this.mContext = mContext
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_gank_image, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mImages.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val layoutParams = holder!!.itemView.layoutParams
        layoutParams.height = mHeights!![position]
        holder.itemView.layoutParams = layoutParams
        if (holder is ViewHolder) {
            val gankData = mImages[position]



        }

    }

    fun getRandomHeight(mList: List<GankData>) {
        mHeights = ArrayList<Int>()
        for (i in mList.indices) {
            //随机的获取一个范围为200-600直接的高度
            mHeights!!.add((300 + Math.random() * 400).toInt())
        }
    }


    class ViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View?) : super(itemView) {}
    }

}
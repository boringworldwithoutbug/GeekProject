package com.guoxw.geekproject.gankio.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.guoxw.geekproject.R
import com.guoxw.geekproject.events.RCVItemClickListener
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

    var mHeights: MutableList<Int> = ArrayList<Int>()

    var mContext: Context? = null

    var rcvItemClickListener: RCVItemClickListener? = null

    constructor(mContext: Context?) : super() {
        this.mContext = mContext
    }

    constructor(mContext: Context?, rcvItemClickListener: RCVItemClickListener?) : super() {
        this.mContext = mContext
        this.rcvItemClickListener = rcvItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_gank_image, parent, false)

        return ViewHolder(view, rcvItemClickListener)
    }

    override fun getItemCount(): Int {
        return mImages.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val layoutParams = holder!!.itemView.layoutParams
        layoutParams.height = mHeights!![position]
        holder.itemView.layoutParams = layoutParams
        val gankData = mImages[position]

        Glide.with(mContext)
                .load(gankData.url)
                .into(holder.img_item_gank)

    }

    fun getRandomHeight(mList: MutableList<GankData>) {

        for (i in mList.indices) {
            //随机的获取一个范围为200-600直接的高度
            mHeights!!.add((300 + Math.random() * 400).toInt())
        }
    }


    class ViewHolder : RecyclerView.ViewHolder {

        var img_item_gank: ImageView? = null
        var tv_item_gank: TextView? = null
        var itemClickListener: RCVItemClickListener? = null

        var cv_item_gank: CardView? = null

        constructor(itemView: View?, itemClickListener: RCVItemClickListener?) : super(itemView) {
            this.itemClickListener = itemClickListener
            img_item_gank = itemView!!.findViewById(R.id.img_item_gank) as ImageView
            tv_item_gank = itemView!!.findViewById(R.id.tv_item_gank) as TextView

            cv_item_gank = itemView!!.findViewById(R.id.cv_item_gank) as CardView

            cv_item_gank!!.setOnClickListener { view ->
                itemClickListener!!.onItemClickListener(view, adapterPosition)
            }

            cv_item_gank!!.setOnLongClickListener { view ->
                itemClickListener!!.onItemLongClickListener(view, adapterPosition)
                false
            }
        }


    }

}
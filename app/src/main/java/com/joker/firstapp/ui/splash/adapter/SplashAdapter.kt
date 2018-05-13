package com.joker.firstapp.ui.splash.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by Vincent;
 * Created on 2018/5/11;
 * DSC:   引导页的PagerAdapter
 */
class SplashAdapter(var context : Context, var resId : IntArray) : PagerAdapter() {

    override fun isViewFromObject(view : View, `object` : Any) : Boolean {
        return view == `object`
    }

    override fun getCount() : Int {
        return resId.size
    }

    override fun destroyItem(container : ViewGroup, position : Int, `object` : Any) {
        container.removeView(`object` as View)
    }


    override fun instantiateItem(container : ViewGroup, position : Int) : Any {
        val img = ImageView(context)
        img.scaleType = ImageView.ScaleType.FIT_XY
        img.setImageResource(resId[position])
        container.addView(img)
        return img
    }
}
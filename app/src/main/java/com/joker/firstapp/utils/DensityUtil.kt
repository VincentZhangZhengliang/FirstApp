package com.joker.firstapp.utils

import android.content.Context


/**
 * Created by Vincent;
 * Created on 2018/5/11;
 * DSC:
 */
object DensityUtil {

    var RATIO = 0.95f//缩放比例值

    /**
     * px 转 dp【按照一定的比例】
     */
    fun px2dipRatio(context: Context, pxValue: Float): Int {
        val scale = getScreenDendity(context) * RATIO
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px【按照一定的比例】
     */
    fun dip2pxRatio(context: Context, dpValue: Float): Int {
        val scale = getScreenDendity(context) * RATIO
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * px 转 dp
     * 48px - 16dp
     * 50px - 17dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = getScreenDendity(context)
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * dp转px
     * 16dp - 48px
     * 17dp - 51px
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = getScreenDendity(context)
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕的宽度（像素）
     */
    fun getScreenWidth(context: Context?): Int? {
        return context?.resources?.displayMetrics?.widthPixels//1080
    }

    /**
     * 获取屏幕的高度（像素）
     */
    fun getScreenHeight(context: Context?): Int? {
        return context?.resources?.displayMetrics?.heightPixels//1776
    }

    /**
     * 获取屏幕的宽度（dp）
     */
    fun getScreenWidthDp(context: Context): Int {
        val scale = getScreenDendity(context)
        return context.resources.displayMetrics.widthPixels / scale.toInt()
    }

    /**
     * 获取屏幕的高度（像素）
     */
    fun getScreenHeightDp(context: Context): Int {
        val scale = getScreenDendity(context)
        return context.resources.displayMetrics.heightPixels / scale.toInt()
    }

    /**屏幕密度比例 */
    fun getScreenDendity(context: Context): Float {
        return context.resources.displayMetrics.density//3
    }

    /**获取状态栏的高度 72px
     * http://www.2cto.com/kf/201501/374049.html */
    fun getStatusBarHeight(context: Context): Int {
        var statusHeight = -1
        try {
            val aClass = Class.forName("com.android.internal.R\$dimen")
            val `object` = aClass.newInstance()
            val height = Integer.parseInt(aClass.getField("status_bar_height").get(`object`).toString())
            statusHeight = context.getResources().getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }

    /**
     * 指定机型（displayMetrics.xdpi）下dp转px
     * 18dp - 50px */
    fun dpToPx(context: Context, dp: Int): Int {
        return Math.round(dp.toFloat() * getPixelScaleFactor(context))
    }

    /**
     * 指定机型（displayMetrics.xdpi）下px 转 dp
     * 50px - 18dp */
    fun pxToDp(context: Context, px: Int): Int {
        return Math.round(px.toFloat() / getPixelScaleFactor(context))
    }

    /**获取水平方向的dpi的密度比例值
     * 2.7653186 */
    fun getPixelScaleFactor(context: Context): Float {
        val displayMetrics = context.getResources().getDisplayMetrics()
        return displayMetrics.xdpi / 160.0f
    }

}
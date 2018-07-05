package com.wangsz.meizi.util

import android.util.DisplayMetrics
import com.wangsz.meizi.App

/**
 * author: wangsz
 * date: On 2018/7/5 0005
 */
object ScreenUtil {


    val displayMetrics: DisplayMetrics by lazy {
        App.instance.resources.displayMetrics
    }

    /**
     * 相对屏幕的宽度
     */
    fun width(rate: Float): Float {
        return displayMetrics.widthPixels * rate
    }

    fun widthInt(rate: Float): Int {
        return width(rate).toInt()
    }

    /**
     * 屏幕的高度
     */
    fun height(rate: Float): Float {
        return displayMetrics.heightPixels * rate
    }

    fun heightInt(rate: Float): Int {
        return height(rate).toInt()
    }

    /**
     * 将dip转换为px
     *
     * @param dip
     * @return dip转换为px
     */
    fun dip2px(dip: Int): Int {
        return (dip * displayMetrics.density + 0.5f * if (dip >= 0) 1 else -1).toInt()
    }

}
package com.wangsz.meizi.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * author: wangsz
 * date: On 2018/7/6 0006
 */
object ImageLoader {

    fun load(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url)
                .apply(RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate())
                .into(imageView)
    }

}
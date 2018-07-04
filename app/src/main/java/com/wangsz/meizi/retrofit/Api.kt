package com.wangsz.meizi.retrofit

import android.util.Log

/**
 * author: wangsz
 * date: On 2018/7/4 0004
 */
object Api {

    val apiservice: Apiservice by lazy {
        Log.d("Api", "apiservice create lazy")
        RetrofitUtil.retrofit.create(Apiservice::class.java)
    }

}
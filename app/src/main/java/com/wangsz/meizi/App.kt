package com.wangsz.meizi

import android.app.Application

/**
 * author: wangsz
 * date: On 2018/7/5 0005
 */
class App : Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}
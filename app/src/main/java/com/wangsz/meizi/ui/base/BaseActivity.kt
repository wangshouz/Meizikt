package com.wangsz.meizi.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : RxAppCompatActivity() {

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
    }

}

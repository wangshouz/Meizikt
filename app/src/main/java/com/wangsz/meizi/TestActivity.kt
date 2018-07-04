package com.wangsz.meizi

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.wangsz.meizi.model.Result
import com.wangsz.meizi.retrofit.Api
import com.wangsz.meizi.retrofit.HttpObserver
import com.wangsz.meizi.retrofit.Rx

class TestActivity : RxAppCompatActivity() {

    val mTvHello: TextView by lazy {
        findViewById<TextView>(R.id.tv_hello)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mTvHello.setOnClickListener({
            Rx.get(Api.apiservice.getGank("Android", 1), this).subscribe(object : HttpObserver<List<Result>>() {
                override fun onSuccess(t: List<Result>?) {
                    Log.d(this.toString(), t?.size.toString())
                }
            })
        })

    }
}
